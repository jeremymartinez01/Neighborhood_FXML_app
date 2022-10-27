/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.Alert;

/**
 *
 * @author ERWIN AURIA
 */
public class StringToDate {
    
    public static LocalDateTime stringToDate(String fecha) throws DateTimeParseException{
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
        dateTime = LocalDateTime.parse(fecha, formatter);
        return dateTime;
    }
    
    public static double diferenciaMinutos(LocalDateTime inicio, LocalDateTime fin){
        return inicio.until(fin, ChronoUnit.MINUTES);
    }
    
    public static boolean enIntervalo(LocalDateTime inicio, 
        LocalDateTime fin, LocalDateTime buscar){
        return (buscar.isAfter(inicio) && buscar.isBefore(fin)) || (buscar.isEqual(inicio)|| buscar.isEqual(fin)) ;
        //
    }
    
}
