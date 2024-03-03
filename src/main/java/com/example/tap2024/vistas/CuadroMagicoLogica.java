package com.example.tap2024.vistas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CuadroMagicoLogica {

    RandomAccessFile cuadroMagico;
    int n;

    public CuadroMagicoLogica(int n){
        try {
            cuadroMagico = new RandomAccessFile("cuadro.dat", "rw");
            for(int i = 0; i < Math.pow(n,2); i++){
                cuadroMagico.seek(i*4);
                cuadroMagico.writeInt(-1);
            }
            this.n = n;
            generateMagicSquare();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void generateMagicSquare(){
        boolean normalFlag = false;
        int i = 0;
        int j = n/2;
        man(i,j,1);
        for(int k = 2; k <= Math.pow(n,2);k++){
            normalFlag = false;
            if(i == 0 && j == (n-1)){
                i++;
            }else if(i == 0){
                i = n-1;
                j++;
            }else if (j == (n-1)){
                i--;
                j = 0;
            }else{
                i--;
                j++;
                normalFlag = true;
            }
            if(isNotEmpty(i,j)){
                if(normalFlag){
                    i = i + 2;
                    j--;
                }else {
                    i++;
                }
            }
            man(i,j,k);
        }
    }

    public boolean isNotEmpty(int i, int j){
        boolean result = true;
        try {
            cuadroMagico.seek(0);
            cuadroMagico.seek(((i * n) + j)*4);
            if(cuadroMagico.readInt() < 0) result = false;
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public void man(int i, int j, int data){
        try {
            cuadroMagico.seek(0);
            cuadroMagico.seek(((i*n)+j)*4);
            cuadroMagico.writeInt(data);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showMagicSquare(){
        try {
            for (int i = 0; i < Math.pow(n, 2); i++) {
                cuadroMagico.seek(i * 4);
                System.out.println(cuadroMagico.readInt());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showMagicSquareGUI(GridPane square){
        try {
            for (int i = 0; i < Math.pow(n, 2); i++) {
                cuadroMagico.seek(i * 4);
                square.add(new Button(String.format("%5s",String.valueOf(cuadroMagico.readInt()))),i%n,i/n);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
