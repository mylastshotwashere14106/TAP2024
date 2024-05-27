package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Calendar;

public class Impresora extends Stage {

    private TableView tabla = new TableView();
    private TableColumn<Impresion,String> column1 = new TableColumn<>("No");
    private TableColumn<Impresion,String> column2 = new TableColumn<>("Nombre");
    private TableColumn<Impresion,String> column3 = new TableColumn<>("Hojas");
    private TableColumn<Impresion,String> column4 = new TableColumn<>("Acceso");
    private ProgressBar progress;
    private Button agregar, apagar;
    private HBox botones;
    private VBox mainLayout;
    private Scene scene;
    /*
    private Imprimir impresora;
    private Scene scene;
    private HBox buttons;
    private VBox mainLayout = new VBox(), table = new VBox();
    private Button add, pause;
    */
    public Impresora(){
        CrearUI();
        this.setWidth(600);
        this.setHeight(600);
        this.setScene(scene);
        this.setTitle("Impresora");
        this.show();
    }

    public void CrearUI(){

        column1.setCellValueFactory(new PropertyValueFactory<>("Narchivo"));
        column2.setCellValueFactory(new PropertyValueFactory<>("Nomarchivo"));
        column3.setCellValueFactory(new PropertyValueFactory<>("Nhojas"));
        column4.setCellValueFactory(new PropertyValueFactory<>("Hacceso"));

        progress = new ProgressBar(0);

        agregar = new Button("Agregar");

        apagar = new Button("Apagar");

        botones = new HBox(agregar, apagar);

        mainLayout = new VBox(tabla, progress, botones);

        scene = new Scene(mainLayout);
        /*
        add = new Button("Agregar tarea");
        add.setOnAction(event -> addElementToTable());
        pause = new Button("Parar/Iniciar");
        pause.setOnAction(event -> impresora.setContinuar(false));
        buttons = new HBox(add, pause);
        mainLayout.getChildren().add(table);
        mainLayout.getChildren().add(buttons);
        scene = new Scene(mainLayout);
         */
    }

    public void addElementToTable(){
        /*
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
         */
    }
}
