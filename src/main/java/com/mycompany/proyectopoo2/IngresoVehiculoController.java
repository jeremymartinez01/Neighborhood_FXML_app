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
public class IngresoVehiculoController implements Initializable {

    @FXML
    private TextField matricula;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    @FXML
    private void Salir(ActionEvent event) {
        try {
            App.setRoot("SimulacionResidente");
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la vista");
        }
    }
  
    @FXML
    private void Ingresando_Vehiculo(MouseEvent event) {
        
        String matricula_ingresada = matricula.getText();
        Vehiculo vehiculo = new Vehiculo("", matricula_ingresada);
        String [] lista = matricula_ingresada.split("-");
        if( (lista[0].length()!=3) || (lista[1].length()!=4 )){
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor, ingresar 3 letras, luego - y por ultimo 4 números");
            a.setHeaderText("Error en formato de matricula");
            a.show();   
        } else{
            if(obtener_ListaVehiculo().contains(vehiculo)){
                Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Bienvenido(a) "+obtener_Propietario(matricula_ingresada));
                a.setHeaderText("Acceso Concedido");
                a.show();       
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR,"La matricula ingresada no se encuentra registrada para ningún residente");
                a.setHeaderText("Acceso Denegado");
                a.show();
            }
             
        }
    }
   
    
    private ArrayList<Vehiculo> obtener_ListaVehiculo() {
        ArrayList<Residente> residentes = App.getResidentes();
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        for(Residente r: residentes){
            for(Vehiculo v: r.getVehiculos()){
                vehiculos.add(v);            
            }
        }
        return vehiculos;
    }
    
    private String obtener_Propietario(String matricula) {
        ArrayList<Vehiculo> l_Vehiculos = obtener_ListaVehiculo();
        for(Vehiculo v: l_Vehiculos){
            if(v.getMatricula().equals(matricula)){
                return v.getPropietario();
            }
        }
        return null;
    }
    
}
