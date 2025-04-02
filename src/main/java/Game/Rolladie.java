package Game;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI.UI;
import Game.Menu.MenuSystem;
import Game.Menu.MenuSystem.MenuAction;
import Game.Menu.TerminalUtils;

/**
 * Entry point of the Rolladie application
 * Initializes the main menu and starts interactions with the user
 */

public class Rolladie {

    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";

        final MenuSystem menuSystem = new MenuSystem();

        while (true) {
            try {
                Map<String, MenuAction> mainMenu = new LinkedHashMap<>();

                mainMenu.put("Start Game", () -> { 
                    Game game = new Game();
                    gameStart(game, menuSystem);
                    return false; });
                mainMenu.put("Load Game", () -> { 
                    try {
                        Game game = Storage.loadGame(); // May throw RolladieException
                        gameStart(game, menuSystem);
                    } catch (RolladieException e) {
                        UI.printErrorMessage("Failed to load game: " + e.getMessage());
                        return true;
                    }
                    return false; });
                mainMenu.put("Exit", () -> { UI.printExitMessage(); System.exit(0); return true; });

                menuSystem.displayMenu("Main Menu", mainMenu);
            } catch (IOException e) {
                UI.printErrorMessage(e.getMessage());
            }
        }

        // String inputString = Parser.readInput();
        // while (!inputString.equals("exit")) {
        //     Game game;
        //     try {
        //         switch (inputString) {
        //         case "start":
        //             game = new Game();
        //             game.run();
        //             UI.printWelcomeMessage();
        //             break;
        //         case "load":
        //             game = Storage.loadGame();
        //             game.run();
        //             UI.printWelcomeMessage();
        //             break;
        //         default:
        //             throw new RolladieException("You can only use \"start\", \"load\" or \"exit\" bro");
        //         }
        //     } catch (RolladieException e) {
        //         UI.printErrorMessage(e.getMessage());
        //     }
        //     inputString = Parser.readInput();
        // }
        // UI.printExitMessage();
    }

    private static void gameStart(Game game, MenuSystem menuSystem) {
        menuSystem.exitPrivateMode();
        try {
            TerminalUtils.resetTerminal();
        } catch (IOException | InterruptedException e) {
            UI.printErrorMessage(e.getMessage());
        }
        game.run();
        UI.printWelcomeMessage();
    }
}
