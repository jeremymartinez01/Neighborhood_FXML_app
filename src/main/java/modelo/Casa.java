/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
// las coordenadas de la casa deberian ser double o si no al cambiar el layout, no olvidar hacer el casting

/**
 * @author wgcot
 */
public class Casa implements Serializable {

    private static final long serialVersionUID = 1L;
    private Residente residente;
    private int coordX;
    private int coordY;
    private Manzana manzana;
    private int villa;
    

    public Casa() {
    }

    public Casa(Residente residente, int coordX, int coordY, Manzana manzana, int villa) {
        this.residente = residente;
        this.coordY = coordY;
        this.coordX = coordX;
        this.manzana = manzana;
        this.villa = villa;
    }

    public Casa(int coordX, int coordY, Manzana manzana, int villa) {
        this.residente = null;
        this.coordY = coordY;
        this.coordX = coordX;
        this.manzana = manzana;
        this.villa = villa;
    }
    
    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public Residente getResidente(){
        return residente;
    }
    
    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public Manzana getManzana() {
        return manzana;
    }

    public void setManzana(Manzana manzana) {
        this.manzana = manzana;
    }

    public int getVilla() {
        return villa;
    }

    public void setVilla(int villa) {
        this.villa = villa;
    }
    

    public enum Manzana {
        GRIFFINDOR, HUFFLEPUFF, RAVENCLAW, SLYTHERIN
    }

    @Override
    public String toString() {
        return residente.getNombre()+", " +coordX +", " +coordY +", " +manzana +", " + villa;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Casa) {
                Casa c = (Casa) o;
                return manzana.equals(c.manzana) && villa==c.villa;
            }
        } return false;
    }
}
