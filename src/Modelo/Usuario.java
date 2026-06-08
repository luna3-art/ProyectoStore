/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


// Encapsulamiento
public class Usuario {
    
    // Crear atributos
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
    
    // Métodos set

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
}
