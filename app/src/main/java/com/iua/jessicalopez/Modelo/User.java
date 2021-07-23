package com.iua.jessicalopez.Modelo;

import android.util.Patterns;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.regex.Pattern;

public class User implements Serializable {

    private Integer id;
    private String nombreApellido;
    private String email;
    private String password;

    public User(){}
    public User(Integer id, String nombreApellido, String email, String password) {
        this.id = id;
        this.nombreApellido = nombreApellido;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean validarLogin(String email,String password)
    {
        if(email.equals(null)||email.trim().length()==0||!validarEmail(email)||password.equals(null) || password.trim().length()==0)
            return true;
        return false;
    }

    public String validarDatos(String nombreApellido, String email, String password, String password2)
    {
        if (nombreApellido.equals(null)||nombreApellido.trim().length()==0)
            return "El nombre ingresado no es valido";
        if(email.equals(null)||email.trim().length()==0||!validarEmail(email))
            return "El email ingresado no es valido";
        if (password.equals(null)||password.trim().length()==0 || password.length()<8 ||
                password2.equals(null)||password2.trim().length()==0 || password2.length()<8)
            return "La contraseña ingresada no es valida";
        if (!password.equals(password2))
            return "Las contraseñas no coinciden";

        return null;
    }


}
