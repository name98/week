package com.example.ex1.ex2;

import java.util.Random;

public class MyRandom {
    private int number;
    Random random = new Random();

    public int nextInt(){
        number = random.nextInt();
        if(number<0)
            return Math.abs(number);
        if(number == 0)
            return number;
        else return number;

    }
    public int nextNotZero(){
        number = random.nextInt();
        if(number<0)
            return Math.abs(number);
        if(number == 0)
            return number+1;
        else return number;
    }
}
