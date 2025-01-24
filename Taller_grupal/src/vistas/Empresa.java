/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JComboBox;

/**
 *
 * @author InnoVausuario
 */
public class Empresa {
    // arreglo para guardar ventas mensuales totales y los gastos realizadas por mes, 
    private Float [][] datos;
    // crear 
    public void crear (){
        this.datos = new Float [2][12];
    
    
    }
    
    // para guardar registro del anio
    public static void cargarAnios(JComboBox cbx){
        cbx.removeAllItems();
        for (int i= 2000; i <=2024; i ++) {
            cbx.addItem(i);
        }
    }
    // Para obtener el promedio 
     public Float promedio (){ 
        Float promedio = 0.0f;
        Float cont =0.0f;
        
        for (int i=0 ; i< 12 ;i++){ // i = 12 mese 
            if ( datos [0][i] != null){// dato en la posicion 0 y recorre las ganacias 
                cont ++;
                promedio +=datos[0][i]; // acumula      
            }
        }
        return promedio/cont;
    }
     // Total de gancias y total de gastos 
    public Float total (Integer numero ){
        Float total = 0.0f; 
        
        for (int i=0 ; i< 12 ;i++){ // i = 12 mese 
            if ( datos [numero][i] != null){// dato segun el "numero" 0 = ganancias 1 = gastos 
                total +=datos[numero][i]; // acumula      
            }
        }
        return total; 

    }
    // El mes y la venta total más alta alcanzada en el año, así como los gastos.
    public Integer mayor (Integer numero, Integer tipo ){
        Float aux =  datos [0][0];
        Integer posicion =0;
        int cont=1;
        while(cont<12&&datos[tipo][cont]!=null){
             if (aux < datos[tipo][cont]) {
                 aux=datos[tipo][cont];
                posicion = cont;
            }
             cont++;
        }
        return posicion; 

    }
    //El mes y la venta total más baja alcanzada en el año, así como los gastos.
    public Integer menor(Integer numero,Integer tipo) {
        Float aux = datos[tipo][0];
        Integer posicion = 0;
        int cont=1;
        while(cont<12&&datos[tipo][cont]!=null){
             if (aux > datos[tipo][cont]) {
                 aux=datos[tipo][cont];
                posicion = cont;
            }
             cont++;
        }
        return posicion;
    }

    
    void cargaranios(JComboBox<String> cbxnumero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Integer verificar (){
        Integer pos = -1;
        for (int i =0;i< datos[0].length; i++){ // . length todo el tamaño de esa fila
            if (datos [0][i] == null){ // este
                pos = i;
                break;}
        }
        return pos; 
    } 
    // 0 = ventas
    // 1 = gastos
    public  Boolean guardar ( Float venta , Float gasto){
        int pos = verificar ();
        if (pos >= 0 && pos < datos[0].length){ // este
            datos[0][pos] = venta;
            datos [1][pos]= gasto;
            return true;
        }
        return false;
        
    }
    public  Boolean modificar ( Float venta, Float gasto, Integer pos){
        if (pos >= 0 && pos < datos[0].length){ // este
            datos[0][pos] = venta;
            datos [1][pos]= gasto;
            return true;
        }
        return false;
        
    }
    public Boolean generar_Archivo(int anio) {
        if (datos != null) {
            String pathInforme = "src" + File.separatorChar + "data" + File.separatorChar + "empresa" + File.separatorChar + anio + ".txt";
            try {
                FileWriter file_registro = new FileWriter(pathInforme);
                for (int i = 0; i < datos[0].length; i++) {
                    if (datos[0][i] != null) {
                        file_registro.write(datos[0][i].toString() + "\t" + datos[1][i].toString() + "\n");
                        file_registro.flush();
                    }
                }
                file_registro.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public Boolean cargarArchivo(int anio) {
        String pathAnios = "src" + File.separatorChar + "data" + File.separatorChar + "empresa" + File.separatorChar + anio + ".txt";
        try {

            FileReader fre = new FileReader(pathAnios);
            BufferedReader file_datos = new BufferedReader(fre);
            fre.close();
            file_datos.close();

            fre = new FileReader(pathAnios);
            file_datos = new BufferedReader(fre);
            
            String linea;
            int contador = 0;
            while ((linea = file_datos.readLine()) != null) {
                String[] dato=linea.split("\t");
                datos[0][contador]=Float.parseFloat(dato[0]);
                datos[1][contador]=Float.parseFloat(dato[1]);
                contador++;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Float[][] getDatos() {
        return datos;
    }

    public void setDatos(Float[][] datos) {
        this.datos = datos;
    }
    
    
    
    
    
    
    
}
