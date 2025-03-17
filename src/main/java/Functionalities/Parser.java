package Functionalities;

import java.io.IOException;
import java.util.Scanner;

/**
 * Parser class to handle user inputs.
 * Parser object is created to take in user inputs and handle them.
 */
public class Parser {
    public static final Scanner in = new Scanner(System.in);
    public boolean inBattle = false;

    public void scanMessage() {
        if (inBattle) {
            scanBattleMessage();
        }
    }

    private void scanBattleMessage() {

    }
}
