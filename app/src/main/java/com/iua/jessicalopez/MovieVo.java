package com.iua.jessicalopez;


import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

public class MovieVo  implements Serializable {

    private int nameMovie;
    private int imageMovie;
    private int sinopsis;



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


    public void setNameMovie(int nameMovie) {
        this.nameMovie = nameMovie;
    }

    public int getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(int imageMovie) {
        this.imageMovie = imageMovie;
    }


}
