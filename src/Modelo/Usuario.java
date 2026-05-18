/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Luna
 */

// Encapsulamiento
public class Usuario {
    
    // Crear variables
    private String usuario;
    private String password;
    
    // Constructor
    public Usuario(String usuario, String contraseña){
        this.usuario=usuario;
        this.password=password;
    }
    
    // Metodos get - leer
    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
    
    
}
