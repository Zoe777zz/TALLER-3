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
public class Matriz {

    private Integer[][] Mtz;

    // crear la matriz 
    public void crear(Integer m, Integer n) { // m fila - n columna 
        this.Mtz = new Integer[m][n];
    }

    public Boolean modificar(Integer m, Integer n, Integer dato) {
        if (Mtz[m][n] != null) { // 
            Mtz[m][n] = dato;
            return true;
        }
        return false;
    }
    public static void cargaM(JComboBox cbx){
        cbx.removeAllItems();
        for (int i= 1; i <=20; i ++) {
            cbx.addItem(i);
        }
    }
    public Integer[] verificar() {
        Integer[] pos = new Integer[2]; // dos coordenadas - un arreglo 2 mxn
        for (int i = 0; i < Mtz.length; i++) { // . length todo el tamaño de esa fila
            for (int j = 0; j < Mtz[0].length; j++) {
                if (Mtz[i][j] == null) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    public Boolean guardar(Integer dato) {
        Integer pos[] = verificar();
        if (pos != null) { // este
            Mtz[pos[0]][pos[1]] = dato;

            return true;
        }
        return false;
    }

    public void generarMatrizAleatorio() {
        int arreglo[] = new int[Mtz.length * Mtz[0].length]; // se multiplica la matriz Mtz[0].length columnas // Mtz.length filas
        boolean band;
        int aux = 0;
        for (int i = 0; i < arreglo.length; i++) {
            do {
                band = false;
                for (int j = 0; j < i; j++) {

                    aux = aleatorio(1, (Mtz.length * Mtz[0].length) * 2);
                    if (arreglo[j] == aux) {
                        band = true;
                        break;
                    }
                }
            } while (band);
            arreglo[i] = aux;

        }
        int cont = 0;
        for (int i = 0; i < Mtz.length; i++) {

            for (int j = 0; j < Mtz[0].length; j++) {
                Mtz[i][j] = arreglo[cont]; // sumando 
                cont++;
            }
        }

    }

    public void ElimnarPar() {
        for (int i = 0; i < Mtz.length; i++) {
            for (int j = 0; j < Mtz[0].length; j++) {
                if (Mtz[i][j] != null && (Mtz[i][j] % 2 == 0)) {
                    Mtz[i][j] = null;
                }

            }

        }
    }

    public void ElimnarImpar() {
        for (int i = 0; i < Mtz.length; i++) {
            for (int j = 0; j < Mtz[0].length; j++) {
                if (Mtz[i][j] != null && (Mtz[i][j] % 2 != 0)) { // comparo si es nulo
                    Mtz[i][j] = null;
                }

            }

        }
    }

    public void EliminarPrimo() {
        for (int i = 0; i < Mtz.length; i++) {
            for (int j = 0; j < Mtz[0].length; j++) {
                if (Mtz[i][j] != null && esPrimo(Mtz[i][j])) { // comparo si es nulo
                    Mtz[i][j] = null;
                }

            }

        }
    }

    public void ElimnarMultiplo(int multiplo) {
        for (int i = 0; i < Mtz.length; i++) {
            for (int j = 0; j < Mtz[0].length; j++) {
                if (Mtz[i][j] != null && (Mtz[i][j] % multiplo == 0)) { // comparo si es nulo// divido para ese multiplo
                    Mtz[i][j] = null;
                }

            }

        }
    }

    public Boolean esPrimo(int numero) {

        if (numero <= 1) {
            return false;
        }
        Boolean band = true;
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                band = false;
                break;
            }
        }
        return band;
    }

    public static int aleatorio(int min, int max) {
        double aux;
        aux = Math.random() / Math.nextDown(1.0);
        return ((int) (min * (1.0 - aux) + max * aux));

    }

    public Integer[][] getMtz() {
        return Mtz;
    }

    public void setMtz(Integer[][] Mtz) {
        this.Mtz = Mtz;
    }

   

}
