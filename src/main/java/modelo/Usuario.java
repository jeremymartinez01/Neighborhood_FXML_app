/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 * @author wgcot
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String user;
    private String password;
    private Tipo tipo;

    public Usuario(String user, String password, Tipo tipo) {
        this.user = user;
        this.password = password;
        this.tipo = tipo;
    }

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Usuario(String user) {
        this.user = user;
        this.password = "0";
        this.tipo = Tipo.RESIDENTE;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +"user='" + user + '\'' + ", password='" + password + '\'' + ", tipo=" + tipo + '}';
    }

    public String toCSV() {
        return user + "," + password + "," + tipo;
    }

    public boolean equals(Object o) {
        if (o != null) {
            if ((o instanceof Usuario)) {
                Usuario n = (Usuario) o;
                return user.equals(n.user) && password.equals(n.password);
            }
        }
        return false;
    }


    public enum Tipo {
        RESIDENTE, ADMINISTRADOR
    }

}
