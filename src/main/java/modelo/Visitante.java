/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.beans.property.SimpleStringProperty;
import javax.mail.internet.ParseException;



/**
 *
 * @author ERWIN AURIA
 */
public class Visitante implements Serializable  {
    private String nombre;
    private String ci;
    private String correo;
    private LocalDateTime fecha;
    private String pin;
    private Estado estado;

    public Visitante(String nombre, String ci,String correo, LocalDateTime fecha) {
        this.nombre = nombre;
        this.ci = ci;
        this.correo = correo;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCi() {
        return ci;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getPin() {
        return pin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    public String formatoFecha(LocalDateTime fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm");
        String fechaFormateada = fecha.format(formatter); 
        return fechaFormateada;
    }
    
    
    @Override
    public String toString() {
        return "{"+ nombre + ", " + ci + ", " + correo + ", " + formatoFecha(fecha) + ", " + pin + ", " +estado +'}';
    }
    
    public enum Estado{
        AFUERA, DENTRO
    }
    
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Visitante) {
                Visitante visitante = (Visitante) o;
                return pin.equals(visitante.pin);
            }
        } return false;
    }
    

    
    
}
