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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jjmg0
 */
public class SimulacionResidenteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Salir(ActionEvent event) {
        try {
            App.setRoot("Simulacion");
        } catch (IOException ex) {
            System.out.println("no se pudo cargar la vista");
        }
    }

    @FXML
    private void Peaton(MouseEvent event) {
        try {
            App.setRoot("IngresoPeaton");
        } catch (IOException ex) {
            System.out.println("no se pudo cargar la vista");
        }
    }

    @FXML
    private void Vehiculo(MouseEvent event) {
        try {
            App.setRoot("IngresoVehiculo");
        } catch (IOException ex) {
            System.out.println("no se pudo cargar la vista");
        }
    }
    
}
