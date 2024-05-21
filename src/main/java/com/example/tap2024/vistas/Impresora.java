package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;

public class Impresora extends Stage {

    private Imprimir impresora;
    private Scene scene;
    private VBox mainLayout, table;
    private Button add, pause;

    public Impresora(){
        impresora = new Imprimir();
        CrearUI();
        this.setScene(scene);
        this.setTitle("Impresion");
        this.show();
    }

    public void CrearUI(){

    }

    public void addElementToTable(){
        impresora.addTask();
    }
}
