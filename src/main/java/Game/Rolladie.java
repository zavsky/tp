package Game;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI.UI;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Rolladie {

    /**
     * The main entry point of RollaDie game.
     *
     * @param args Command-line arguments.
     * @throws RolladieException Exception thrown if error occurs during game execution.
     */
    public static void main(String[] args) throws RolladieException {
        assert false : "dummy assertion set to fail";

        UI.printWelcomeMessage();
        String inputString = Parser.readInput();
        while (!inputString.equals("exit")) {
            if (inputString.equals("start")) {
                Game game = new Game();
                game.run();
                UI.printWelcomeMessage();
            } else if (inputString.equals("load")) {
                Game game = Storage.loadGame();
                game.run();
                UI.printWelcomeMessage();
            } else {
                UI.printErrorMessage("You can only use \"start\" or \"load\" bro");
            }
            inputString = Parser.readInput();
        }
        UI.printExitMessage();
    }
}
