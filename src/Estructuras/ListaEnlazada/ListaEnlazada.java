/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras.ListaEnlazada;

import Estructuras.ListaEnlazada.ListaEnlazada;


public class ListaEnlazada<T> {

    private Nodo<T> cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void agregarAlInicio(T dato) {

        Nodo<T> nuevoNodo = new Nodo<>(dato);

        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    public void agregarAlFinal(T dato) {

        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {

            Nodo<T> temp = cabeza;

            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }

            temp.siguiente = nuevoNodo;
        }
    }

    public int cantidadNodos() {

        int contador = 0;

        Nodo<T> temp = cabeza;

        while (temp != null) {
            contador++;
            temp = temp.siguiente;
        }

        return contador;
    }

    public T mostrarPrimerElemento() {

        return (cabeza != null)
                ? cabeza.dato
                : null;
    }

    public T mostrarUltimoElemento() {

        if (cabeza == null) {
            return null;
        }

        Nodo<T> temp = cabeza;

        while (temp.siguiente != null) {
            temp = temp.siguiente;
        }

        return temp.dato;
    }

    public boolean buscarDato(T dato) {

        Nodo<T> temp = cabeza;

        while (temp != null) {

            if (temp.dato.equals(dato)) {
                return true;
            }

            temp = temp.siguiente;
        }

        return false;
    }

    public void eliminarInicio() {

        if (cabeza != null) {
            cabeza = cabeza.siguiente;
        }
    }

    public void eliminarFinal() {

        if (cabeza == null) {
            return;
        }

        if (cabeza.siguiente == null) {
            cabeza = null;
            return;
        }

        Nodo<T> temp = cabeza;

        while (temp.siguiente.siguiente != null) {
            temp = temp.siguiente;
        }

        temp.siguiente = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public String mostrarLista() {

        StringBuilder lista = new StringBuilder();

        Nodo<T> temp = cabeza;

        while (temp != null) {

            lista.append(temp.dato)
                 .append("\n");

            temp = temp.siguiente;
        }

        return lista.length() == 0
                ? "La lista está vacía."
                : lista.toString();
    }

    public boolean agregarDespuesDe(T referencia, T nuevoDato) {

        Nodo<T> temp = cabeza;

        while (temp != null &&
               !temp.dato.equals(referencia)) {

            temp = temp.siguiente;
        }

        if (temp != null) {

            Nodo<T> nuevoNodo =
                    new Nodo<>(nuevoDato);

            nuevoNodo.siguiente =
                    temp.siguiente;

            temp.siguiente = nuevoNodo;

            return true;
        }

        return false;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }
    
    public void vaciar() {
        cabeza = null;
    }
}