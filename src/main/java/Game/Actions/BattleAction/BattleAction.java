package Game.Actions.BattleAction;

import Exceptions.InvalidActionException;
import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Actions.Action;
import Game.Events.EventType;
import Game.Game;

public abstract class BattleAction extends Action {

    @Override
    public void execute(Game game, Storage storage, UI ui) throws RolladieException {
        if (game.getEventType() == EventType.BATTLE) {
            executeBattleAction(game, storage, ui);
        } else {
            throw new InvalidActionException();
        }
    }

    public abstract void executeBattleAction(Game game, Storage storage, UI ui) throws RolladieException;
}
