package com.example.tap2024.vistas;

import java.util.ArrayList;

public class Imprimir extends Thread{

    private static int noArchivoInt = 0;
    private boolean continuar = true;
    private static int noArchivo = 0;
    ArrayList<Imprimible> porImprimir = new ArrayList<>();

    public void run(){
        while(continuar){
            for(int i = 0; i < porImprimir.size();i++){
                try {
                    porImprimir.get((porImprimir.size()-1)-i).start();
                    porImprimir.get((porImprimir.size()-1)-i).join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void addTask(){
        porImprimir.add(new Imprimible(noArchivoInt));
    }

}
