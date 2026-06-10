/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Estructuras.ListaEnlazada;
import Estructuras.Nodo;
import Modelo.Producto;

public class ControlProducto {

    private ListaEnlazada<Producto> productos;

    public ControlProducto() {
        productos = new ListaEnlazada<>();
    }

    public void agregarProducto(Producto producto) {
        productos.agregar(producto);
    }

    public ListaEnlazada<Producto> getProductos() {
        return productos;
    }

    public Producto buscarPorCodigo(String codigo) {

        Nodo<Producto> aux = productos.getCabeza();

        while (aux != null) {

            if (aux.getDato().getCodigo().equalsIgnoreCase(codigo)) {
                return aux.getDato();
            }

            aux = aux.getSiguiente();
        }

        return null;
    }
    
    //METODO PARA ELIMINAR
    public boolean eliminarProducto(String codigo) {

        Nodo<Producto> aux = productos.getCabeza();

        while (aux != null) {

            if (aux.getDato().getCodigo().equalsIgnoreCase(codigo)) {
                return productos.eliminar(aux.getDato());
            }

            aux = aux.getSiguiente();
        }

        return false;
    }
    
    //METODO PARA ACTUALIZAR
    public boolean actualizarProducto(Producto productoActualizado) {

        Nodo<Producto> aux = productos.getCabeza();

        while (aux != null) {

            Producto p = aux.getDato();

            if (p.getCodigo().equalsIgnoreCase(productoActualizado.getCodigo())) {

                p.setNombre(productoActualizado.getNombre());
                p.setCategoria(productoActualizado.getCategoria());
                p.setPrecio(productoActualizado.getPrecio());
                p.setStock(productoActualizado.getStock());

                return true;
            }

            aux = aux.getSiguiente();
        }

        return false;
    }
    
    //METODO PARA BUSCAR POR CODIGO O NOMBRE
    public ListaEnlazada<Producto> buscarProductos(String texto) {

        ListaEnlazada<Producto> resultados = new ListaEnlazada<>();

        Nodo<Producto> temp = productos.getCabeza();

        while (temp != null) {

            Producto p = temp.getDato();

            if (p.getCodigo().toLowerCase().contains(texto.toLowerCase())
                || p.getNombre().toLowerCase().contains(texto.toLowerCase())) {

            resultados.agregar(p);
            }

            temp = temp.getSiguiente();
        }
        return resultados;
    }
    
}