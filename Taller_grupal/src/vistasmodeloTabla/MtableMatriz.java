/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistasmodeloTabla;

import javax.swing.table.AbstractTableModel;
import vistas.Empresa;
import vistas.Matriz;

/**
 *
 * @author InnoVausuario
 */
public class MtableMatriz extends AbstractTableModel{

    private Matriz matriz; 

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }
    @Override
    public int getRowCount() { // obten el numero de filas
        return matriz.getMtz().length;
    }

    @Override
    public int getColumnCount() {
        return matriz.getMtz()[0].length;
    }

    @Override
    public Object getValueAt(int m, int n) {
        return matriz.getMtz()[m][n];
        
    }
    
}
