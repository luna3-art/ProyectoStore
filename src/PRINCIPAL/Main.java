/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PRINCIPAL;

import vista2.Login2;

/**
 *
 * @author Luna
 */
public class Main {

    public static void main(String[] args) {
        // Abrir Login2
        java.awt.EventQueue.invokeLater(() -> {
            Login2 login = new Login2();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }
    
}
