/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Estructuras.Cola;
import Estructuras.ListaEnlazada;
import Estructuras.Nodo;
import Modelo.Pedido;

import Modelo.DetallePedido;
import Modelo.Movimiento;
import Modelo.Producto;
import java.time.LocalDate;

public class ControlPedido {

    private Cola<Pedido> pendientes;
    private ListaEnlazada<Pedido> enProceso;
    private ListaEnlazada<Pedido> completados;
    private ListaEnlazada<Pedido> cancelados;

    public ControlPedido() {
        pendientes = new Cola<>();
        enProceso = new ListaEnlazada<>();
        completados = new ListaEnlazada<>();
        cancelados = new ListaEnlazada<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedido.setEstado("Pendiente");
        pendientes.encolar(pedido);
    }

    public Cola<Pedido> getPendientes() {
        return pendientes;
    }

    public ListaEnlazada<Pedido> getEnProceso() {
        return enProceso;
    }

    public ListaEnlazada<Pedido> getCompletados() {
        return completados;
    }

    public ListaEnlazada<Pedido> getCancelados() {
        return cancelados;
    }

    public Pedido iniciarProceso() {

        Pedido pedido = pendientes.desencolar();

        if (pedido != null) {

            pedido.setEstado("En Proceso");

            enProceso.agregar(pedido);
        }

        return pedido;
    }

    public boolean completarPedido(String idPedido) {

        Nodo<Pedido> temp = enProceso.getCabeza();

        while (temp != null) {

            Pedido pedido = temp.getDato();

            if (pedido.getIdPedido().equalsIgnoreCase(idPedido)) {

                Nodo<DetallePedido> detalle
                        = pedido.getDetalles().getCabeza();

                // VALIDAR STOCK
                while (detalle != null) {

                    DetallePedido d = detalle.getDato();

                    if (d.getProducto().getStock()
                            < d.getCantidad()) {

                        return false;
                    }

                    detalle = detalle.getSiguiente();
                }

                // DESCONTAR STOCK
                detalle = pedido.getDetalles().getCabeza();

                while (detalle != null) {

                    DetallePedido d = detalle.getDato();

                    Producto producto = d.getProducto();

                    producto.setStock(producto.getStock() - d.getCantidad());

                    DatosSistema.totalSalidas += d.getCantidad();

                    DatosSistema.movimientos.agregar(
                            new Movimiento(
                                    LocalDate.now().toString(),
                                    "SALIDA",
                                    producto.getNombre(),
                                    detalle.getDato().getCantidad(),
                                    "Venta completada"
                            )
                    );

                    detalle = detalle.getSiguiente();
                }

                enProceso.eliminar(pedido);

                pedido.setEstado("Completado");

                completados.agregar(pedido);

                return true;
            }

            temp = temp.getSiguiente();
        }

        return false;
    }

    public boolean cancelarPedido(String idPedido) {

        Nodo<Pedido> temp = enProceso.getCabeza();

        while (temp != null) {

            Pedido pedido = temp.getDato();

            if (pedido.getIdPedido().equalsIgnoreCase(idPedido)) {

                enProceso.eliminar(pedido);

                pedido.setEstado("Cancelado");

                cancelados.agregar(pedido);

                return true;
            }

            temp = temp.getSiguiente();
        }

        return false;
    }

    public Pedido buscarPedido(String idPedido) {

        Nodo<Pedido> temp;

        temp = pendientes.getFrenteNodo();

        while (temp != null) {

            Pedido p = temp.getDato();

            if (p.getIdPedido().equalsIgnoreCase(idPedido)) {
                return p;
            }

            temp = temp.getSiguiente();
        }

        temp = enProceso.getCabeza();

        while (temp != null) {

            Pedido p = temp.getDato();

            if (p.getIdPedido().equalsIgnoreCase(idPedido)) {
                return p;
            }

            temp = temp.getSiguiente();
        }

        temp = completados.getCabeza();

        while (temp != null) {

            Pedido p = temp.getDato();

            if (p.getIdPedido().equalsIgnoreCase(idPedido)) {
                return p;
            }

            temp = temp.getSiguiente();
        }

        temp = cancelados.getCabeza();

        while (temp != null) {

            Pedido p = temp.getDato();

            if (p.getIdPedido().equalsIgnoreCase(idPedido)) {
                return p;
            }

            temp = temp.getSiguiente();
        }

        return null;
    }

}
