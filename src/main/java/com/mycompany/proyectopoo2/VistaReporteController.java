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
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaReporteController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrarMapa(ActionEvent event) {
        try {
            App.setRoot("VistaMapa");
        } catch (IOException ex) {}
    }

    @FXML
    private void salir(ActionEvent event) {
        try {
            App.setRoot("VistaPrincipal");
        } catch (IOException ex) {}
    }
    
}
