package Game.Battle;

import Game.Characters.Character;

/**
 * Represents the player's turn. Player can select different moves during his/her turn.
 */
public class PlayerTurn {
    private final Character player;

    /**
     * Constructs a PlayerTurn object.
     *
     * @param player The player character in battle.
     */
    public PlayerTurn(Character player) {
        this.player = player;
    }

    /**
     * Uses parser to scan for user input to carry out an action.
     *
     * @return A String representing the action of the player.
     */
    public String getAction() {
        return "0";
    }


}
