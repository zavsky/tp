package Game.Actions;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Events.EventType;
import Game.Game;

public abstract class Action {
    public abstract void execute(Game game, Storage storage, UI ui) throws RolladieException;
}
