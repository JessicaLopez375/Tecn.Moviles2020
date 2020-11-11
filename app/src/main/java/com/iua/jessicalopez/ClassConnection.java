package com.iua.jessicalopez;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassConnection extends AsyncTask<String, String, String> {

    //Trabajar en segundo plano
    @Override
    protected String doInBackground(String... strings) {
        //Objeto para hacer la conexion
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        //Objeto que tendra la direccion donde estan los datos a descargar
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //nos conectamos


        try {
            //Abrimos la conexion
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //Nos conectamos
            httpURLConnection.connect();
            //Obtenemos el codigo de respuesta
            int code = httpURLConnection.getResponseCode();

            //Si todo esta bien empiezo a leer la informacion
            if (code == HttpURLConnection.HTTP_OK) {
                //Guardamos la informacion
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

                /* Creamos un lector para recorrer todo el archivo y guardarla en un string */
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuffer buffer = new StringBuffer();
                //Leemos linea por linea
                while ((line = reader.readLine()) != null) {
                    //Agregamos al buffer cada linea
                    buffer.append(line);
                }
                
                return buffer.toString();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
