package Game.Battle;

import Game.Characters.Character;

/**
 * Implements the battle logic inside a battle between the player and the enemy
 */
public class BattleLogic {
    private static final int START_OF_TURN = 0;

    private Character player;
    private Character enemy;
    private int turn = START_OF_TURN;
    private boolean playerTurn = true;
    private boolean hasWon = false;

    /**
     * Constructs a Battle Logic object.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character that player is facing.
     */
    public BattleLogic(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
    }


    public boolean BattleSequence() {
        while (player.isAlive || enemy.isAlive) {
            if (playerTurn) {

            }
            else {

            }
        }
        return hasWon;
    }

}
