package Game.Events.Battle;

import Game.Characters.Character;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Events.Event;
import Game.Events.EventType;

/**
 * Represents a battle event in which player fights an enemy.
 */
public class Battle {
    private final Player player;
    private final Enemy enemy;
    private boolean hasWon;
    /**
     * Constructs a Battle event object.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character that player is facing.
     */
    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
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


    @Override
    public EventType getEventType() {
        return EventType.BATTLE;
    }

    @Override
    public String toString() {
        return super.toString() + "Battle";
    }
}
