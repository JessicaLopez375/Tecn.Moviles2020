package com.iua.jessicalopez.Modelo;

public class User {

    private Integer id;
    private String nombreApellido;
    private String email;
    private String password;

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
}
