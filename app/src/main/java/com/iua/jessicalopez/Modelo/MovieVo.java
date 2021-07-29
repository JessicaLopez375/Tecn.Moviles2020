package com.iua.jessicalopez.Modelo;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Constantes.Constantes;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

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


    public void registrarMovieFavorita(String titulo, String description, String uricompleta, ConexionSQLiteHelper con) {

        //Abro la base de datos para poder editarlo
        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constantes.CAMPO_TITULO, titulo);
        values.put(Constantes.CAMPO_DESCRIPCION, description);
        values.put(Constantes.CAMPO_URICOMPLETA, uricompleta);
        Long resultado = db.insert(Constantes.TABLA_FAV, Constantes.CAMPO_IDFAV, values);

    }

    public int buscarPelicula(String titulo, ConexionSQLiteHelper con) {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {titulo};
        String[] campos = {Constantes.CAMPO_IDFAV};
        int flag = 0;
        try {
            Cursor cursor = db.query(Constantes.TABLA_FAV, campos, Constantes.CAMPO_TITULO + "=?",
                    param, null, null, null);
            cursor.moveToFirst();
            flag = cursor.getInt(0);
            cursor.close();

        } catch (Exception e) {
        }
        return flag;
    }

    public ArrayList<MovieVo> listarPeliculasFav(ConexionSQLiteHelper con) {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] campos = {Constantes.CAMPO_IDFAV, Constantes.CAMPO_TITULO, Constantes.CAMPO_DESCRIPCION,
        Constantes.CAMPO_URICOMPLETA};
        ArrayList<MovieVo> pelisfav = new ArrayList<>();
        try {
            Cursor cursor = db.query(Constantes.TABLA_FAV, campos, null,
                    null,null, null, null);
            cursor.moveToFirst();
            do{
                pelisfav.add(new MovieVo(cursor.getString(1).toString(),
                                cursor.getString(3).toString(),
                        cursor.getString(2).toString()));

            }while (cursor.moveToNext());
            cursor.close();

        }catch (Exception e){



        }
        return pelisfav;

    }

    public void eliminarPeliFav(String titulo, ConexionSQLiteHelper con){
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {titulo};
        db.delete(Constantes.TABLA_FAV, Constantes.CAMPO_TITULO + "=?", param);
        db.close();



    }

}


