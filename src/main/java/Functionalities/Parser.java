package Functionalities;

import Game.Actions.Action;
import Game.Actions.BattleAction.AttackAction;
import Game.Actions.DefaultAction;
import Game.Actions.BattleAction.DefendAction;
import Game.Actions.BattleAction.EndAction;
import Game.Actions.ExitAction;
import Game.Actions.HelpAction;
import Game.Actions.TravelAction.MoveAction;
import Game.Actions.StartAction;

import java.util.Scanner;

/**
 * Parser class to handle user inputs.
 * Parser object is created to take in user inputs and handle them.
 */

public class Parser {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ACTION_START = "start";
    private static final String ACTION_MOVE = "move";
    private static final String ACTION_HELP = "help";
    private static final String ACTION_EXIT = "exit";
    private static final String ACTION_END = "end";
    private static final String ACTION_DEFEND = "defend";
    private static final String ACTION_ATTACK = "attack";

    private String[] splitActionWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        if (split.length == 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }

    public String readInput() {
        String inputLine = SCANNER.nextLine().toLowerCase();
        return inputLine;
    }

    public Action getAction(String userInputString) {
        final String[] actionTypeAndParams = splitActionWordAndArgs(userInputString);
        final String actionType = actionTypeAndParams[0].toLowerCase();
        final String actionArgs = actionTypeAndParams[1];
        switch (actionType) {
        case ACTION_START:
            return new StartAction();
        case ACTION_MOVE:
            return new MoveAction();
        case ACTION_HELP:
            return new HelpAction();
        case ACTION_EXIT:
            return new ExitAction();
        case ACTION_END:
            return new EndAction();
        case ACTION_DEFEND:
            return new DefendAction();
        case ACTION_ATTACK:
            return new AttackAction();
        default:
            return new DefaultAction();
        }
    }
}
