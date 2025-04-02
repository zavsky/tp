package Game.Actions.BattleAction;

import Exceptions.InvalidActionException;
import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UIHandler;
import Game.Actions.Action;
import Game.Events.EventType;
import Game.Game;

public abstract class BattleAction extends Action {

    public boolean execute(Game game, Storage storage, UIHandler ui) throws RolladieException {
        if (game.getEventType() == EventType.BATTLE) {
            executeBattleAction(game, storage, ui);
            return false;
        } else {
            throw new InvalidActionException();
        }
    }

    public abstract void executeBattleAction(Game game, Storage storage, UIHandler ui) throws RolladieException;
}
