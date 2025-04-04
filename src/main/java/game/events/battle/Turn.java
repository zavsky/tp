package game.events.battle;

import game.actions.Action;

public abstract class Turn {
    protected Action currentAction;
    public boolean hasSurrendered = false;
}
