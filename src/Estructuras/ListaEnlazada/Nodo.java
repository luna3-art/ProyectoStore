/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras.ListaEnlazada;

/*
 * Se utilizan genéricos (<T>) para que las estructuras de datos sean
 * reutilizables y puedan almacenar diferentes tipos de objetos
 * (Cliente, Producto, Venta, etc.) sin duplicar código.
 * Esto mejora la flexibilidad, el mantenimiento y la seguridad del programa.
 */


public class Nodo<T> {
    
    // Creamos atributos
    T dato;
    Nodo<T> siguiente;
    
    // Constructor

    public Nodo(T dato){
        this.dato = dato;
        this.siguiente = null;
    }    
    
}
