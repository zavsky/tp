package Functionalities;

import Game.Game;
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
import Game.Equipment.Equipment;
import Game.Menu.MenuSystem;
import Game.Menu.MenuSystem.MenuAction;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.googlecode.lanterna.terminal.Terminal;

import Exceptions.RolladieException;
import Functionalities.UI.UI;

/**
 * Parser class to handle user inputs.
 * Parser object is created to take in user inputs and handle them.
 */

public class Parser {
    // private static final String ACTION_MOVE = "move";
    // private static final String ACTION_HELP = "help";
    // private static final String ACTION_EXIT = "exit";
    // private static final String ACTION_FLEE = "flee";
    // private static final String ACTION_DEFEND = "defend";
    // private static final String ACTION_DEFEND_SHORT = "d";
    // private static final String ACTION_ATTACK = "attack";
    // private static final String ACTION_ATTACK_SHORT = "a";
    // private static final String ACTION_BUY = "buy";
    // private static final String ACTION_SELL = "sell";
    // private static final String ACTION_LEAVE = "leave";

    // private static final Scanner scanner = new Scanner(System.in);


    // protected static String[] splitActionWordAndArgs(String rawUserInput) {
    //     final String[] split = rawUserInput.trim().split("\\s+", 2);
    //     if (split.length == 2) {
    //         return split;
    //     } else {
    //         return new String[]{split[0], ""};
    //     }
    // }

    // public static Action getAction(String userInputString) {
    //     final String[] actionTypeAndParams = splitActionWordAndArgs(userInputString);
    //     final String actionType = actionTypeAndParams[0].toLowerCase();
    //     final String actionArgs = actionTypeAndParams[1];
    //     switch (actionType) {
    //     case ACTION_HELP:
    //         return new HelpAction();
    //     case ACTION_EXIT:
    //         return new ExitAction();
    //     case ACTION_FLEE:
    //         return new FleeAction();
    //     case ACTION_DEFEND, ACTION_DEFEND_SHORT:
    //         return new DefendAction();
    //     case ACTION_ATTACK, ACTION_ATTACK_SHORT:
    //         return new AttackAction();
    //     default:
    //         UI.printMessage("Invalid action");
    //         return getAction(readInput());
    //     }
    // }

    // public static Action getShopAction(String userInputString) {
    //     final String[] actionTypeAndParams = splitActionWordAndArgs(userInputString);
    //     final String actionType = actionTypeAndParams[0].toLowerCase();
    //     final String actionArgs = actionTypeAndParams[1];
    //     switch (actionType) {
    //     case ACTION_HELP:
    //         return new HelpAction();
    //     case ACTION_EXIT:
    //         return new ExitAction();
    //     case ACTION_BUY:
    //         return new BuyAction(actionArgs);
    //     case ACTION_SELL:
    //         return new SellAction(actionArgs);
    //     case ACTION_LEAVE:
    //         return new LeaveAction();
    //     default:
    //         UI.printMessage("Invalid action");
    //         return getAction(readInput());
    //     }
    // }

    // public static String readInput() {
    //     String inputLine = scanner.nextLine().toLowerCase();
    //     return inputLine;
    // }

    public static Action getAction (MenuSystem menuSystem) {
        Map<String, Action> actionMenu = new LinkedHashMap<>();

        actionMenu.put("Attack", new AttackAction());
        actionMenu.put("Defend", new DefendAction());
        actionMenu.put("Flee", new FleeAction());
        actionMenu.put("Exit", new ExitAction());

        return menuSystem.displayParserMenu("Battle Options", actionMenu);
    }

    public Action getShopAction (MenuSystem menuSystem, Equipment[] playerEquipments, Equipment[] shopEquipments) {
        Map<String, Action> actionMenu = new LinkedHashMap<>();

        actionMenu.put("Buy", showBuyMenu(menuSystem, playerEquipments, shopEquipments));
        actionMenu.put("Sell", showSellMenu(menuSystem, playerEquipments, shopEquipments));
        actionMenu.put("Leave", new LeaveAction());

        return menuSystem.displayParserMenu("Shop Options", actionMenu);
    }

    private Action showBuyMenu(MenuSystem menuSystem, Equipment[] playerEquipments, Equipment[] shopEquipments) {
        Map<String, Integer> buyMenu = new LinkedHashMap<>();
        int i = 1;

        for (Equipment equipment : shopEquipments) {
            buyMenu.put(i + ". " + equipment.getName() + " - " + equipment.getValue() + " gold", i);
            i++;
        }

        buyMenu.put("Return", -1);

        int selectedOption =  menuSystem.displayBuyMenu("What to purchase", buyMenu);

        if (selectedOption == -1) {
            return getShopAction(menuSystem, playerEquipments, shopEquipments);
        }

        return new BuyAction(Integer.toString(selectedOption));
    }

    private Action showSellMenu(MenuSystem menuSystem, Equipment[] playerEquipments, Equipment[] shopEquipments) {
        Map<String, Integer> sellMenu = new LinkedHashMap<>();
        int i = 1;

        for (Equipment equipment : playerEquipments) {
            sellMenu.put(i + ". " + equipment.getName() + " - " + (equipment.getValue() / 2) + " gold", i);
            i++;
        }

        sellMenu.put("Return", -1);

        int selectedOption = menuSystem.displayBuyMenu("What to sell?", sellMenu);

        if (selectedOption == -1) {
            return getShopAction(menuSystem, playerEquipments, shopEquipments);
        } else {
            return new SellAction(Integer.toString(selectedOption));
        }
    }
}
