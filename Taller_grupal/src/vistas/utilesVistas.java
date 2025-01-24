/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;
import javax.swing.JComboBox;
/**
 *
 * @author InnoVausuario
 */
public class utilesVistas {
     public static void cargarAnios(JComboBox cbx){
        cbx.removeAllItems();
        for (int i= 2000; i <=2024; i ++) {
            cbx.addItem(i);
        }
    }
    
}
