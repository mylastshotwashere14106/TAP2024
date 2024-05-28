package com.example.tap2024.vistas;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Impresora extends Stage {

    private static int noTareas = 0;
    private boolean apagado = false;
    private boolean funcionando = true;

    private TableView tabla = new TableView();
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
        this.setWidth(800);
        this.setHeight(600);
        this.setScene(scene);
        this.setTitle("Impresora");
        this.show();

        Thread thread = new Thread(new Runnable(){

            @Override
            public void run(){
                //Aqui modificamos las variables
                    for(int i = 0; i < 1000; i++) {
                        if(i == 999) i = 0;
                        try {
                            Thread.sleep(1000);
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        if ((!apagado) && tabla.getItems().size() > 0) {
                            //System.out.println("Hay algo en la tabla y no esta apagado");
                            Impresion temp = (Impresion) tabla.getItems().get(0);
                            int duracion = temp.Nhojas;
                            for(int j = 0; j < duracion; j ++){
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                final int j2 = j;
                                //System.out.println("Duracion actual " + duracion);
                                //System.out.println("Progreso actual " + j);
                                Platform.runLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        progress.setProgress(progress.getProgress() + ((double) 1/duracion));
                                        if(j2 == duracion - 1){
                                            progress.setProgress(0);
                                            deleteElementFromTable();
                                        }
                                    }
                                });
                            }
                        }

                    }
            }
        });
            thread.start();
    }

    public void CrearUI(){

        TableColumn<Impresion,String> column1 = new TableColumn<>("No");
        column1.setCellValueFactory(new PropertyValueFactory<>("Narchivo"));
        TableColumn<Impresion,String> column2 = new TableColumn<>("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<>("Nomarchivo"));
        TableColumn<Impresion,String> column3 = new TableColumn<>("Hojas");
        column3.setCellValueFactory(new PropertyValueFactory<>("Nhojas"));
        TableColumn<Impresion,String> column4 = new TableColumn<>("Acceso");
        column4.setCellValueFactory(new PropertyValueFactory<>("Hacceso"));
        column1.setPrefWidth(200);
        column2.setPrefWidth(200);
        column3.setPrefWidth(200);
        column4.setPrefWidth(200);
        tabla.getColumns().addAll(column1, column2, column3, column4);

        progress = new ProgressBar(0);
        progress.setPrefWidth(800);
        progress.setVisible(true);

        agregar = new Button("Agregar");
        agregar.setOnAction(even -> addElementToTable());

        apagar = new Button("Apagar/Encender");
        //apagar.setOnAction(event -> deleteElementFromTable());
        apagar.setOnAction(event -> apagarEncender());

        botones = new HBox(agregar, apagar);

        mainLayout = new VBox(tabla, progress, botones);

        BackgroundImage bg= new BackgroundImage(new Image(getClass().getResource("/images/pinkusmaximus.png").toString(),800,600,true,false),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
        Background bgFR = new Background(bg);
        mainLayout.setBackground(bgFR);

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

    public void apagarEncender(){
        if(apagado){
            apagado = false;
        }else{
            apagado = true;
        }
    }

    public void deleteElementFromTable(){
        if(tabla.getItems().size() > 0){
            tabla.getItems().remove(0);
            //tabla.refresh();
        }else{
            System.out.println("Soy chido");
        }
    }

    public void addElementToTable(){

        int noTemp = noTareas;
        int noHojasTemp = ((int) (Math.random() * 99)) + 1;
        String horaTemp = Calendar.getInstance().getTime().toString();
        String nomTemp = CheckInput.withoutSpaces(horaTemp) + CheckInput.generateExtension();

        Impresion temp = new Impresion(noTemp, nomTemp, noHojasTemp, horaTemp);
        tabla.getItems().add(temp);

        /*tabla.getItems().add(new Impresion(noTareas,
                CheckInput.withoutSpaces(Calendar.getInstance().toString()) + CheckInput.generateExtension(),
                (int) (Math.random() * 100),
                Calendar.getInstance().toString()));
        */
        noTareas++;
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
