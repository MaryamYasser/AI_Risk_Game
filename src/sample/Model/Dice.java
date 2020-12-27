package sample.Model;

import java.util.Random;

public  class Dice {
    Random r = new Random();
    int value;
    int sides;


    public Dice() {
        this.sides = 6;
        this.value=this.throwDice();
    }

    public int getValue() {
        return value;
    }

    public int throwDice(){

        int dice = r.nextInt(6) + 1;
        return dice;
    }

}



