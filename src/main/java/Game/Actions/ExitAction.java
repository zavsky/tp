package Game.Actions;

import Functionalities.Storage;
import Functionalities.UIHandler;
import Game.Game;

public class ExitAction extends Action {
    public boolean execute(Game game, Storage storage, UIHandler ui) {
        ui.printMessage("Leaving so soon? I expected more from you!");
        return true;
    }
}
