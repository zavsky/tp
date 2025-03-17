package Game.Battle;

import Game.Characters.Character;

/**
 * Represents a battle event in which player fights an enemy.
 */
public class Battle {
    private final Character player;
    private final Character enemy;
    private boolean hasWon;
    /**
     * Constructs a Battle event object.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character that player is facing.
     */
    public Battle(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
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
