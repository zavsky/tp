package Game.Events.Battle;

public abstract class Turn {
    protected enum Action {
        ATTACK, DEFEND, SURRENDER
    }
    protected Action currentAction;

}
