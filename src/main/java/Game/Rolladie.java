package Game;

import Exceptions.RolladieException;
import Functionalities.UI;
import Game.Actions.ExitAction;
import Game.Actions.StartAction;


public class Rolladie {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) throws RolladieException {
        assert false : "dummy assertion set to fail";

        UI.printWelcomeMessage();
        String inputString = UI.readInput();
        while (!inputString.equals("exit")) {
            if (inputString.equals("start")) {
                new StartAction().execute();
            } else {
                UI.printMessage("You can only use \"Start\" bro");
            }
            inputString = UI.readInput();
        }
        new ExitAction().execute();
    }
}
