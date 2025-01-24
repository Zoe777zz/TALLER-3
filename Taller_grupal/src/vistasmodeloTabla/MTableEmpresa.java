/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistasmodeloTabla;
import javax.swing.table.AbstractTableModel;
import vistas.Empresa;

/**
 *
 * @author InnoVausuario
 */
// herencia 
//abstract == implementar metodos 
public class MTableEmpresa extends AbstractTableModel {
    public enum Meses{ENERO,FEBRERO,MARZO,ABRIL,MAYO,JUNIO,JULIO,AGOSTO,SEPTIEMBRE,OCTUBRE,NOVIEMBRE,DICIEMBRE 
}
    
    private Empresa empresa; 

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
    @Override
    public int getRowCount() { // obten el numero de filas
        return 12;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch(i1){
            case 0: return Meses.values()[i].name();
            case 1:return empresa.getDatos()[0][i];
            case 2: return empresa.getDatos()[1][i];
            default : return null; 
        }
    }
    @Override
    public String getColumnName(int i) {
        switch(i){
            case 0:return "MESES";
            case 1:return "VENTAS";
            case 2: return "GASTOS";
            default : return null; 
        }
    }

    
}
