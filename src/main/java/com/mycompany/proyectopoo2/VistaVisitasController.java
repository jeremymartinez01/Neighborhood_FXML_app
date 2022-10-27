/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import data.ResidentesData;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.LocalDateStringConverter;
import modelo.Residente;
import modelo.StringToDate;
import static modelo.StringToDate.enIntervalo;
import modelo.Vehiculo;
import modelo.Visitante;
import modelo.Visitante.Estado;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaVisitasController implements Initializable {

    @FXML
    private Button btn_RegitrarVisita;
    @FXML
    private Button btn_Eliminar;
    @FXML
    private TextField tbl_FechaInicial;
    @FXML
    private TextField lbl_FechaFinal;
    @FXML
    private Button btn_Buscar;
    @FXML
    private TableView<Visitante> table_Visitante;
    @FXML
    private TableColumn<Visitante, String> tbl_Nombre;
    @FXML
    private TableColumn<Visitante, String> tbl_Cedula;
    @FXML
    private TableColumn<Visitante, Integer> tbl_Pin;
    @FXML
    private TableColumn<Visitante, LocalDateTime> tbl_Fecha;
    @FXML
    private TableColumn<Visitante, Estado> tbl_Estado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        tbl_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbl_Cedula.setCellValueFactory(new PropertyValueFactory<>("ci"));
        tbl_Pin.setCellValueFactory(new PropertyValueFactory<>("pin"));
        tbl_Fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));    
        tbl_Estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
      
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
    private void menuRegistrar(MouseEvent event){
        try {
            App.setRoot("VistaRegistrarVisita");
        } catch (IOException ex) {
            System.out.println("No se pudo cambiar la vista");
        }
        
    }   
      
    @FXML
    private void buscar(MouseEvent event){
        
        ArrayList<String> errores = new ArrayList<String>();
        String fechaInicial=tbl_FechaInicial.getText();
        String fechaFinal=lbl_FechaFinal.getText();
        if(fechaInicial.equals("")|fechaInicial.equals(" ")){
            errores.add("Ingrese la fecha inicial de su búsqueda");      
        }
        if(fechaFinal.equals("")|fechaFinal.equals(" ")){
            errores.add("Ingrese la fecha final de su búsqueda");      
        }
        if(errores.isEmpty()){
            try{
                
                LocalDateTime dateTimeInicial= StringToDate.stringToDate(fechaInicial);
                LocalDateTime dateTimeFinal= StringToDate.stringToDate(fechaFinal); 
                ArrayList<Visitante> visitantesFiltrados = new ArrayList<>();
                
                for(Visitante v:obtener_ListaVisitante()){
                    if(enIntervalo(dateTimeInicial,dateTimeFinal,v.getFecha())){
                        visitantesFiltrados.add(v);
                    }
                }
                table_Visitante.getItems().setAll(visitantesFiltrados);
       
            }catch(DateTimeParseException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Fecha en el formato incorrecto");
                alert.setContentText("Ingrese la fecha con el siguiente formato:\nDD/MM/AAAA HH:MM");
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
        
 
    @FXML
    private void eliminar(ActionEvent action) throws IOException{
       
        Residente r = VistaResidenteController.getLogg_Residente(); 
        Visitante v = table_Visitante.getSelectionModel().getSelectedItem();   
        if(r.getVisitantes().contains(v)){
            r.getVisitantes().remove(v);
            ResidentesData.agregarResidentes(r);
            ResidentesData.escribirResidentes(App.getResidentes());
            table_Visitante.getItems().remove(v);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Registro eliminado exitosamente");
            alert.show();        
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,("Seleccione un registro"));
            alert.show();             
        }   
    }
    
    private void limpiar(){
        tbl_FechaInicial.setText("");
        lbl_FechaFinal.setText("");
    }
    
    public static ArrayList<Visitante> obtener_ListaVisitante() {
        ArrayList<Residente> residentes = App.getResidentes();
        ArrayList<Visitante> visitantes = new ArrayList<>();
        for(Residente r: residentes){
            for(Visitante v: r.getVisitantes()){
                visitantes.add(v);            
            }
        }
        return visitantes;
    }
    
    
}
