package Game.Battle;

import Functionalities.UI;
import Game.Characters.Character;

/**
 * Implements the battle logic inside a battle between the player and the enemy.
 */
public class BattleLogic {
    private static final int START_OF_TURN = 0;

    private Character player;
    private Character enemy;
    private int turnNumber = START_OF_TURN;
    private boolean playerTurn = true;
    private boolean hasWon = false;
    private boolean hasBattleEnded = false;

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

    /**
     * Checks whether the battle ends after current turn.
     *
     * @param turn current player/enemy's turn.
     */
    private void checkBattleEnd(Turn turn) {
        if (turn.hasSurrendered) {
            hasWon = false;
            hasBattleEnded = true;
        }
        else if (!player.isAlive) {
            hasWon = false;
            hasBattleEnded = true;
        }
        else if (!enemy.isAlive) {
            hasWon = true;
            hasBattleEnded = true;
        }
    }

    /**
     * Handles the player and enemy turns. Calls UI to print player and enemy info after enemy turn.
     *
     */
    private void handleTurnOrder() {
        if (playerTurn) {
            turnNumber++;
            PlayerTurn turn = new PlayerTurn(player, enemy);
            turn.handleAction();
            checkBattleEnd(turn);
        }
        else {
            EnemyTurn turn = new EnemyTurn(player, enemy);
            turn.handleAction();
            UI.printCharacterInfo(player);
            UI.printCharacterInfo(enemy);
            checkBattleEnd(turn);
        }
        playerTurn = !playerTurn;
    }

    /**
     * Handles the battle sequence from start to finish.
     *
     * @return a boolean on whether the player has won the battle.
     */
    public boolean BattleSequence() {
        while (!hasBattleEnded) {
            handleTurnOrder();
        }
        return hasWon;
    }

}
