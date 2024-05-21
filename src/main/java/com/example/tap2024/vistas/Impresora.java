package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;

public class Impresora extends Stage {

    private Imprimir impresora;
    private Scene scene;
    private HBox buttons;
    private VBox mainLayout = new VBox(), table = new VBox();
    private Button add, pause;

    public Impresora(){
        impresora = new Imprimir();
        impresora.start();
        CrearUI();
        this.setHeight(600);
        this.setWidth(600);
        this.setScene(scene);
        this.setTitle("Impresion");
        this.show();
    }

    public void CrearUI(){
        add = new Button("Agregar tarea");
        add.setOnAction(event -> addElementToTable());
        pause = new Button("Parar/Iniciar");
        pause.setOnAction(event -> impresora.setContinuar(false));
        buttons = new HBox(add, pause);
        mainLayout.getChildren().add(table);
        mainLayout.getChildren().add(buttons);
        scene = new Scene(mainLayout);
    }

    public void addElementToTable(){
        impresora.addTask();
        int index = impresora.porImprimir.size()-1;
        Label no = new Label(String.valueOf(impresora.porImprimir.get(index).getNo()));
        Label nombre = new Label(String.valueOf(impresora.porImprimir.get(index).getNameN()));
        Label noHojas = new Label(String.valueOf(impresora.porImprimir.get(index).getNoHojas()));
        Label hora = new Label(impresora.porImprimir.get(0).getHora());
        ProgressBar progreso = new ProgressBar();
        impresora.porImprimir.get(0).progressBar = progreso;
        VBox temp = new VBox(no, nombre, noHojas, hora, progreso);
        table.getChildren().add(temp);
    }
}
