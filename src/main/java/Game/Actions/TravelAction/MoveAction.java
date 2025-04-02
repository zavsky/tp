package Game.Actions.TravelAction;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UIHandler;
import Game.Game;

public class MoveAction extends TravelAction {

    @Override
    public void executeTravelAction(Game game, Storage storage, UIHandler ui) throws RolladieException {
        String message = game.move();
        ui.printMessage(message);
    }
}
