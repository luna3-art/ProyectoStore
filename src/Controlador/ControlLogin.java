/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Luna
 */

// Puente entre el modelo y la vista, refuerza el encapsulamiento
public class ControlLogin {
    
    // Método para validar usuario
    public boolean validar(String usuario, String password){
        return usuario.equals("admi")
            && password.equals("123");
    }
}
