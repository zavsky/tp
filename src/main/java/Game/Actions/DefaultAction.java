package Game.Actions;

import Exceptions.InvalidActionException;
import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Game;

public class DefaultAction extends Action {
    public boolean execute(Game game, Storage storage, UI ui) throws RolladieException {
        throw new InvalidActionException();
    }
}
