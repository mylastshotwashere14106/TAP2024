package com.example.tap2024.vistas;

import javafx.scene.control.ProgressBar;

import java.util.Calendar;

public class Imprimible extends Thread{

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNameN() {
        return name;
    }

    public void setNameN(String name) {
        this.name = name;
    }

    public int getNoHojas() {
        return noHojas;
    }

    public void setNoHojas(int noHojas) {
        this.noHojas = noHojas;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    private int no;
    private String name;
    private int noHojas;
    private String hora;
    private double progreso = 0;
    private boolean finalizado = false;
    public ProgressBar progressBar = new ProgressBar();

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
                    progressBar.setProgress(progreso);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
