/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jjmg0
 */
public class SimulacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Simulacion_Residente(ActionEvent event) {
        try {
            App.setRoot("SimulacionResidente");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }    
    }
    
    
    @FXML
    private void Simulacion_Visitante(ActionEvent event) {
        try {
            App.setRoot("SimulacionVisitantes");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }    
    }

    @FXML
    private void volver(ActionEvent event) {
        try {
            App.setRoot("VistaPrincipal");
        } catch (IOException ex) {
            System.out.println("no se pudo cargar la vista");
        }
    }
    
    
}
