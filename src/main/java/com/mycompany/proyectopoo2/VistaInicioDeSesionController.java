/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import data.UsuariosData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modelo.Usuario;
import modelo.Usuario.Tipo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static data.UsuariosData.*;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaInicioDeSesionController implements Initializable {

    @FXML
    private TextField lbl_LoginUsername;
    @FXML
    private PasswordField lbl_LoginPassword;
    @FXML
    private Button btn_Login;
    
    public static String logg_user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void logIn(MouseEvent event) throws IOException {
        String user = lbl_LoginUsername.getText();
        String password = lbl_LoginPassword.getText();
        Usuario usuario = new Usuario(user, password);
        ArrayList<Usuario> usuarios = App.getUsuarios();
        if (usuarios.contains(usuario)) {
            try {
                for (Usuario u : usuarios) {
                    if (u.equals(usuario)) {
                        //setLogg_user(user);
                        Tipo tipo = u.getTipo();
                        if (tipo.equals(Tipo.ADMINISTRADOR)) {
                            App.setRoot("VistaAdministrador");
                        } else {
                            setLogg_user(user);
                            App.setRoot("VistaResidente");
                        }
                    }
                }

            } catch (IOException ex) {}
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Credenciales Incorrectas");
            alert.show();
        }
        
    }

    public static void setLogg_user(String logg_user) {
        VistaInicioDeSesionController.logg_user = logg_user;
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
