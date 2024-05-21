package com.example.tap2024.vistas;

import java.util.Calendar;

public class Imprimible extends Thread{

    private int no;
    private String name;
    private int noHojas;
    private String hora;
    private double progreso = 0;
    private boolean finalizado = false;

    Imprimible(int no){
        this.no = no;
        name = MakeAName.makeAName();
        noHojas = (int) Math.round(Math.random()*100);
        hora = Calendar.getInstance().getTime().toString();
    }

    public void run(){
        try{
            while(!finalizado) {
                if(progreso == 1.0){
                    finalizado = true;
                }else {
                    Thread.sleep(1000 * noHojas);
                    progreso = progreso + 1/noHojas;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
