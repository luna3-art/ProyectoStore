/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Cliente {
    
    // Crear atributos
    private int id;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;
    
    // Constructor sin parámetros
    public void Cliente(){
    }
    
    // Constructor con parámetros
    public Cliente(int id, String nombre, String dni, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    // Métodos get - leer

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
    
    
    // Métodos set

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
