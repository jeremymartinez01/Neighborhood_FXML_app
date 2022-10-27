package data;

import com.mycompany.proyectopoo2.App;
import modelo.Residente;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import modelo.Usuario;
import modelo.Vehiculo;

public class ResidentesData {

    static String ruta = Constantes.ruta+"Residentes.txt";
    
    //REVISADO
    public static ArrayList<Residente> loadResidentes()  
        throws IOException, ClassNotFoundException {
            ArrayList<Residente> residentes= null;
            try (ObjectInputStream objectInputStream 
                = new ObjectInputStream(new FileInputStream(ruta))){
                residentes = (ArrayList<Residente>) objectInputStream.readObject();
        }
        return residentes;
    }

    //ME FALTA REVISAR
    public static void escribirResidentes(ArrayList<Residente> residentes){ 
            
        try (ObjectOutputStream objectOutputStream 
                = new ObjectOutputStream(new FileOutputStream(ruta))){
            objectOutputStream.writeObject(residentes);
            objectOutputStream.flush();
            System.out.println("... written to Residentes.dat.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
      
    public static void agregarResidentes (Residente r) 
        throws IOException {
            try (ObjectOutputStream objectOutputStream 
                = new ObjectOutputStream(new FileOutputStream(ruta,true)))  {
            objectOutputStream.writeObject(r);
            objectOutputStream.flush();
        }
    }
           
       
    public static void main(String[] args) throws ClassNotFoundException {

        ArrayList<Residente> residentes= new ArrayList<Residente>();
        Residente r1 = new Residente("eauria12","Eliana Auria","0915822243","eauria@espol.edu.ec","Femenino",1549);
        Residente r2 = new Residente("jjmartinez","Jeremy Martinez","0934872152","jjmg039@hotmail.com","Masculino",4673);
        //r1.getVehiculos().add(new Vehiculo("eli","GSF-4562"));
        residentes.add(r1);
        r2.getVehiculos().add(new Vehiculo("Jeremy","GSM-1073"));
        residentes.add(r2);
        
        try {
            escribirResidentes(residentes);
            System.out.println("escritos datos");
            try {
                System.out.println(loadResidentes());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
