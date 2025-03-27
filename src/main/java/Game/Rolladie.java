package Game;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;


public class Rolladie {
    /**
     * Main menu of Rolladie Game
     */

    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";

        UI.printWelcomeMessage();
        String inputString = UI.readInput();
        while (!inputString.equals("exit")) {
            Game game;
            switch(inputString) {
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
                UI.printMessage("You can only use \"start\" or \"load\" bro");
            }
            inputString = UI.readInput();
        }
        UI.printMessage("Leaving so soon? I expected more from you!");
    }
}
