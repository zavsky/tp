package Game;

import Functionalities.UI;
import java.util.Random;

/**
 *
 */
public class RollDice {
    public static final int MAX = 6;
    public static final int MIN = 1;
    public static final int MISS = 0;
    public static final int HIT = 10;
    public static final int CRUCIAL_HIT = 20;
    private static int first_dice;
    private static int second_dice;

    /**
     *
     * @return
     */
    public static int rollDice(){
        Random rand = new Random();
        first_dice = rand.nextInt(MAX - MIN + 1) + MIN;
        second_dice = rand.nextInt(MAX - MIN + 1) + MIN;

        printDiceImage(first_dice);
        printDiceImage(second_dice);

        return first_dice + second_dice;
    }


    private static void printDiceImage(int diceValue){
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
    //2,3,4,5,6,7,8,9,10,11,12
    // hit, crucial, miss
    // 1-4 --> miss, 5 -9 = hit, 10-12 = crucial hit

    /**
     *
     * @param diceValue
     * @return
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
