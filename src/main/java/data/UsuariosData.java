/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author wgcot
 */
public class UsuariosData {
    
    static String ruta = Constantes.ruta+"Usuarios.txt";
    static int numUsuarios;

    public static int getNumUsuarios() {
        return numUsuarios;
    }

    public static void setNumUsuarios(int numUsuarios) {
        UsuariosData.numUsuarios = numUsuarios;
    }
    //REVISADO
    public static ArrayList<Usuario> loadUsuarios() throws IOException {
        ArrayList<Usuario> usuarios = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            reader.readLine().split(",");
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                Usuario usuario = new Usuario(datos[0], datos[1], Usuario.Tipo.valueOf(datos[2].toUpperCase()));
                usuarios.add(usuario);
                counter++;
            }
            numUsuarios = counter;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } catch (IOException ex){
            System.out.println(ex.getMessage());
            throw  ex;
        }
        return usuarios;
    }

    //ME FALTA REVISAR
    public static void registrarUsuario(Usuario usuario)  throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta,true))) {
            writer.newLine();
            writer.write(usuario.toCSV());
            writer.flush();
        }
    }
}
