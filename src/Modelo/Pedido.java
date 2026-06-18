/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Estructuras.ListaEnlazada;

public class Pedido {

    private String idPedido;
    private String cliente;
    private String fecha;
    private double total;
    private String estado;

    private ListaEnlazada<DetallePedido> detalles;

    public Pedido(String idPedido, String cliente,
                  String fecha, double total,
                  String estado) {

        this.idPedido = idPedido;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;

        detalles = new ListaEnlazada<>();
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ListaEnlazada<DetallePedido> getDetalles() {
        return detalles;
    }

    public void agregarDetalle(DetallePedido detalle) {

        detalles.agregar(detalle);

        total += detalle.getSubtotal();
    }

    @Override
    public String toString() {
        return idPedido;
    }
}