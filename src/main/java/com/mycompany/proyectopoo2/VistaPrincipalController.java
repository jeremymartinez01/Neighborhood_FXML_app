/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaPrincipalController implements Initializable {

    @FXML
    private Button btn_InicioSesion;
    @FXML
    private Button btn_InicioSimulacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        try {
            App.setRoot("VistaInicioDeSesion");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }
    }

    @FXML
    private void simulacion(MouseEvent event) {
        try {
            App.setRoot("Simulacion");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }
        
    }

}
