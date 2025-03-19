package Game.Battle;

import Game.Actions.Action;
import Game.Actions.AttackAction;
import Game.Actions.DefendAction;
import Game.Characters.Character;

import static Functionalities.Parser.getAction;

/**
 * Represents the player's turn. Player can select different moves during his/her turn.
 */
public class PlayerTurn extends Turn {
    private final Character player;
    private final Character enemy;

    /**
     * Constructs a PlayerTurn object.
     *
     * @param player The player character in battle.
     */
    public PlayerTurn(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    /**
     * Uses parser to scan for user input to carry out an action.
     *
     * @return A String representing the action of the player.
     */
    private Action currAction() {
        return getAction();
    }

    private void handleAction() {
        Action currentAction = currAction();
        if (currentAction instanceof AttackAction) {
            player.attack(enemy);
        }
        else if (currentAction instanceof DefendAction) {

        }

    }


}
