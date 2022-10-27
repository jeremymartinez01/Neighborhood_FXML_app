/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import data.ResidentesData;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import modelo.Casa;
import modelo.Residente;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaInformacionController implements Initializable {

    @FXML
    private TextField tfield_Nombre;
    @FXML
    private TextField tfield_Correo;
    @FXML
    private TextField tfield_Manzana;
    @FXML
    private TextField tfield_Villa;
    @FXML
    private TextField tfield_Pin;
    @FXML
    private ColumnConstraints img_Residente;
    @FXML
    private VBox vbox_imagen;
    @FXML
    private CheckBox check_Activar;
    @FXML
    private Button btn_CmbiarPin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String ruta = "Images/"+rutaImagen();
        try{
            ImageView imgv = new ImageView( new Image(App.class.getResourceAsStream(ruta),250,250,true,true) );
            vbox_imagen.getChildren().add(imgv);
        } catch(NullPointerException n){
            System.out.println("imagen no encontrada");
        }
        //vbox_imagen.getChildren().add(imgv);
        
        String user_residente = VistaInicioDeSesionController.logg_user;
        ArrayList<Residente> residentes = App.getResidentes();
        ArrayList<Casa> houses = App.getCasas();
            for (Residente r:residentes){
                if(r.getUser().equals(user_residente)){
                    String nombre = r.getNombre();
                    tfield_Nombre.setText(nombre);
                    tfield_Correo.setText(r.getCorreo());
                    for (Casa c : houses){
                        if(c.getResidente()!= null){
                                if(c.getResidente().getNombre().equals(nombre)){
                                    tfield_Manzana.setText(c.getManzana().name());
                                    tfield_Villa.setText(Integer.toString(c.getVilla()));
                                }
                        }
                    }
                    tfield_Pin.setText(Integer.toString(r.getPin()));
                }
            }               
    }    

    @FXML
    private void mostrarInformacion(ActionEvent event) {
        try {
            App.setRoot("VistaInformacion");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar la vista");
        }
    }

    @FXML
    private void mostrarVehiculos(ActionEvent event) {
        try {
            App.setRoot("VistaVehiculos");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar la vista");
        }
    }

    @FXML
    private void mostrarVisitas(ActionEvent event) {
        try {
            App.setRoot("VistaVisitas");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar la vista");
        }
    }

    @FXML
    private void Salir(ActionEvent event) {
        try {
            App.setRoot("VistaPrincipal");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar la vista");
        }
    }
    
    @FXML
    private void activarCambiarPin(ActionEvent event){
        btn_CmbiarPin.setDisable(false);
    }

    @FXML
    private void cambiarPin(MouseEvent event) {
        try {
            App.setRoot("CambiarPin");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar la vista");
        }
        
    }

    public static String rutaImagen(){
        String ruta="";
        ArrayList<Residente> residentes = App.getResidentes();
        ArrayList<Casa> houses = App.getCasas();
        String user_residente = VistaInicioDeSesionController.logg_user;
        
        for (Residente r:residentes){
                if(r.getUser().equals(user_residente)){
                    String nombre = r.getNombre();
                    for (Casa c : houses){
                        if(c.getResidente()!= null){
                                if(c.getResidente().getNombre().equals(nombre)){
                                    String manzana = c.getManzana().name();
                                    if(manzana.equals("GRIFFINDOR")){
                                        ruta="g.png";
                                    }else if(manzana.equals("HUFFLEPUFF")){
                                        ruta="h.png";
                                    }else if(manzana.equals("RAVENCLAW")){
                                        ruta="r.png";
                                    }else if(manzana.equals("SLYTHERIN")){
                                        ruta="s.png";
                                    }
                                }
                        }
                    }
                }
        }        
        return ruta;
    }

}
    

