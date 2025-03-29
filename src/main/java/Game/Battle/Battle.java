package Game.Battle;

import Exceptions.ExceptionMessage;
import Exceptions.RolladieException;
import Game.Characters.Enemy;
import Game.Characters.Player;

/**
 * Represents a battle event in which player fights an enemy.
 */
public class Battle {
    private final Player player;
    private final Enemy enemy;
    private boolean hasWon;

    /**
     * Constructs a valid Battle event object.
     * Initialises hasWon to be false at start of battle.
     * @param player The player character in battle.
     * @param enemy The enemy character that player is facing.
     */
    public Battle(Player player, Enemy enemy) throws ExceptionMessage {
        final int FIRST_HEALTH_BAR = 0;
        if (player.getHealthBars()[FIRST_HEALTH_BAR] <= 0) {
            throw new ExceptionMessage("Player health bar is empty.");
        }
        else if (enemy.getHealthBars()[FIRST_HEALTH_BAR] <= 0) {
            throw new ExceptionMessage("Enemy health bar is empty.");
        }
        if (player.getAttackValue() < 0) {
            throw new ExceptionMessage("Player attack value is negative.");
        }
        else if (enemy.getAttackValue() < 0) {
            throw new ExceptionMessage("Enemy attack value is negative.");
        }
        this.player = player;
        this.enemy = enemy;
        hasWon = false;
    }

    public Battle() {
        this.player = null;
        int[] enemyHealth = {50};
        this.enemy = new Enemy(enemyHealth, 15, 5, "Goblin");
    }

    /**
     * Starts the battle.
     * When battle starts, creates a BattleLogic object to handle battle logic.
     *
     * @return A boolean representing whether the player has won the battle.
     */
    public boolean startBattle() {
        BattleLogic battleLogic = new BattleLogic(player, enemy);
        hasWon = battleLogic.BattleSequence();

        return hasWon;
    }
}
