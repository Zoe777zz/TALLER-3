/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistasmodeloTabla;

import javax.swing.table.AbstractTableModel;
import vistas.Casas;

/**
 *
 * @author InnoVausuario
 */
public class MTableCasas extends AbstractTableModel {

    private Casas[] casas;

    public Casas[] getCasa() {
        return casas; // vector colocar datos dentro de una tabla 
    }

    public void setCasa(Casas[] casas) {
        this.casas = casas;
    }

    
    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        if (casas[rowIndex] == null) {
        return null; // O devuelve un valor por defecto
    }
        switch(columnIndex){
            case 0:return "Casa "+(rowIndex+1);
            case 1:return casas[rowIndex].getLargo();
            case 2:return casas[rowIndex].getAncho();
            case 3:return casas[rowIndex].getPisos();
            default : return null; 
        }
    } 
    
    @Override
    public String getColumnName(int i) {
        switch(i){
            case 0: return "  Casa  ";
            case 1: return "  Largo  ";
            case 2: return "  Ancho  ";
            case 3: return "  Pisos  ";
            default : return null; 
        }
    }
}
