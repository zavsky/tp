package Game.Actions;

import Functionalities.Storage;
import Functionalities.UI;
import Game.Game;

public class ExitAction extends Action {
    public void execute() {
        UI.printMessage("Leaving so soon? I expected more from you!");
    }
}
