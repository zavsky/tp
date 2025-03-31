package Game;

import Functionalities.UI;
import java.util.Random;

/**
 * A class to represent the dice.
 */
public class RollDice {
    /**
     * An integer to represent the maximum dice value.
     */
    public static final int MAX = 6;
    /**
     * An integer to represent the minimum dice value.
     */
    public static final int MIN = 1;
    /**
     * No bonus point given for MISS case.
     */
    public static final int MISS = 0;
    /**
     * 10 bonus point given for HIT case.
     */
    public static final int HIT = 10;
    /**
     * 20 bonus point given for CRUCIAL_HIT case.
     */
    public static final int CRUCIAL_HIT = 20;
    private static int first_dice;
    private static int second_dice;

    /**
     * Return the outcome of rolling 2 dice.
     * @return An integer to represent the outcome of rolling dice.
     */
    public static int rollDice(){
        Random rand = new Random();
        first_dice = rand.nextInt(MAX - MIN + 1) + MIN;
        second_dice = rand.nextInt(MAX - MIN + 1) + MIN;

        printDiceImage(first_dice);
        printDiceImage(second_dice);

        return first_dice + second_dice;
    }

    /**
     * Method to print the dice image to the terminal according to the outcome of dice.
     *
     * @param diceValue An integer to represent the current outcome of rolling dice.
     */
    public static void printDiceImage(int diceValue){
        switch (diceValue){
        case 1:
            System.out.println(UI.dieFace1);
            break;
        case 2:
            System.out.println(UI.dieFace2);
            break;
        case 3:
            System.out.println(UI.dieFace3);
            break;
        case 4:
            System.out.println(UI.dieFace4);
            break;
        case 5:
            System.out.println(UI.dieFace5);
            break;
        case 6:
            System.out.println(UI.dieFace6);
            break;
        }
    }

    /**
     * Return the effect of rolling dice.
     * Three possible outcome: MISS, HIT, CRUCIAL_HIT
     *
     * @param diceValue Integer to represent the total outcome of rolling 2 dice
     * @return Integer to represent the effect of rolling dice.
     */
    public static int diceOutcome(int diceValue){

        if(diceValue < 5){
            System.out.println("Öops, you got 0 bonus point for this action :(");
            return MISS;
        }else if(diceValue < 10){
            System.out.println("10 bonus points for this action!");
            return HIT;
        }else{
            System.out.println("20 bonus points for this action :)");
            return CRUCIAL_HIT;
        }

    }



}
