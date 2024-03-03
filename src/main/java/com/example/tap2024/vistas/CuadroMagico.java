package com.example.tap2024.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CuadroMagico extends Stage {

    private CuadroMagicoLogica cuadro;

    private GridPane magicSquare;

    private Button create;

    private TextField size;

    private VBox layout;

    private HBox createLayout;

    public CuadroMagico(){
        this.getIcons().add(new Image("https://i.4cdn.org/w/1709038154516331.png"));
        CrearUI();
        layout = new VBox(magicSquare,create,size);
        Scene scene = new Scene(layout,500,500);
        scene.getStylesheets().add(getClass().getResource("/estilos/cuadromagico.css").toString());
        this.setTitle("Cuadro Mágico");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI(){
        magicSquare = new GridPane();
        magicSquare.setPrefSize(500,500);
        magicSquare.setAlignment(Pos.CENTER);
        size = new TextField("3");
        size.setPrefSize(500,25);
        create = new Button("Crear");
        create.setPrefSize(500,25);
        create.setOnAction(event -> {
            if(!CheckInput.checkInt(size.getText())){
                size.setText("Type a number you dummy");
            }else if(!CheckInput.checkOdd(Integer.parseInt(size.getText()))) {
                size.setText("Type an odd number you dummy");
            }else if(Integer.parseInt(size.getText()) < 3){
                size.setText("You need at least a 3 dude");
            }else{
                cuadro = new CuadroMagicoLogica(Integer.parseInt(size.getText()));
                cuadro.showMagicSquareGUI(magicSquare);
                size.setText("");
            }
        });
    }
}

