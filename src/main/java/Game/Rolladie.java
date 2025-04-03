package Game;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

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

    private static Terminal terminal = null;

    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";

        try {
            terminal = new DefaultTerminalFactory().createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            TerminalUtils.saveTerminalState(); // Before launching Lanterna
            MenuSystem menuSystem = new MenuSystem(terminal);
            menuSystem.enterPrivateMode();
            
            Map<String, MenuAction> mainMenu = new LinkedHashMap<>();

            mainMenu.put("Start Game", () -> { 
                Game game = new Game(terminal, menuSystem);
                gameStart(game, menuSystem);
                return false; });
            mainMenu.put("Load Game", () -> { 
                try {
                    Game game = Storage.loadGame(terminal); // May throw RolladieException
                    gameStart(game, menuSystem);
                } catch (RolladieException e) {
                    e.printStackTrace();
                    return true;
                }
                return false; });
            mainMenu.put("Exit", () -> { 
                try {
                    UI.printExitMessage(terminal, 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                } System.exit(0); return true; });

            menuSystem.displayMenu("Main Menu", mainMenu);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void gameStart(Game game, MenuSystem menuSystem) {
        try {
            menuSystem.exitPrivateMode();
            TerminalUtils.resetTerminal();
            // Ensure console input is visible
            System.out.print("\033[?25h"); // Show cursor (Unix)
            System.out.flush();

            game.run();

            // Prepare for returning to Lanterna
            TerminalUtils.prepareForLanterna();
            
            menuSystem.reinitializeTerminal(); // Add this method to MenuSystem
            menuSystem.enterPrivateMode();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            try {
                TerminalUtils.resetTerminalOverkill();
            } catch (Exception ignored) {}
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
