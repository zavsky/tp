package Functionalities;

import Game.Actions.Action;
import Game.Actions.BattleAction.AttackAction;
import Game.Actions.DefaultAction;
import Game.Actions.BattleAction.DefendAction;
import Game.Actions.BattleAction.FleeAction;
import Game.Actions.ExitAction;
import Game.Actions.HelpAction;
import Game.Actions.ShopAction.BuyAction;
import Game.Actions.ShopAction.LeaveAction;
import Game.Actions.ShopAction.SellAction;

import java.util.Scanner;

import Functionalities.UI.UI;

/**
 * Parser class to handle user inputs.
 * Parser object is created to take in user inputs and handle them.
 */

public class Parser {
    private static final Scanner scanner = new Scanner(System.in);


    private static boolean checkUsingParseInt(String input) {
        try {
            // Attempt to parse the input string to an integer
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            // If parsing fails, the input is not a valid integer
            return false;
        }
    }

    /**
     * Gets the action integer value from the user's input.
     * @param userInputString String that user inputs.
     * @return Integer value of string.
     */
    public static int getActionInteger(String userInputString) {
        if (userInputString.length() == 1 && checkUsingParseInt(userInputString)) {
            return Integer.parseInt(userInputString);
        }
        else {
            UI.printMessage("Invalid action");
            return getActionInteger(readInput());
        }
    }

    public static String readInput() {
        return scanner.nextLine().toLowerCase();
    }

}
