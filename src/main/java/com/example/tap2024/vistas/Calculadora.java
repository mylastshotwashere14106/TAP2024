package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Scene escena;
    private VBox vContenedor;
    private GridPane gdpTeclado;
    private TextField txtPantalla;
    private Button[][] arBotones = new Button[4][4];
    private char[] arEtiquetas = {'7','8','9','/','4','5','6','*','1','2','3','-','0','.','=','+'};

    public Calculadora(){
        CrearUI();
        this.setTitle("Mi primer Calculadora :)");
        this.setScene(escena);
        this.show();
    }

    public void usarCalculadora(){
        CalculadoraLogica calcular = new CalculadoraLogica();
        String aRealizar = txtPantalla.getText();
        boolean valido = true;
        char operacion = 'a';
        valido = CheckInput.checkValidFormat(aRealizar);
            if(valido){
                operacion = CheckInput.getOperand(aRealizar);
                    switch (operacion) {
                        case '+': {
                            calcular.add(CheckInput.getOperatorA(txtPantalla.getText()), CheckInput.getOperatorB(txtPantalla.getText()));
                            txtPantalla.setText(calcular.getAnswer());
                        }
                        break;
                        case '-': {
                            calcular.subtract(CheckInput.getOperatorA(txtPantalla.getText()), CheckInput.getOperatorB(txtPantalla.getText()));
                            txtPantalla.setText(calcular.getAnswer());
                        }
                        break;
                        case '*': {
                            calcular.multiply(CheckInput.getOperatorA(txtPantalla.getText()), CheckInput.getOperatorB(txtPantalla.getText()));
                            txtPantalla.setText(calcular.getAnswer());
                        }
                        break;
                        case '/': {
                            calcular.divide(CheckInput.getOperatorA(txtPantalla.getText()), CheckInput.getOperatorB(txtPantalla.getText()));
                            if (calcular.getIsReal()) {
                                txtPantalla.setText(calcular.getAnswer());
                            } else {
                                txtPantalla.setText("Division by 0 is not defined");
                            }
                        }
                        break;
                        default:
                            ;
                    }
            }else {
                txtPantalla.setText("Please type a proper input");
            }
    }

    private void CrearUI() {
        txtPantalla = new TextField("0");
        gdpTeclado = new GridPane();
        CrearTeclado();
        vContenedor = new VBox(txtPantalla,gdpTeclado);
        vContenedor.setSpacing(5);
        escena = new Scene(vContenedor, 200,200);
        escena.getStylesheets()
                .add(getClass().getResource("/estilos/calculadora.css").toString());
    }

    private void CrearTeclado() {
        int pos = 0;
        char simbolo;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                arBotones[i][j] = new Button(arEtiquetas[pos]+"");
                arBotones[i][j].setPrefSize(50,50);
                int finalPos = pos;
                arBotones[i][j].setOnAction(event -> setValue(arEtiquetas[finalPos]));
                gdpTeclado.add(arBotones[i][j],j,i);

                if( arEtiquetas[pos] == '+' || arEtiquetas[pos] == '-' || arEtiquetas[pos] == '*' || arEtiquetas[pos] == '/' )
                    arBotones[i][j].setId("color-operador");

                pos++;
            }
        }
        arBotones[3][2].setOnAction(event -> usarCalculadora());
    }

    private void setValue(char simbolo) {
        txtPantalla.appendText(simbolo+"");
    }


}