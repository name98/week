package com.example.week2.items;

import android.graphics.Color;

import java.util.ArrayList;

public class MyColors {
    public static int getColor(String color){
        switch (color){
            case "RED":
                return Color.rgb(255, 90, 95);
            case "YELLOW":
                return Color.rgb(255, 255, 0);
            case "MAGENTA":
                return Color.rgb(255, 0, 255);
            case "OPERA_MAUVE":
                return Color.rgb(193, 131, 159);
            case "DARK_VANILLA":
                return Color.rgb(189, 190, 169);
            default:
                return Color.rgb(255, 255, 255);
        }
    }
    public static int getDarkColor(String color){
        switch (color){
            case "RED":
                return Color.rgb(200, 90, 95);
            case "YELLOW":
                return Color.rgb(255, 200, 0);
            case "MAGENTA":
                return Color.rgb(255, 0, 170);
            case "OPERA_MAUVE":
                return Color.rgb(131, 131, 159);
            case "DARK_VANILLA":
                return Color.rgb(172, 190, 140);
            default:
                return Color.rgb(0, 0, 0);
        }
    }

    public static ArrayList<String> getDataColors(){
        ArrayList<String> temp = new ArrayList<>();
        temp.add("RED");
        temp.add("YELLOW");
        temp.add("MAGENTA");
        temp.add("OPERA_MAUVE");
        temp.add("DARK_VANILLA");
        temp.add("WHITE");
        return temp;
    }


}
