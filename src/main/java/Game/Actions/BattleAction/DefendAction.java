package Game.Actions.BattleAction;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UIHandler;
import Game.Actions.Action;
import Game.Game;

public class DefendAction extends BattleAction {

    @Override
    public void executeBattleAction(Game game, Storage storage, UIHandler ui) throws RolladieException {
        String message = game.defend();
        ui.printMessage(message);
    }
}
