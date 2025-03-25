package Game.Actions;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Game;

public abstract class Action {
    public abstract boolean execute(Game game, Storage storage, UI ui) throws RolladieException;
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
