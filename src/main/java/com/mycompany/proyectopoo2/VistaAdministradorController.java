/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import static com.mycompany.proyectopoo2.App.loadFXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaAdministradorController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }       

    @FXML
    private void mostrarMapa(ActionEvent event) {
        try {
            App.setRoot("VistaMapa");
        } catch (IOException ex) {}
    }

    @FXML
    private void mostrarReporte(ActionEvent event) {
        try {
            App.setRoot("VistaReporte");
        } catch (IOException ex) {}
    }   

    @FXML
    private void salir(ActionEvent event) {
        try {
            App.setRoot("VistaPrincipal");
        } catch (IOException ex) {}
    }

}
