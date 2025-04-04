package game.events.battle;

import game.actions.Action;

public abstract class Turn {
    //protected enum Action {
    //  ATTACK, DEFEND, SURRENDER
    //}
    public boolean hasSurrendered = false;
    protected Action currentAction;

}
