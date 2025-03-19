package Game.Actions;

import Functionalities.Storage;
import Functionalities.UI;
import Game.Game;

public class ExitAction extends Action {
    public void execute(Game game, Storage storage, UI ui) {
        ui.printMessage("Leaving so soon? I expected more from you!");
        System.exit(0);
    }
}
