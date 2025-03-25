package Game;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Actions.Action;


public class Rolladie {
    private static final Game GAME = new Game();
    private static final UI UI = new UI();
    private static final Parser PARSER = new Parser();
    private static final Storage STORAGE = new Storage();

    public static void main(String[] args) {
        UI.printWelcomeMessage();
        UI.printMessage(GAME.toString());
        while(true) {
            try {
                String userInput = PARSER.readInput();
                Action action = PARSER.getAction(userInput);
                UI.printMessage("You chose to: " + action);

                boolean isTrue = true;

                if (action.execute(GAME, STORAGE, UI)) {
                    UI.exit();
                    break;
                }

                assert isTrue = false : "isTrue should be false";
            } catch (RolladieException e) {
                UI.printMessage(e.getErrorMessage());
            } catch (Exception e) {
                UI.printMessage(e.getMessage());
                break;
            }
        }
    }
}
