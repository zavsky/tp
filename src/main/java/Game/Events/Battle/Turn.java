package Game.Events.Battle;

import Game.Actions.Action;

public abstract class Turn {
    //protected enum Action {
      //  ATTACK, DEFEND, SURRENDER
    //}
    protected Action currentAction;
    public boolean hasSurrendered = false;

}
