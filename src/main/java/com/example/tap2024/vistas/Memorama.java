package com.example.tap2024.vistas;

import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
//import org.kordamp.bootstrapfx.BootstrapFX;
//import org.kordamp.bootstrapfx.scene.layout.Panel;
import java.util.Timer;

public class Memorama extends Stage {

    private int tarAntX = -1, tarAntY = -1;
    private ImageView standardImage;
    private ImageView tempRealImage;
    private int victoria = 100;

    private int turno = 1;
    private Scene escena;
    private GridPane gdpJuego;
    private TextField pares;
    private Button mixButton;
    private Label player1;
    private Label score1l;
    private Label timer1l;
    private Label player2;
    private Label score2l;
    private Label timer2l;
    private int timePerTurn = 60;
    private int score1 = 0, score2 = 0, timer1 = timePerTurn , timer2 = timePerTurn, x, y;
    private HBox mixParesLayout, player1HLay, player2HLay, mainLayoutFR;
    private VBox player1VLay, player2VLay, mainLayout;
    private String[][] images;
    private Button[][] buttons;
    private ImageView[][] imagesForButton;
    String[] nImg = {"black.png","black-2.png","black-3.png","brunette.png","brunette-2.png",
    "dark-blue.png","green.png","green-2.png","green-3.png","orange.png",
    "pink.png","pink-2.png","red.png","white.png","yellow.png"};


    public Memorama(){
        CrearUI();
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setTitle("Memorama :)");
        this.setScene(escena);
        this.show();

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i <= timePerTurn; i++){

                    if(score1 == victoria){
                        pares.setText("First player wins!");
                    }else if(score2 == victoria){
                        pares.setText("Second player wins!");
                    }else{
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(i == timePerTurn){
                            if(turno % 2 == 0) {
                                timer2 = timePerTurn;
                            }else{
                                timer1 = timePerTurn;
                            }
                            turno = turno + 1;
                            i = 0;
                        }

                        if(turno % 2 == 0) {
                            timer2 = timer2 - 1;
                        }else{
                            timer1 = timer1 - 1;
                        }
                        final int timer1lf = timer1;
                        final int timer2lf = timer2;

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(turno % 2 == 0) {
                                    timer2l.setText(String.valueOf(timer2lf));
                                    timer2l.getStyleClass().clear();
                                    timer2l.getStyleClass().add("lbl-warning");
                                    player1.getStyleClass().clear();
                                    player2.getStyleClass().clear();
                                    player2.getStyleClass().add("lbl-success");
                                    player1.getStyleClass().add("lbl-danger");
                                    //player2.setStyle("-fx-text-fill: green;");
                                    //player1.setStyle("-fx-text-fill: red;");
                                }else{
                                    timer1l.setText(String.valueOf(timer1lf));
                                    timer1l.getStyleClass().clear();
                                    timer1l.getStyleClass().add("lbl-warning");
                                    player1.getStyleClass().clear();
                                    player2.getStyleClass().clear();
                                    player1.getStyleClass().add("lbl-default");
                                    player2.getStyleClass().add("lbl-danger");
                                    //player1.setStyle("-fx-text-fill: green;");
                                    //player2.setStyle("-fx-text-fill: red;");
                                }
                            }
                        });
                    }
                }
            }
        });
        taskThread.start();
    }

    private void CrearUI() {

        //Iniciar Gridpane
        gdpJuego = new GridPane();
        //Mezclar pares boton y texto
        pares = new TextField("Numero de pares");
        mixButton = new Button("Iniciar juego");
        mixButton.getStyleClass().clear();
        mixButton.getStyleClass().add("btn-info");
        mixButton.setOnAction(event -> revolverCartas());
        mixParesLayout = new HBox(pares, mixButton);
        //Player 1
        player1 = new Label("Player 1");
        player1.getStyleClass().clear();
        player1.getStyleClass().add("lbl-success");
        score1l = new Label(String.valueOf(score1));
        score1l.getStyleClass().clear();
        score1l.getStyleClass().add("lbl-primary");
        player1HLay = new HBox(player1, score1l);
        timer1l = new Label("60");
        timer1l.getStyleClass().clear();
        timer1l.getStyleClass().add("lbl-warning");
        player1VLay = new VBox(player1HLay, timer1l);
        //Player 2
        player2 = new Label("Player 2");
        player1.getStyleClass().clear();
        player1.getStyleClass().add("lbl-danger");
        score2l = new Label(String.valueOf(score2));
        score2l.getStyleClass().clear();
        score2l.getStyleClass().add("lbl-primary");
        player2HLay = new HBox(player2, score2l);
        timer2l = new Label("60");
        timer2l.getStyleClass().clear();
        timer2l.getStyleClass().add("lbl-warning");
        player2VLay = new VBox(player2HLay, timer2l);
        //Main layout V
        mainLayout = new VBox(mixParesLayout, player1VLay, player2VLay);
        //Main layout
        mainLayoutFR = new HBox(gdpJuego, mainLayout);
        BackgroundImage bg= new BackgroundImage(new Image(getClass().getResource("/images/pinkusmaximus.png").toString(),960,720,true,false),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
        Background bgFR = new Background(bg);
        mainLayoutFR.setBackground(bgFR);
        escena = new Scene(mainLayoutFR, 960, 720);
    }

    private void revolverCartas() {

        if(CheckInput.checkInt(pares.getText())) {
            int tempSize = Integer.parseInt(pares.getText());
            buttons = new Button[tempSize][2];
            images = new String[tempSize][2];
            imagesForButton = new ImageView[tempSize][2];
            int posx = 0;
            int posy = 0;
            int cont = 0;
            tarAntX = -1;
            tarAntY = -1;
            score1 = 0;
            score2 = 0;
            score1l.setText("Score: " + score1);
            score2l.setText("Score: " + score2);
            timer1 = timePerTurn;
            timer2 = timePerTurn;
            turno = 1;
            victoria = tempSize;
            for (int i = 0; i < tempSize; ) {
                posx = (int) (Math.random() * tempSize);
                posy = (int) (Math.random() * 2);
                if (buttons[posx][posy] == null) {
                    buttons[posx][posy] = new Button();
                    images[posx][posy] = nImg[i];
                    //System.out.println(images[posx][posy] + "posicion" + "x" +posx + "y" + posy);
                    imagesForButton[posx][posy] = new ImageView(
                            getClass().getResource("/images/" + nImg[i]).toString()
                    );
                    imagesForButton[posx][posy].setFitHeight(200);
                    imagesForButton[posx][posy].setFitWidth(240);
                    //buttons[posx][posy].setGraphic(imagesForButton[posx][posy]);

                    standardImage = new ImageView(getClass().getResource("/images/lel.jpg").toString());
                    standardImage.setFitHeight(200);
                    standardImage.setFitWidth(240);

                    buttons[posx][posy].setGraphic(standardImage);
                    buttons[posx][posy].setPrefSize(240, 200);

                    final int posxt = posx;
                    final int posyt = posy;
                    buttons[posx][posy].setOnAction(event -> presionarCarta(posxt, posyt));

                    gdpJuego.add(buttons[posx][posy], posx, posy);
                    cont++;
                    if (cont == 2) {
                        i++;
                        cont = 0;
                    }
                }
            }
        }else{
            pares.setText("Please type a proper size");
        }
    }


    public void presionarCarta(int posx, int posy){
        tempRealImage = new ImageView(getClass().getResource("/images/lel.jpg").toString());
        tempRealImage.setFitWidth(240);
        tempRealImage.setFitHeight(200);
        if(tarAntX == -1 || tarAntY == -1) {
            tempRealImage = new ImageView(getClass().getResource("/images/" + images[posx][posy]).toString());
            tempRealImage.setFitWidth(240);
            tempRealImage.setFitHeight(200);
            buttons[posx][posy].setGraphic(tempRealImage);
            tarAntX = posx;
            tarAntY = posy;
        }else{
            tempRealImage = new ImageView(getClass().getResource("/images/" + images[posx][posy]).toString());
            tempRealImage.setFitWidth(240);
            tempRealImage.setFitHeight(200);
            buttons[posx][posy].setGraphic(tempRealImage);
            if(images[posx][posy].equals(images[tarAntX][tarAntY])) {
                buttons[tarAntX][tarAntY].setGraphic(imagesForButton[tarAntX][tarAntY]);
                buttons[posx][posy].setGraphic(imagesForButton[posx][posy]);
                tarAntX = -1;
                tarAntY = -1;
                if(turno % 2 == 0){
                    score2 = score2 + 1;
                    score2l.setText("Score: " + String.valueOf(score2));
                    score2l.getStyleClass().clear();
                    score2l.getStyleClass().add("lbl-primary");
                }else{
                    score1 = score1 + 1;
                    score1l.setText("Score: " + String.valueOf(score1));
                    score1l.getStyleClass().clear();
                    score1l.getStyleClass().add("lbl-primary");
                }
            }else{
                if(turno % 2 == 0){
                    timer2 = timePerTurn;
                }else{
                    timer1 = timePerTurn;
                }
                turno = turno + 1;
                tempRealImage = new ImageView(getClass().getResource("/images/lel.jpg").toString());
                tempRealImage.setFitWidth(240);
                tempRealImage.setFitHeight(200);
                buttons[posx][posy].setGraphic(tempRealImage);
                tempRealImage = new ImageView(getClass().getResource("/images/lel.jpg").toString());
                tempRealImage.setFitWidth(240);
                tempRealImage.setFitHeight(200);
                buttons[tarAntX][tarAntY].setGraphic(tempRealImage);
                tarAntX = -1;
                tarAntY = -1;
            }
        }
    }

}

