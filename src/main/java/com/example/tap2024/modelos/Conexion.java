package com.example.tap2024.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static private String DB = "sys";
    static private String USER = "root";
    static private String PWD = "1234";
    static public Connection connection;

    public static void crearConexion(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+DB+"?allowPublicKeyRetrieval=true&useSSL=false",USER,PWD);
            System.out.println("Conexion establecida");
        }catch(Exception e){
            e.printStackTrace();;
        }
    }
}
