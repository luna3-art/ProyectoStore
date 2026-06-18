/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Estructuras.ListaEnlazada;
import Modelo.Movimiento;

public class DatosSistema {
    
    public static ControlProducto controlProducto = new ControlProducto();

    public static ControlPedido controlPedido = new ControlPedido();
    
    public static int totalEntradas = 0;
    public static int totalSalidas = 0;
    
    public static ListaEnlazada<Movimiento> movimientos = new ListaEnlazada<>();
    
}
