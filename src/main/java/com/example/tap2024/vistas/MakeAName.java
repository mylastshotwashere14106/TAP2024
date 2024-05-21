package com.example.tap2024.vistas;

import java.util.Calendar;

public class MakeAName {

    public static String makeAName(){
        String name = "";
        String date = deleteSpaces(Calendar.getInstance().getTime().toString());
        name = date + randomExtension();
        return name;
    }

    public static String deleteSpaces(String text){
        String newText = "";
        for(int i = 0; i < text.length(); i++){
            if(!String.valueOf(text.charAt(i)).equals(" ")){
                newText = newText + String.valueOf(text.charAt(i));
            }
        }
        return newText;
    }

    public static String randomExtension(){
        String extensionName = "";
        int extension = (int) Math.round(Math.random() * 5);
        switch(extension){
            case 0:extensionName = ".txt";
            break;
            case 1:extensionName = ".pdf";
            break;
            case 2:extensionName = ".csv";
            break;
            case 3:extensionName = ".dat";
            break;
            case 4:extensionName = ".bin";
            break;
            default:extensionName = ".lamamadejavier";
        }
        return extensionName;
    }
}
