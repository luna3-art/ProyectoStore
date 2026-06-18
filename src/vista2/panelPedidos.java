/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista2;

import Controladores.ControlPedido;
import Modelo.Pedido;
import Estructuras.Nodo;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.time.LocalDate;
import Controladores.DatosSistema;

import Modelo.Producto;
import Modelo.DetallePedido;
import Controladores.DatosSistema;
import Controladores.ControlProducto;

public class panelPedidos extends javax.swing.JPanel {

    /**
     * Creates new form panelPedidos
     */
    private ControlPedido controlador = DatosSistema.controlPedido;
    private ControlProducto controlProducto = DatosSistema.controlProducto;

    public panelPedidos() {
        initComponents();

        cboxPedidos.setSelectedItem("Pendientes");
        cargarTabla();
    }

    //METODO PARA CARGAR LA TABLA
    private void cargarTabla() {

        DefaultTableModel modelo
                = (DefaultTableModel) tblPedidos.getModel();

        modelo.setRowCount(0);

        String filtro
                = cboxPedidos.getSelectedItem().toString();

        if (filtro.equals("Pendientes")) {

            Nodo<Pedido> aux
                    = controlador.getPendientes().getFrenteNodo();

            while (aux != null) {

                Pedido p = aux.getDato();

                modelo.addRow(new Object[]{
                    p.getIdPedido(),
                    p.getCliente(),
                    p.getFecha(),
                    p.getTotal(),
                    p.getEstado(),
                    ""
                });

                aux = aux.getSiguiente();
            }
        } else if (filtro.equals("En Proceso")) {

            Nodo<Pedido> aux
                    = controlador.getEnProceso().getCabeza();

            while (aux != null) {

                Pedido p = aux.getDato();

                modelo.addRow(new Object[]{
                    p.getIdPedido(),
                    p.getCliente(),
                    p.getFecha(),
                    p.getTotal(),
                    p.getEstado(),
                    ""
                });

                aux = aux.getSiguiente();
            }
        } else if (filtro.equals("Completados")) {

            Nodo<Pedido> aux
                    = controlador.getCompletados().getCabeza();

            while (aux != null) {

                Pedido p = aux.getDato();

                modelo.addRow(new Object[]{
                    p.getIdPedido(),
                    p.getCliente(),
                    p.getFecha(),
                    p.getTotal(),
                    p.getEstado(),
                    ""
                });

                aux = aux.getSiguiente();
            }
        } else if (filtro.equals("Cancelados")) {

            Nodo<Pedido> aux
                    = controlador.getCancelados().getCabeza();

            while (aux != null) {

                Pedido p = aux.getDato();

                modelo.addRow(new Object[]{
                    p.getIdPedido(),
                    p.getCliente(),
                    p.getFecha(),
                    p.getTotal(),
                    p.getEstado(),
                    ""
                });

                aux = aux.getSiguiente();
            }
        }
    }

    //METODO PARA AGREGAR PEDIDO
    private void mostrarDialogoNuevoPedido() {

        JTextField txtId = new JTextField();
        JTextField txtCliente = new JTextField();

        Object[] campos = {
            "ID Pedido:", txtId,
            "Cliente:", txtCliente
        };

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                campos,
                "Nuevo Pedido",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (respuesta != JOptionPane.OK_OPTION) {
            return;
        }

        String fechaActual = LocalDate.now().toString();

        Pedido pedido = new Pedido(
                txtId.getText().trim(),
                txtCliente.getText().trim(),
                fechaActual,
                0,
                "Pendiente"
        );

        boolean seguir = true;

        while (seguir) {

            JComboBox<Producto> cmbProductos = new JComboBox<>();

            Nodo<Producto> temp = controlProducto.getProductos().getCabeza();

            while (temp != null) {

                cmbProductos.addItem(temp.getDato());

                temp = temp.getSiguiente();
            }

            JTextField txtCantidad
                    = new JTextField();

            Object[] detalle = {
                "Producto:", cmbProductos,
                "Cantidad:", txtCantidad
            };

            int r = JOptionPane.showConfirmDialog(
                    this,
                    detalle,
                    "Agregar Producto",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (r != JOptionPane.OK_OPTION) {
                break;
            }

            try {

                Producto producto = (Producto) cmbProductos.getSelectedItem();

                int cantidad = Integer.parseInt(txtCantidad.getText());

                DetallePedido det = new DetallePedido(producto, cantidad);

                pedido.agregarDetalle(det);

                int opcion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Agregar otro producto?",
                        "Continuar",
                        JOptionPane.YES_NO_OPTION
                );

                seguir = opcion == JOptionPane.YES_OPTION;

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cantidad inválida."
                );
            }
        }

        controlador.agregarPedido(pedido);

        cargarTabla();

        JOptionPane.showMessageDialog(this, "Pedido registrado correctamente.\n" + "Total: S/ " + pedido.getTotal());
    }

    //METODO PARA CARGAR DETALLES
    private void mostrarDetallePedido() {

        int fila = tblPedidos.getSelectedRow();

        if (fila == -1) {
            return;
        }

        String idPedido = tblPedidos.getValueAt(fila, 0).toString();

        Pedido pedido = controlador.buscarPedido(idPedido);

        if (pedido == null) {
            return;
        }

        DefaultTableModel modelo
                = (DefaultTableModel) tablaDetallePedido.getModel();

        modelo.setRowCount(0);

        Nodo<DetallePedido> aux
                = pedido.getDetalles().getCabeza();

        while (aux != null) {

            DetallePedido d = aux.getDato();

            modelo.addRow(new Object[]{
                d.getProducto().getNombre(),
                d.getCantidad(),
                d.getProducto().getPrecio(),
                d.getSubtotal()
            });

            aux = aux.getSiguiente();
        }

        jLabel1e.setText(
                "Detalle del Pedido #" + pedido.getIdPedido()
        );

        jLabel1e1.setText(
                "Total: S/ " + pedido.getTotal()
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblInventario = new javax.swing.JLabel();
        lblSubtitulo3 = new javax.swing.JLabel();
        btnNuevoPedido = new javax.swing.JButton();
        panelPedidoss = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        panelDetallePedido = new javax.swing.JPanel();
        jLabel1e = new javax.swing.JLabel();
        jLabel1e1 = new javax.swing.JLabel();
        jScrollPaneDetallePedido = new javax.swing.JScrollPane();
        tablaDetallePedido = new javax.swing.JTable();
        btnProcesar = new javax.swing.JButton();
        btnCompletar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cboxPedidos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Categoría:");
        jLabel1.setAlignmentX(650.0F);
        jLabel1.setAlignmentY(25.0F);

        setBackground(new java.awt.Color(248, 249, 251));
        setPreferredSize(new java.awt.Dimension(1000, 700));

        lblInventario.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblInventario.setForeground(new java.awt.Color(17, 24, 39));
        lblInventario.setText("Gestión de Pedidos");
        lblInventario.setAlignmentX(40.0F);
        lblInventario.setAlignmentY(25.0F);

        lblSubtitulo3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubtitulo3.setForeground(new java.awt.Color(17, 24, 39));
        lblSubtitulo3.setText("Administra y procesa los pedidos de los clientes.");

        btnNuevoPedido.setBackground(new java.awt.Color(179, 227, 4));
        btnNuevoPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevoPedido.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoPedido.setText("+ Nuevo Pedido");
        btnNuevoPedido.setAlignmentX(780.0F);
        btnNuevoPedido.setAlignmentY(35.0F);
        btnNuevoPedido.setPreferredSize(new java.awt.Dimension(160, 40));
        btnNuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPedidoActionPerformed(evt);
            }
        });

        panelPedidoss.setBackground(new java.awt.Color(255, 255, 255));
        panelPedidoss.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                panelPedidossComponentAdded(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblPedidos.setBackground(new java.awt.Color(255, 255, 255));
        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Pedido", "Cliente", "Fecha", "Total", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidos);
        if (tblPedidos.getColumnModel().getColumnCount() > 0) {
            tblPedidos.getColumnModel().getColumn(0).setResizable(false);
            tblPedidos.getColumnModel().getColumn(1).setResizable(false);
            tblPedidos.getColumnModel().getColumn(2).setResizable(false);
            tblPedidos.getColumnModel().getColumn(3).setResizable(false);
            tblPedidos.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelPedidossLayout = new javax.swing.GroupLayout(panelPedidoss);
        panelPedidoss.setLayout(panelPedidossLayout);
        panelPedidossLayout.setHorizontalGroup(
            panelPedidossLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        panelPedidossLayout.setVerticalGroup(
            panelPedidossLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelDetallePedido.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1e.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1e.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1e.setText("Detalle del  Pedido #");

        jLabel1e1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1e1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1e1.setText("Total: S/ ");

        jScrollPaneDetallePedido.setBackground(new java.awt.Color(255, 255, 255));

        tablaDetallePedido.setBackground(new java.awt.Color(255, 255, 255));
        tablaDetallePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio Unit.", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneDetallePedido.setViewportView(tablaDetallePedido);
        if (tablaDetallePedido.getColumnModel().getColumnCount() > 0) {
            tablaDetallePedido.getColumnModel().getColumn(0).setResizable(false);
            tablaDetallePedido.getColumnModel().getColumn(1).setResizable(false);
            tablaDetallePedido.getColumnModel().getColumn(2).setResizable(false);
            tablaDetallePedido.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout panelDetallePedidoLayout = new javax.swing.GroupLayout(panelDetallePedido);
        panelDetallePedido.setLayout(panelDetallePedidoLayout);
        panelDetallePedidoLayout.setHorizontalGroup(
            panelDetallePedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallePedidoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1e)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1e1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPaneDetallePedido, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelDetallePedidoLayout.setVerticalGroup(
            panelDetallePedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallePedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetallePedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1e, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1e1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneDetallePedido, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
        );

        btnProcesar.setBackground(new java.awt.Color(0, 0, 0));
        btnProcesar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProcesar.setForeground(new java.awt.Color(179, 227, 4));
        btnProcesar.setText("Procesar");
        btnProcesar.setAlignmentX(780.0F);
        btnProcesar.setAlignmentY(35.0F);
        btnProcesar.setPreferredSize(new java.awt.Dimension(160, 40));
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        btnCompletar.setBackground(new java.awt.Color(0, 0, 0));
        btnCompletar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCompletar.setForeground(new java.awt.Color(179, 227, 4));
        btnCompletar.setText("Completar");
        btnCompletar.setAlignmentX(780.0F);
        btnCompletar.setAlignmentY(35.0F);
        btnCompletar.setPreferredSize(new java.awt.Dimension(160, 40));
        btnCompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(0, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(179, 227, 4));
        btnCancelar.setText("Cancelar");
        btnCancelar.setAlignmentX(780.0F);
        btnCancelar.setAlignmentY(35.0F);
        btnCancelar.setPreferredSize(new java.awt.Dimension(160, 40));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cboxPedidos.setBackground(new java.awt.Color(255, 255, 255));
        cboxPedidos.setForeground(new java.awt.Color(0, 0, 0));
        cboxPedidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendientes", "En Proceso", "Completados", "Cancelados" }));
        cboxPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPedidosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Estado:");
        jLabel2.setAlignmentX(650.0F);
        jLabel2.setAlignmentY(25.0F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelDetallePedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelPedidoss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblInventario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtitulo3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(245, 245, 245)
                                .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCompletar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(331, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInventario)
                    .addComponent(btnNuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo3)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(panelPedidoss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCompletar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDetallePedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void panelPedidossComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_panelPedidossComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPedidossComponentAdded

    private void btnNuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPedidoActionPerformed
        mostrarDialogoNuevoPedido();
    }//GEN-LAST:event_btnNuevoPedidoActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed

        String estado = cboxPedidos.getSelectedItem().toString();

        if (!estado.equals("Pendientes")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solo puedes procesar pedidos pendientes."
            );

            return;
        }

        Pedido pedido = controlador.iniciarProceso();

        if (pedido != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido enviado a En Proceso."
            );

            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay pedidos pendientes."
            );
        }
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void btnCompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletarActionPerformed

        String estado = cboxPedidos.getSelectedItem().toString();

        if (!estado.equals("En Proceso")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solo puedes completar pedidos En Proceso."
            );

            return;
        }

        int fila = tblPedidos.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un pedido."
            );

            return;
        }

        String idPedido = tblPedidos.getValueAt(fila, 0).toString();

        boolean completado = controlador.completarPedido(idPedido);

        if (completado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido completado y stock actualizado."
            );

            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay stock suficiente para completar el pedido."
            );
        }
    }//GEN-LAST:event_btnCompletarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        int fila = tblPedidos.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(this, "Seleccione un pedido.");

            return;
        }

        String idPedido = tblPedidos.getValueAt(fila, 0).toString();

        boolean cancelado = controlador.cancelarPedido(idPedido);

        if (cancelado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido cancelado."
            );

            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "El pedido debe estar En Proceso."
            );
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cboxPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPedidosActionPerformed
        cargarTabla();
    }//GEN-LAST:event_cboxPedidosActionPerformed

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        mostrarDetallePedido();
    }//GEN-LAST:event_tblPedidosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCompletar;
    private javax.swing.JButton btnNuevoPedido;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JComboBox<String> cboxPedidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1e;
    private javax.swing.JLabel jLabel1e1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDetallePedido;
    private javax.swing.JLabel lblInventario;
    private javax.swing.JLabel lblSubtitulo3;
    private javax.swing.JPanel panelDetallePedido;
    private javax.swing.JPanel panelPedidoss;
    private javax.swing.JTable tablaDetallePedido;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables
}
