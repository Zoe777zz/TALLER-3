/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

/**
 *
 * @author InnoVausuario
 */
public class Casas {
    private Float Ancho; //ATRIBUTOS
    private Float Largo;
    private Integer Pisos;

    public Float getAncho() {
        return Ancho;
    }

    public void setAncho(Float Ancho) {
        this.Ancho = Ancho;
    }

    public Float getLargo() {
        return Largo;
    }

    public void setLargo(Float Largo) {
        this.Largo = Largo;
    }

    public Integer getPisos() {
        return Pisos;
    }

    public void setPisos(Integer Pisos) {
        this.Pisos = Pisos;
    }
    public Casas(Float ancho, Float largo, Integer piso){
        this.Ancho=ancho;
        this.Largo=largo; // constructor llamamos al objeto clase y ya le damos valores 
        this.Pisos=piso;
    }
    
    
    
    
    
    
    
}
