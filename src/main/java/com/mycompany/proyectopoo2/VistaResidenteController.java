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
import modelo.Residente;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaResidenteController implements Initializable {
    
    
    private static Residente residente;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrarInformacion(ActionEvent event) {
        try {
            App.setRoot("VistaInformacion");
        } catch (IOException ex) {
            ex.printStackTrace();;
        }
    }

    @FXML
    private void mostrarVehiculos(ActionEvent event) {
        try {
            App.setRoot("VistaVehiculos");
        } catch (IOException ex) {
            ex.printStackTrace();;

        }
    }

    @FXML
    private void mostrarVisitas(ActionEvent event) {
        try {
            App.setRoot("VistaVisitas");
        } catch (IOException ex) {
            ex.printStackTrace();;

        }
    }

    @FXML
    private void Salir(ActionEvent event) {
        try {
            App.setRoot("VistaPrincipal");
        } catch (IOException ex) {
            ex.printStackTrace();;
                
        }
    }
    
    public static Residente getLogg_Residente() {
        ArrayList<Residente> residentes = App.getResidentes();
        String logg_user=VistaInicioDeSesionController.logg_user;
        for(Residente r: residentes){
            if(r.getUser().equals(logg_user)){
                return r;
            }
        }
        return null;
    }
    
}
