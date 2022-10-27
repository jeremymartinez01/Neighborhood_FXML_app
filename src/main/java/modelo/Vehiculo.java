/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mycompany.proyectopoo2.App;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author wgcot
 */
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String propietario;
    private String matricula;
    

    public Vehiculo(String propietario,String matricula) {
        this.propietario = propietario;
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPropietario() {
        return propietario;
    }

    @Override
    public String toString() {
        return "("+ propietario + ", "+ matricula +")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if ((o instanceof Vehiculo)) {
                Vehiculo n = (Vehiculo) o;
                return matricula.equals(n.matricula);
            }
        }
        return false;
    }
    
    
}
