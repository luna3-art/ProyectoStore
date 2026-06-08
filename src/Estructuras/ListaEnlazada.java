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

        Nodo<T> aux = cabeza;

        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }

        aux.setSiguiente(nuevo);
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public int tamaño() {

        int contador = 0;

        Nodo<T> aux = cabeza;

        while (aux != null) {
            contador++;
            aux = aux.getSiguiente();
        }

        return contador;
    }
}
