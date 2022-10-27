/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modelo.Residente;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author jjmg0
 */
public class IngresoPeatonController implements Initializable {

    @FXML
    private TextField cedula;
    @FXML
    private TextField pin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void volver(ActionEvent event) {
        try {
            App.setRoot("SimulacionResidente");
        } catch (IOException ex) {
            System.out.println("no se pudo cargar la vista");
        }
        
    }

    @FXML
    private void IngresarPeaton(MouseEvent event) {
        ArrayList<Residente> residentes = App.getResidentes();
        String cedula_ingresada = cedula.getText();
        String pin_ingresado = pin.getText();
        if(pin_ingresado.equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor, verifique que ingresó datos numericos");
            a.setHeaderText("Error en formato de pin"+residentes);
            a.show(); 
        }
        try{
            Integer.parseInt(pin_ingresado);
            if (cedula_ingresada.length()!=10){
                Alert a = new Alert(Alert.AlertType.ERROR,"Por favor, verifique que ingresó 10 numeros en la cedula");
                a.setHeaderText("Error en formato de cédula");
                a.show(); 
            }else if(pin_ingresado.length()!=4){
                Alert a = new Alert(Alert.AlertType.ERROR,"Por favor, verifique que ingresó 4 numeros en el pin");
                a.setHeaderText("Error en formato de pin");
                a.show();
            }else{
                Residente r = new Residente("","",cedula_ingresada,"","",Integer.parseInt(pin_ingresado));
                if(residentes.contains(r)){
                Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Bienvenido(a) "+obtener_Propietario(cedula_ingresada));
                a.setHeaderText("Acceso Concedido");
                a.show();      
                }else{
                    Alert a = new Alert(Alert.AlertType.ERROR,"Los datos ingresados no le pertenecen a ningún residente");
                    a.setHeaderText("Acceso Denegado");
                    a.show();
                }
            }
        }catch (NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor, verifique que todos los datos que ingresó sean numericos");
            a.setHeaderText("Error en formato de pin");
            a.show();
        }
    }
    private String obtener_Propietario(String cedula2) {
        ArrayList<Residente> residentes = App.getResidentes();
        for(Residente r: residentes){
            if(r.getCedula().equals(cedula2)){
                return r.getNombre();
            }
        }
        return null;
    }
}
