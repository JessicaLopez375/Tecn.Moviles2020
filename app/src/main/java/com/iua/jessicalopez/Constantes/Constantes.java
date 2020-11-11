package com.iua.jessicalopez.Constantes;

public class Constantes {

    //Constantes de los campos de la tabla usuarios

    public static String TABLA_USER = " user";
    public static String CAMPO_ID = "id";
    public static String CAMPO_NOMBREAPELLIDO = "nombreApellido";
    public static String CAMPO_EMAIL = "email";
    public static String CAMPO_PASSWORD = "password";
   public static final String CREAR_TABLA_USER = "CREATE TABLE" + TABLA_USER+" " +
           "("+CAMPO_ID+" INTEGER, " +
           ""+CAMPO_NOMBREAPELLIDO+" TEXT, " +
           ""+CAMPO_EMAIL+" TEXT, " +
           ""+CAMPO_PASSWORD+" TEXT)";
}
