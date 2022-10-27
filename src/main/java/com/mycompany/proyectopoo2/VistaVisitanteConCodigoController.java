/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import data.ResidentesData;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modelo.Residente;
import modelo.StringToDate;
import modelo.Visitante;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class VistaVisitanteConCodigoController implements Initializable{

    @FXML
    private TextField codigoAcceso;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ingresar(ActionEvent event) throws IOException {
       ArrayList<Visitante> visitantes= VistaVisitasController.obtener_ListaVisitante();
        String codigoA= codigoAcceso.getText();
        LocalDateTime fechaFin= LocalDateTime.now();
        if (codigoA.length()!=4){
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor, verifique que ingresó 4 caracteres");
            a.show(); 
        }else{
            //LocalDateTime fechaInicio = obtener_FechaInicio(codigoA);
            Visitante v= new Visitante("","","",fechaFin);
            v.setPin(codigoA);
            if(visitantes.contains(v)){
                LocalDateTime fechaInicio = obtener_FechaInicio(codigoA);
                LocalDateTime nuevaFechaInicio= fechaInicio.minusMinutes(5);
                LocalDateTime nuevaFechaFin= fechaInicio.plusMinutes(5);
                
                if((StringToDate.diferenciaMinutos(nuevaFechaInicio, fechaFin)>=0.0 && StringToDate.diferenciaMinutos(nuevaFechaInicio, fechaFin)<=5.0) || (StringToDate.diferenciaMinutos(fechaFin, nuevaFechaFin)>=0.0 && StringToDate.diferenciaMinutos(fechaFin, nuevaFechaFin)<=5.0) ){
                    if(obtenerEstado(codigoA)){
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Bienvenido(a) "+obtener_NombreVisitante(codigoA));
                        a.setHeaderText("Acceso Concedido");
                        a.show(); 
                        cambiarEstado(obtenerVisitante(codigoA));
                    }else{
                        Alert a = new Alert(Alert.AlertType.INFORMATION,"Este pin ya fue usado");
                        a.setHeaderText("Acceso Denegado");
                        a.show();     
                    }
                    
                }else{
                    Alert a = new Alert(Alert.AlertType.ERROR,"Este pin no es válido" + StringToDate.diferenciaMinutos(nuevaFechaFin, fechaFin));
                    a.setHeaderText("Acceso Denegado");
                    a.show();                    
                }
                                
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR,"Este pin no está registrado");
                a.setHeaderText("Acceso Denegado");
                a.show();
            }
        }     
    }
    
    
    private String obtener_NombreVisitante(String pin) {
        ArrayList<Visitante> visitantes = VistaVisitasController.obtener_ListaVisitante();
        for(Visitante v: visitantes){
            if(v.getPin().equals(pin)){
                return v.getNombre();
            }
        }
        return null;
    }
    
    private LocalDateTime obtener_FechaInicio(String pin){ 
        ArrayList<Visitante> visitantes = VistaVisitasController.obtener_ListaVisitante();
        for(Visitante v: visitantes){
            if(v.getPin().equals(pin)){
                return v.getFecha();
            }
        }
        return null;
    }
    
    private Visitante obtenerVisitante(String pin){
        
        ArrayList<Visitante> visitantes = VistaVisitasController.obtener_ListaVisitante();
        for(Visitante v: visitantes){
            if(v.getPin().equals(pin)){
                return v;
            }
        }
        return null;
    }
        
    private void cambiarEstado(Visitante v) throws IOException{
        Residente r = VistaResidenteController.getLogg_Residente();  
        if(r.getVisitantes().contains(v)){
            v.setEstado(Visitante.Estado.DENTRO);
            ResidentesData.agregarResidentes(r);
            ResidentesData.escribirResidentes(App.getResidentes());    
        }        
    }
    
    private boolean obtenerEstado(String pin){
        ArrayList<Visitante> visitantes = VistaVisitasController.obtener_ListaVisitante();
        for(Visitante visi: visitantes){
            if(visi.getPin().equals(pin)){
                if(!visi.getEstado().equals(Visitante.Estado.DENTRO)){
                    return true;
                }       
            }
        }
        return false;
    }

    @FXML
    private void salir(ActionEvent event) {
        try {
            App.setRoot("Simulacion");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }
    }


}
