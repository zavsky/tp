package Game.Actions;

import Exceptions.RolladieException;
import Functionalities.UI;
import Game.Game;

public class StartAction {
    public void execute() {
        Game game = new Game();
        game.run();
        UI.printWelcomeMessage();
    }
}
