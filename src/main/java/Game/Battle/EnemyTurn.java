package Game.Battle;

import Game.Actions.Action;
import Game.Actions.AttackAction;
import Game.Actions.DefendAction;
import Game.Actions.EndAction;
import Game.Actions.ExitAction;
import Game.Characters.Character;

import static Functionalities.Parser.getAction;

/**
 * Represents the enemy's turn. Enemy can only attack for now.
 */
public class EnemyTurn extends Turn {
    private final Character player;
    private final Character enemy;


    /**
     * Constructs a EnemyTurn object to represent current enemy's turn.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character in battle
     */
    public EnemyTurn(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void handleAction() {
        enemy.attack(player);
    }


}
