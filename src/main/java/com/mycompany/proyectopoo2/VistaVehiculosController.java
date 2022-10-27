/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import modelo.Visitante;
import data.ResidentesData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Residente;
import modelo.Usuario;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaVehiculosController implements Initializable {

    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_matricula;
    @FXML
    private TableColumn<Vehiculo, String> tbl_nombre;
    @FXML
    private TableColumn<Vehiculo, String> tbl_matricula;
    @FXML
    private TableView<Vehiculo> tablaVehiculos;
    @FXML
    private Button btn_Registrar;
    @FXML
    private Button bton_Eliminar;
    
    private CheckBox checkBoxRegistro;
    
    private int posicionVehiculoSeleccionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Residente r = VistaResidenteController.getLogg_Residente();
        ArrayList<Vehiculo> vehiculo= r.getVehiculos();
        tbl_nombre.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("propietario"));
        tbl_matricula.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("matricula"));
        tablaVehiculos.getItems().setAll(vehiculo);
                    
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
    private void registrarVehiculo(ActionEvent event) 
        throws IOException, ClassNotFoundException{
            
            ArrayList<String> errores = new ArrayList<String>();
            String propietario=txt_nombre.getText();
            String matricula=txt_matricula.getText();
            if(propietario.equals("") | propietario.equals(" ")){
                errores.add("Debe haber un propietario");      
            }
            if(matricula.equals("") | matricula.equals(" ")){
                errores.add("Debe haber una matricula");      
            }
        
            if(errores.isEmpty()){
                Vehiculo nuevo_Vehiculo= new Vehiculo(propietario,matricula);
                Residente r = VistaResidenteController.getLogg_Residente();         
                if(!r.getVehiculos().contains(nuevo_Vehiculo)){
                    r.getVehiculos().add(nuevo_Vehiculo);
                    ResidentesData.agregarResidentes(r);
                    ResidentesData.escribirResidentes(App.getResidentes());
                    //App.getVehiculos().add(nuevo_Vehiculo);
                    limpiar();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Registro Exitoso");
                    alert.show();
                    tablaVehiculos.getItems().add(nuevo_Vehiculo);   
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,("Este vehiculo ya está registrado"));
                    alert.show();
                }
           
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String texto="";
                for(String x : errores){
                    texto+=x+"\n";
                }
                alert.setContentText(texto);
                alert.show();
            }   
    }
    
    private void limpiar(){
        txt_nombre.setText("");
        txt_matricula.setText("");
    }
    

    @FXML
    private void eliminar(ActionEvent action) throws IOException{
       
        Residente r = VistaResidenteController.getLogg_Residente(); 
        Vehiculo v = tablaVehiculos.getSelectionModel().getSelectedItem();   
        if(r.getVehiculos().contains(v)){
            r.getVehiculos().remove(v);
            ResidentesData.agregarResidentes(r);
            ResidentesData.escribirResidentes(App.getResidentes());
            tablaVehiculos.getItems().remove(v);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Registro eliminado exitosamente");
            alert.show();        
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,("Seleccione un registro"));
            alert.show();             
        }   
    }
}
