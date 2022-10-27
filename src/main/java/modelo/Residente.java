/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author wgcot
 */
public class Residente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cedula;
    private String nombre;
    private String correo;
    private String genero;
    private int pin;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Visitante> visitantes;
    
    public Residente(String usuario,String nombre,String cedula, String correo, String genero, int pin) {
        super(usuario);
        this.nombre = nombre;
        this.cedula= cedula;
        this.correo = correo;
        this.genero = genero;
        this.pin = pin;
        vehiculos = new ArrayList<>();
        visitantes = new ArrayList<>();
    }

    public Residente(String user) {
        super(user);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getGenero() {
        return genero;
    }

    public int getPin() {
        return pin;
    }

    public String getCedula() {
        return cedula;
    }
    

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<Visitante> getVisitantes() {
        return visitantes;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
    

    @Override
    public String toString() {
        return "{"+ nombre + ", " + correo + ", " + cedula + ", " + genero + ", " + pin + ", " + vehiculos + ", " + visitantes +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Residente) {
                Residente residente = (Residente) o;
                return getUser().equals(residente.getUser()) || (cedula.equals(residente.cedula) && pin==residente.pin) || (nombre.equals(residente.nombre));
            }
        } return false;
    }
}
