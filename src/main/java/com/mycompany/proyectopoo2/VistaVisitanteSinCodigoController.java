/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import Correo.SendEmailTLS;
import data.ResidentesData;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modelo.Casa;
import modelo.Casa.Manzana;
import modelo.Residente;
import modelo.Visitante;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class VistaVisitanteSinCodigoController implements Initializable {

    @FXML
    private TextField txt_Nombre;
    @FXML
    private TextField txt_Cedula;
    @FXML
    private TextField txt_Residente;
    @FXML
    private TextField txt_Villa;
    @FXML
    private TextField txt_Manzana;
    @FXML
    private TextField txt_Correo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void nuevoVisitante(MouseEvent event) throws IOException{
        
        ArrayList<String> errores= new ArrayList<> ();
        String nombreVisitante= txt_Nombre.getText();
        String cedula= txt_Cedula.getText();
        String correo= txt_Correo.getText();
        String nombreResidente= txt_Residente.getText();
        String villa= txt_Villa.getText();
        String manzana= txt_Manzana.getText().toUpperCase();
        Random rand = new Random();
        int pin = (int)(Math.random()*9000)+1000;
        
        if(nombreVisitante.equals("")|nombreVisitante.equals(" ")){
            errores.add("Ingrese su nombre");      
        }
        if(cedula.equals("")|cedula.equals(" ")){
            errores.add("Ingrese su cédula");      
        }
        if(correo.equals("")|correo.equals(" ")){
            errores.add("Ingrese su correo");      
        }
        if(nombreResidente.equals("")|nombreResidente.equals(" ")){
            errores.add("Ingrese el nombre del residente");      
        }
        if(villa.equals("")| villa.equals(" ")){
            errores.add("Ingrese la villa del residente");      
        }
        if(manzana.equals("")| manzana.equals(" ")){
            errores.add("Ingrese la manzana del residente");      
        }
        if(errores.isEmpty()){
            ArrayList<Casa> casas= App.getCasas();
            Casa c1= new Casa();
            Manzana nuevaManzana= Manzana.valueOf(manzana);
            c1.setManzana(nuevaManzana);
            c1.setVilla(Integer.valueOf(villa));
            //Residente r= new Residente("",nombreResidente,"","","",0);
            LocalDateTime fechaHoy= LocalDateTime.now();
            Visitante nuevo_Visitante= new Visitante(nombreVisitante,cedula,correo,fechaHoy);
            nuevo_Visitante.setEstado(Visitante.Estado.AFUERA);
            
            if(casas.contains(c1)){
                
                String correoResidente= buscarResidente(nombreResidente);
                nuevo_Visitante.setPin(Integer.toString(pin));
                String datos = "Los datos para su visita son:\nNombre del Vsistante: "+nombreVisitante
                            +"\nCedula del Visitante: "+ cedula + "\n Pin: "+pin+"\n Correo: " + 
                            correo + "\nSi quiere dar acceso a este visitante, envie el pin a este correo";
            
                SendEmailTLS.enviarcorreo(correoResidente, datos,"Solicitud de ingreso de visitante"); 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Información enviada exitosamente");
                alert.show();
                ingresarNuevoVisitante(nuevo_Visitante);
                limpiar();
                actualizarVista();
                                  
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION );
                alert.setContentText("No existe un residente con los datos ingresados");
                alert.show();
            }
                     
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            String texto="";
            for(String x : errores){
                texto+=x+"\n";
            }
            alert.setContentText(texto);
            alert.show();
        }        
    }
    
    private String buscarResidente(String nombre){
        ArrayList<Residente> residentes= App.getResidentes();
        
        for(Residente r:residentes){
           if(r.getNombre().equals(nombre)){
               return r.getCorreo();
           } 
        }
        return null;
    }
    
    private String buscarCorreo(Residente r){
      
        ArrayList<Residente> resi= App.getResidentes();
        for(Residente c: resi){
            if(c.getNombre().equals(r.getNombre()))
              return c.getCorreo();
        }
        return null;
    }
    

    private void actualizarVista(){
        try {
            App.setRoot("VistaVisitanteConCodigo");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }
    }
    
    
    private void limpiar(){
        txt_Nombre.setText("");
        txt_Cedula.setText("");
        txt_Correo.setText("");
        txt_Residente.setText("");
        txt_Villa.setText("");
        txt_Manzana.setText("");
        
    }
    
    
    private void ingresarNuevoVisitante(Visitante v) throws IOException{
        Residente r = VistaResidenteController.getLogg_Residente();  
        r.getVisitantes().add(v);
        ResidentesData.agregarResidentes(r);
        ResidentesData.escribirResidentes(App.getResidentes());    
    }

    @FXML
    private void Salir(ActionEvent event) {
        try {
            App.setRoot("Simulacion");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }
    }
    
}
