/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;


public class ListaEnlazada<T> {

    private Nodo<T> cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregar(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        if (estaVacia()) {
            cabeza = nuevo;
            return;
        }

        Nodo<T> temp = cabeza;

        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }

        temp.setSiguiente(nuevo);
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public int tamaño() {

        int contador = 0;

        Nodo<T> temp = cabeza;

        while (temp != null) {
            contador++;
            temp = temp.getSiguiente();
        }

        return contador;
    }
    
    //METODO PARA ELIMIAR
    public boolean eliminar(T dato) {

    if (cabeza == null) {
        return false;
    }

    if (cabeza.getDato().equals(dato)) {
        cabeza = cabeza.getSiguiente();
        return true;
    }

    Nodo<T> actual = cabeza;
    Nodo<T> anterior = null;

    while (actual != null) {

        if (actual.getDato().equals(dato)) {
            anterior.setSiguiente(actual.getSiguiente());
            return true;
        }

        anterior = actual;
        actual = actual.getSiguiente();
    }

    return false;
    }
    
}
