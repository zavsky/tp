package oldgame.events.battle;

import oldgame.actions.Action;

public abstract class Turn {
    protected Action currentAction;
    public boolean hasSurrendered = false;
}
