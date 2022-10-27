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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modelo.Residente;

/**
 * FXML Controller class
 *
 * @author jjmg0
 */
public class CambiarPinController implements Initializable {

    @FXML
    private Button btn_guardar;
    @FXML
    private TextField old_pin;
    @FXML
    private TextField new_pin;
    @FXML
    private TextField txt_Confirmar;
    @FXML
    private Button btn_Regresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GuardarPin(MouseEvent event) throws IOException {
        ArrayList<String> errores = new ArrayList<String>();
        int pin_old=0;
        int pin_nuevo=0;
        int pin_confirmacion=0;
        try{
            pin_old = Integer.parseInt(old_pin.getText());
        }catch(NumberFormatException ex){
            errores.add("Pin Actual debe ser valor entero");
        }
        
        try{
            pin_nuevo = Integer.parseInt(new_pin.getText());
        }catch(NumberFormatException ex){
            errores.add("Pin Nuevo debe ser valor entero");
        }
        
        try{
            pin_confirmacion = Integer.parseInt(txt_Confirmar.getText());
        }catch(NumberFormatException ex){
            errores.add("Pin de Confirmación debe ser valor entero");
        }
        
        if(errores.isEmpty()){
           Residente r = VistaResidenteController.getLogg_Residente();   
            if(r.getPin()==pin_old){
               if(pin_nuevo==pin_confirmacion){
                   r.setPin(pin_nuevo);
                   SendEmailTLS.enviarcorreo(r.getCorreo(), "Su pin nuevo es " + pin_confirmacion, "CAMBIO DE PIN");
                   ResidentesData.agregarResidentes(r);
                   ResidentesData.escribirResidentes(App.getResidentes());
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setHeaderText("Cambio Exitoso");
                   alert.show();
                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Pin de confirmación incorrecto");
                   alert.show();
               }      
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Pin actual incorrecto");
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
    
    public void limpiar(){
        old_pin.setText("");
        new_pin.setText("");
    }

    @FXML
    private void regresarInfo(MouseEvent event) {
        try {
          App.setRoot("VistaInformacion");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar a Vista Informacion");
        }
    }
    
}
