package com.iua.jessicalopez.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class Preferencias {
    public static final String GENEROS = "generos";

    public static String generos;

    public String obtenerPreferencias(SharedPreferences preferences, Context context){
        generos = preferences.getString(GENEROS, "Action");
        return  generos;



    }
}
