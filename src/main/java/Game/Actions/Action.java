package Game.Actions;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UIHandler;
import Game.Game;

public abstract class Action {
    public abstract boolean execute(Game game, Storage storage, UIHandler ui) throws RolladieException;
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
