package Game;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;

/**
 * Entry point of the Rolladie application
 * Initializes the main menu and starts interactions with the user
 */

public class Rolladie {

    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";

        UI.printWelcomeMessage();
        String inputString = UI.readInput();
        while (!inputString.equals("exit")) {
            Game game;
            try {
                switch (inputString) {
                case "start":
                    game = new Game();
                    game.run();
                    UI.printWelcomeMessage();
                    break;
                case "load":
                    game = Storage.loadGame();
                    game.run();
                    UI.printWelcomeMessage();
                    break;
                default:
                    throw new RolladieException("You can only use \"start\", \"load\" or \"exit\" bro");
                }
            } catch (RolladieException e) {
                UI.printMessage(e.getMessage());
            }
            inputString = UI.readInput();
        }
        UI.printMessage("Leaving so soon? I expected more from you!");
    }
}
