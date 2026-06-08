/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;


public class ControlLogin {
    public boolean validar(String usuario, String password) {
        return usuario.equals("admin") && password.equals("123");
    }
}
