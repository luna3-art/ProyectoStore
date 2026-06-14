/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Estructuras.Cola;
import Estructuras.Nodo;
import Modelo.Pedido;

public class ControlPedido {

    private Cola<Pedido> pedidos;

    public ControlPedido() {
        pedidos = new Cola<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.encolar(pedido);
    }

    public Cola<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido buscarPorId(String idPedido) {

        Nodo<Pedido> aux = pedidos.getFrenteNodo();

        while (aux != null) {

            Pedido p = aux.getDato();

            if (p.getIdPedido().equalsIgnoreCase(idPedido)) {
                return p;
            }

            aux = aux.getSiguiente();
        }

        return null;
    }

    public boolean actualizarPedido(Pedido pedidoActualizado) {

        Nodo<Pedido> aux = pedidos.getFrenteNodo();

        while (aux != null) {

            Pedido p = aux.getDato();

            if (p.getIdPedido().equalsIgnoreCase(
                    pedidoActualizado.getIdPedido())) {

                p.setCliente(pedidoActualizado.getCliente());
                p.setFecha(pedidoActualizado.getFecha());
                p.setTotal(pedidoActualizado.getTotal());
                p.setEstado(pedidoActualizado.getEstado());

                return true;
            }

            aux = aux.getSiguiente();
        }

        return false;
    }

    public Pedido procesarPedido() {

        return pedidos.desencolar();
    }

}
