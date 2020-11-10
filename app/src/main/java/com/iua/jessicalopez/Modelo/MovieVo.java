package com.iua.jessicalopez.Modelo;


import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

public class MovieVo  implements Serializable {

    private int nameMovie;
    private int imageMovie;
    private int sinopsis;
    private String name;
    private String description;
    private String path;



    public MovieVo() {

    }

    public MovieVo(int imageMovie) {
        this.imageMovie = imageMovie;
    }

    public MovieVo(int nameMovie, int imageMovie, int sinopsis) {
        this.nameMovie = nameMovie;
        this.imageMovie = imageMovie;
        this.sinopsis = sinopsis;
    }

    public MovieVo(String name, int imageMovie, String description) {
        this.name = name;
        this.imageMovie = imageMovie;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public int getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(int sinopsis) {
        this.sinopsis = sinopsis;
    }

    public MovieVo(int nameMovie, int imageMovie) {
        this.nameMovie = nameMovie;
        this.imageMovie = imageMovie;
    }




    public int getNameMovie() {
        return nameMovie;
    }

    public String getName() {
        return name;
    }

    public void setNameMovie(int nameMovie) {
        this.nameMovie = nameMovie;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(int imageMovie) {
        this.imageMovie = imageMovie;
    }

    public String getPath() {
        return path;
    }
}
