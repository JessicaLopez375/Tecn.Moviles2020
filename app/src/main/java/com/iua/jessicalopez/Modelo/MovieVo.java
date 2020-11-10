package com.iua.jessicalopez.Modelo;


import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

public class MovieVo implements Serializable {

    //private int nameMovie;
    //private int imageMovie;
    //private int sinopsis;
    private String name;
    private String description;
    private String foto;


    public MovieVo() {

    }


    public MovieVo(String name, String foto, String description) {
        this.name = name;
        this.foto = foto;
        this.description = description;
    }

    public MovieVo(String name, String foto) {
        this.name = name;
        this.foto = foto;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
