/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import Correo.SendEmailTLS;
import static com.mycompany.proyectopoo2.VistaInformacionController.rutaImagen;
import data.ResidentesData;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.mail.internet.ParseException;
import modelo.Residente;
import modelo.StringToDate;
import modelo.Visitante;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class VistaRegistrarVisitaController implements Initializable {

    @FXML
    private TextField txt_Nombre;
    @FXML
    private TextField txt_Cedula;
    @FXML
    private TextField txt_Correo;
    @FXML
    private TextField txt_FechaIngreso;
    @FXML
    private Button btn_Registrar;
    private VBox vBox_Image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String r= VistaInformacionController.rutaImagen();
        String ruta = "Images/"+r;
        try{
            ImageView imgv = new ImageView( new Image(App.class.getResourceAsStream(ruta),250,250,true,true) );
            vBox_Image.getChildren().add(imgv);
        } catch(NullPointerException n){
            System.out.println("Imagen no encontrada");
        }
        
    }    
    
    @FXML
    public void registrar() 
        throws IOException{
        
        ArrayList<String> errores = new ArrayList<String>();
        String nombre= txt_Nombre.getText();
        String cedula= txt_Cedula.getText(); 
        String correo= txt_Correo.getText();
        String fecha= txt_FechaIngreso.getText();
        Random rand = new Random();
        int pin = (int)(Math.random()*9000)+1000;
        
        if(nombre.equals("")|nombre.equals(" ")){
            errores.add("Ingrese el nombre del visitante");      
        }
        if(cedula.equals("")|cedula.equals(" ")){
            errores.add("Ingrese la cedula del visitante");      
        }
        if(correo.equals("")|correo.equals(" ")){
            errores.add("Ingrese el correo del visitante");      
        }
        if(fecha.equals("")| fecha.equals(" ")){
            errores.add("Ingrese la fecha de ingreso del visitante");      
        }
        if(errores.isEmpty()){
            try{
                
                LocalDateTime dateTime= StringToDate.stringToDate(fecha);
                Visitante v= new Visitante(nombre,cedula,correo,dateTime);
                v.setEstado(Visitante.Estado.AFUERA);
                v.setPin(Integer.toString(pin));
                String datos = "Los datos para su visita son:\nPin: "+pin;
                SendEmailTLS.enviarcorreo(correo, datos,"Código de acceso");
                ingresarVisitante(v);
                limpiar();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Registro Exitoso");
                alert.show();
            }catch(DateTimeParseException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Fecha en el formato incorrecto");
                alert.setContentText("Ingrese la fecha con el siguiente formato:\nDD/MM/AAAA HH:MM");
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
    
    
    
    private void limpiar(){
        txt_Nombre.setText("");
        txt_Cedula.setText("");
        txt_Correo.setText("");
        txt_FechaIngreso.setText("");
    }
    
    private void ingresarVisitante(Visitante v) throws IOException{
        Residente r = VistaResidenteController.getLogg_Residente();  
        r.getVisitantes().add(v);
        ResidentesData.agregarResidentes(r);
        ResidentesData.escribirResidentes(App.getResidentes());    
    }
    
    @FXML
    private void regresar(MouseEvent event){
        try {
            App.setRoot("VistaVisitas");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar la vista");
        }
    }
     
}
