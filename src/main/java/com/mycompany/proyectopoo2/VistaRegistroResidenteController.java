/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import Correo.SendEmailTLS;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import data.ResidentesData;
import data.UsuariosData;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Casa;
import modelo.Residente;
import modelo.Usuario;


/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaRegistroResidenteController implements Initializable {

    @FXML
    private TextField txt_RegistroNombre;
    @FXML
    private TextField txt_RegistroCorreo;
    @FXML
    private TextField txt_RegistroGenero;
    @FXML
    private Button btn_RegistrarResidente;

    private Residente residente;
    private Usuario usuario;
    @FXML
    private TextField txt_RegistroCedula;

    public Residente getResidente() {
        return residente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrarResidente(ActionEvent event)  {

        String nombre = this.txt_RegistroNombre.getText();
        String cedula = this.txt_RegistroCedula.getText();
        String correo = this.txt_RegistroCorreo.getText();
        String genero = this.txt_RegistroGenero.getText();
        //int pin = (int) (Math.random()*10000+1); // Este pin debe ser enviado al correo
        Random rand = new Random();
        int pin = (int)(Math.random()*9000)+1000;
        UsuariosData.setNumUsuarios(UsuariosData.getNumUsuarios()+1);
        String user = "Usuario"+(UsuariosData.getNumUsuarios());
        String password = "1234";

        int coorX = VistaMapaController.coodX;
        int coorY = VistaMapaController.coodY;
        
        
//        Casa casa = (Casa) App.getCasas().stream()
//                .filter(c -> (c.getCoordX() == coorX && c.getCoordY() == coorY));
        residente = new Residente(user, nombre,cedula, correo, genero, pin);
        usuario = new Usuario(user, password, Usuario.Tipo.RESIDENTE);
//        casa.setResidente(residente);
        Casa casa=null;
        for (Casa c : App.getCasas()){
            if (c.getCoordX()==coorX && c.getCoordY()==coorY){  
                System.out.println(c.getCoordX()+" "+c.getCoordY());
                casa = c;                 
            }
        }
        casa.setResidente(residente); 
        App.getCasas().add(casa);

        try {
            App.getResidentes().add(residente);
            App.getUsuarios().add(usuario);            
            UsuariosData.registrarUsuario(usuario);
            ResidentesData.escribirResidentes(App.getResidentes());            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El Registro se Realizo con Exito");
            alert.setWidth(270);
            alert.showAndWait();
            SendEmailTLS.enviarcorreo(correo, "Adjunto sus datos de residente:\nUser: "+user+"\n"+"Password: "+password+"\n"+"Pin de acceso: "+pin,"Datos de Residente");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Se ha enviado un correo con sus datos");
            a.setHeaderText("Correo Enviado");
            a.show();   
            Stage myStage = (Stage) btn_RegistrarResidente.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El Registro no se Realizo con Exito");
            alert.setWidth(270);
            alert.showAndWait();
        }
    }
}
