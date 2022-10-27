/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Casa;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author wgcot
 */
public class VistaMapaController implements Initializable {

    @FXML
    private GridPane grid_Mapa;
    
    static int coodX;
    static int coodY;

    public static void setCoodX(int coodX) {
        VistaMapaController.coodX = coodX;
    }

    public static void setCoodY(int coodY) {
        VistaMapaController.coodY = coodY;
    }
    
    

    private static void registrarResidente(MouseEvent event) {

        try {
            setCoodY(GridPane.getRowIndex((Node)event.getTarget()));
            setCoodX(GridPane.getColumnIndex((Node)event.getTarget()));
            System.out.println(coodX+" "+coodY);
            FXMLLoader loader = new FXMLLoader(VistaMapaController.class.getResource("VistaRegistroResidente.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCasas();
    }
    
    public void cargarCasas(){
        ArrayList<Casa> casas = App.getCasas();
        int ancho = 90;
        int alto = 60;
        String rutaImagen;
        for (Casa casa : casas) {
            int coordenadaX = casa.getCoordX();
            int coordenadaY = casa.getCoordY();

            if ((coordenadaX <= 3) && (coordenadaY <= 2)) { //GRIFFINDOR
                rutaImagen = "Images/casa1.png";
                loadCasa(grid_Mapa, rutaImagen, casa, ancho, alto);

            } else if ((coordenadaX <= 3) && ((coordenadaY >= 3) && (coordenadaY <= 5))) { //HUFFLEPUFF
                rutaImagen = "Images/casa2.png";
                loadCasa(grid_Mapa, rutaImagen, casa, ancho, alto);

            } else if (((coordenadaX >= 4) && (coordenadaX <= 7)) && (coordenadaY <= 2)) { //SLYTHERIN
                rutaImagen = "Images/casa3.png";
                loadCasa(grid_Mapa, rutaImagen, casa, ancho, alto);

            } else if ((coordenadaY >= 3) && (coordenadaY <= 5)) { //RAVENCLAW
                rutaImagen = "Images/casa4.png";
                loadCasa(grid_Mapa, rutaImagen, casa, ancho, alto);
            }
        }
    }

    @FXML
    private void mostrarReporte(ActionEvent event) {
        try {
            App.setRoot("VistaReporte");
        } catch (IOException ex) {
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        try {
            App.setRoot("VistaPrincipal");
        } catch (IOException ex) {
        }
    }

    public void loadCasa(GridPane grid, String rutaImgCasa, Casa casa, int ancho, int alto) {
        try {
            InputStream input = App.class.getResource(rutaImgCasa).openStream();
            Image img = new Image(input, ancho, alto, true, true);
            ImageView imageView = new ImageView(img);
            imageView.setOnMouseClicked(VistaMapaController::registrarResidente);
            GridPane.setConstraints(imageView, casa.getCoordX(), casa.getCoordY());
            grid.getChildren().add(imageView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDragDetection(MouseEvent event) {
    }

    @FXML
    private void handleDragOver(DragEvent event) {
    }

    @FXML
    private void handleDragDrop(DragEvent event) {
    }

}
