/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author ERWIN AURIA
 */
public class FechaNoValidaException extends Exception {
    
    private LocalDateTime fecha;
    
    public FechaNoValidaException(String mensaje, LocalDateTime fecha){
        super(mensaje);
        this.fecha= fecha;     
    }
    
    public FechaNoValidaException(LocalDateTime fecha){
        super("Fecha no valida");
        this.fecha= fecha; 
        
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    
}
