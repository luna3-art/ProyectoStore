/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;


public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamaño;

    public Cola() {
        frente = null;
        fin = null;
        tamaño = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }

        tamaño++;
    }

    public T desencolar() {

        if (estaVacia()) {
            return null;
        }

        T dato = frente.getDato();

        frente = frente.getSiguiente();

        if (frente == null) {
            fin = null;
        }

        tamaño--;

        return dato;
    }

    public T frente() {

        if (estaVacia()) {
            return null;
        }

        return frente.getDato();
    }

    public int tamaño() {
        return tamaño;
    }

    public Nodo<T> getFrenteNodo() {
        return frente;
    }
}
