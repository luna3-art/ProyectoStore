package Controlador;

import Estructuras.ListaEnlazada.Nodo;
import Modelo.Producto;
import servicios.DatosSistema;
import javax.swing.table.DefaultTableModel;

public class ControlProducto {

    private DatosSistema datos = DatosSistema.getInstancia();

    public void cargarTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        Nodo<Producto> temp = datos.getProductos().getCabeza();
        while (temp != null) {
            Producto p = temp.dato;
            modelo.addRow(new Object[]{
                p.getCodigo(), p.getNombre(), p.getCategoria(),
                String.format("S/ %.2f", p.getPrecio()), p.getStock()
            });
            temp = temp.siguiente;
        }
    }

    public void cargarTablaPorCategoria(DefaultTableModel modelo, String categoria) {
        modelo.setRowCount(0);
        Nodo<Producto> temp = datos.getProductos().getCabeza();
        while (temp != null) {
            Producto p = temp.dato;
            if (categoria.equals("Todas") || p.getCategoria().equals(categoria)) {
                modelo.addRow(new Object[]{
                    p.getCodigo(), p.getNombre(), p.getCategoria(),
                    String.format("S/ %.2f", p.getPrecio()), p.getStock()
                });
            }
            temp = temp.siguiente;
        }
    }

    public void buscarEnTabla(DefaultTableModel modelo, String texto) {
        modelo.setRowCount(0);
        String busqueda = texto.toLowerCase().trim();
        Nodo<Producto> temp = datos.getProductos().getCabeza();
        while (temp != null) {
            Producto p = temp.dato;
            if (p.getCodigo().toLowerCase().contains(busqueda)
                    || p.getNombre().toLowerCase().contains(busqueda)
                    || p.getCategoria().toLowerCase().contains(busqueda)) {
                modelo.addRow(new Object[]{
                    p.getCodigo(), p.getNombre(), p.getCategoria(),
                    String.format("S/ %.2f", p.getPrecio()), p.getStock()
                });
            }
            temp = temp.siguiente;
        }
    }

    public boolean agregarProducto(String codigo, String nombre, String categoria,
                                    double precio, int stock) {
        if (datos.buscarProductoPorCodigo(codigo) != null) return false;
        datos.agregarProducto(new Producto(codigo, nombre, categoria, precio, stock));
        return true;
    }

    public boolean eliminarProducto(String codigo) {
        return datos.eliminarProducto(codigo);
    }

    public Producto obtenerProducto(String codigo) {
        return datos.buscarProductoPorCodigo(codigo);
    }

    public void actualizarProducto(String codigo, String nombre, String categoria,
                                    double precio, int stock) {
        datos.actualizarProducto(new Producto(codigo, nombre, categoria, precio, stock));
    }

    public int getTotalProductos() { return datos.getTotalProductos(); }
}
