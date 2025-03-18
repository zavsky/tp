package Game;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Actions.Action;


public class Rolladie {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final Game GAME = new Game();
    private static final UI UI = new UI();
    private static final Parser PARSER = new Parser();
    private static final Storage STORAGE = new Storage();

    public static void main(String[] args) {
        UI.printWelcomeMessage();
        UI.printMessage(GAME.toString());
        while(true) {
            try {
                String inputLine = PARSER.readInput();
                UI.printMessage("You chose to: " + inputLine);
                Action action = PARSER.getAction(inputLine);
                action.execute(GAME, STORAGE, UI);
            } catch (RolladieException e) {
                UI.printMessage(e.getErrorMessage());
            } catch (Exception e) {
                UI.printMessage(e.getMessage());
                System.exit(0);
            }
        }
    }
}
