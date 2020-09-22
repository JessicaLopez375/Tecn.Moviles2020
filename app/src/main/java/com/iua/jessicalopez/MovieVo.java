package com.iua.jessicalopez;

import android.widget.ImageView;

public class MovieVo {

    private int nameMovie;
    private int imageMovie;

    public MovieVo() {

    }

    public MovieVo(int imageMovie) {
        this.imageMovie = imageMovie;
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
