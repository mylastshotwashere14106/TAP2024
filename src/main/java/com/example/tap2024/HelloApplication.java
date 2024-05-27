package com.example.tap2024;

import com.example.tap2024.modelos.Conexion;
import com.example.tap2024.vistas.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private MenuBar mnbPrincipal;
    private Menu menParcial1, menParcial2, menSalir;
    private MenuItem mitCalculadora, mitSalir, mitMemorama, mitCuadroMagico, mitImpresora;
    private BorderPane bdpPanel;
    @Override
    public void start(Stage stage) throws IOException {
        //CuadroMagicoLogica exe = new CuadroMagicoLogica(7);
        //exe.showMagicSquare();

        CrearMenu();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        bdpPanel = new BorderPane();
        bdpPanel.setTop(mnbPrincipal);
        Scene scene = new Scene(bdpPanel);
        scene.getStylesheets().add(getClass().getResource("/estilos/main.css").toString());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
        Conexion.crearConexion();
    }

    private void CrearMenu() {

        /* Menu primer parcial */
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());

        mitMemorama = new MenuItem("Memorama");
        mitMemorama.setOnAction(event -> new Memorama());

        mitCuadroMagico = new MenuItem("Cuadro magico");
        mitCuadroMagico.setOnAction(event -> new CuadroMagico());

        menParcial1 = new Menu("Primer Parcial");
        menParcial1.getItems().addAll(mitCalculadora,mitMemorama, mitCuadroMagico);

        /* Menu segundo parcial */

        mitImpresora = new MenuItem("Impresora");
        mitImpresora.setOnAction(event -> new Impresora());

        menParcial2 = new Menu("Segundo Parcial");
        menParcial2.getItems().addAll(mitImpresora);

        /* Menu salir */
        mitSalir = new MenuItem("Salir");
        menSalir = new Menu("Salir");
        menSalir.getItems().add(mitSalir);
        mitSalir.setOnAction(event -> System.exit(0));

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menParcial1,menParcial2,menSalir);

    }

    public static void main(String[] args) {
        launch();
    }
}