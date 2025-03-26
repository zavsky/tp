package Game.Events.Battle;

import Functionalities.Parser;
import Functionalities.UI;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Events.Event;
import Game.Events.EventType;

/**
 * Represents a battle event in which player fights an enemy.
 */
public class Battle extends Event {
    private final Enemy enemy;
    private boolean hasWon;
    /**
     * Constructs a Battle event object.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character that player is facing.
     */
/*    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        hasWon = false;
    }*/

    public Battle(Player player) {
        super(player);
        int[] enemyHealth = {50};
        this.enemy = new Enemy(enemyHealth, 15, 5, "Goblin");
        this.hasWon = false;
    }

    @Override
    public void run() {
        UI.battleEntry(this.enemy);
        startBattle();
        UI.battleExit(this.enemy, this.player);
    }

    public EventType getEventType() {
        return EventType.BATTLE;
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
