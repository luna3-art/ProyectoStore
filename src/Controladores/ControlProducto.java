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
}