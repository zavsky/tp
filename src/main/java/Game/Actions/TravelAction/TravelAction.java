package Game.Actions.TravelAction;

import Exceptions.InvalidActionException;
import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Actions.Action;
import Game.Events.EventType;
import Game.Game;

public abstract class TravelAction extends Action {

    @Override
    public void execute(Game game, Storage storage, UI ui) throws RolladieException {
        if (game.getEventType() == EventType.TRAVEL) {
            executeTravelAction(game, storage, ui);
        } else {
            throw new InvalidActionException();
        }
    }

    public abstract void executeTravelAction(Game game, Storage storage, UI ui) throws RolladieException;
}
