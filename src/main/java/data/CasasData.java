package data;

import com.mycompany.proyectopoo2.App;
import modelo.Casa;
import modelo.Residente;
import modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static modelo.Casa.*;

public class CasasData {
    static String ruta = Constantes.ruta+"Casas.txt";
    public static ArrayList<Casa> loadCasas(List<Residente> r) throws IOException {
        ArrayList<Casa>  casas = new ArrayList<>();
        ArrayList<Residente> residentes = App.getResidentes();
        ArrayList<Usuario> usuarios = App.getUsuarios();

        try (BufferedReader reader = new BufferedReader(new FileReader( ruta))) {
            reader.readLine();
            String line;
            Casa c_nueva = null;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                String user = datos[0];
                Manzana manzana = Manzana.valueOf(datos[3].toUpperCase());

                if (!user.equals("vacio")){
                    for(Residente r2 : r ){
                        if(r2.getNombre().equals(user)){
                             c_nueva = new Casa(r2, Integer.parseInt(datos[1]),  Integer.parseInt(datos[2]), manzana, Integer.parseInt(datos[4]));
                        }
                    }
                }else{
                     c_nueva= new Casa(Integer.parseInt(datos[1]),  Integer.parseInt(datos[2]), manzana, Integer.parseInt(datos[4]));
                }

                casas.add(c_nueva);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } catch (IOException ex){
            System.out.println(ex.getMessage());
            throw  ex;
        }
        return casas;
    }

    //ME FALTA REVISAR
    public static void actualizarCasas(ArrayList<Casa> casas)  throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for(Casa casa : casas){
                writer.write(casa.toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
    
}
