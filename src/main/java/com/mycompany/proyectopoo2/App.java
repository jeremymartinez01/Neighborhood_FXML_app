package com.mycompany.proyectopoo2;

import data.CasasData;
import data.ResidentesData;
import data.UsuariosData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Casa;
import modelo.Residente;
import modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import modelo.Vehiculo;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ArrayList<Usuario> usuarios;
    private static ArrayList<Residente> residentes;
    public static ArrayList<Casa> casas;

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static ArrayList<Residente> getResidentes() {
        return residentes;
    }

    public static ArrayList<Casa> getCasas() {
        return casas;
    }


    public static void main(String[] args) {
        try {
            usuarios = UsuariosData.loadUsuarios();
            residentes = ResidentesData.loadResidentes();
            casas = CasasData.loadCasas(residentes);
            launch();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No se pudieron cargar los archivos");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("VistaPrincipal"), 1024, 600);
        stage.setScene(scene);
        stage.setTitle("HOGWARTS CITY");
        stage.setResizable(false);
        stage.show();
    }
}