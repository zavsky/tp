package Game.Actions;

import Exceptions.InvalidActionException;
import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UIHandler;
import Game.Game;

public class DefaultAction extends Action {
    public boolean execute(Game game, Storage storage, UIHandler ui) throws RolladieException {
        throw new InvalidActionException();
    }
}
