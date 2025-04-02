package Game.Characters;

/**
 * A class to create the player object.
 */
public class Player extends Character {
    private static final String playerModel =
            "      __      _\n" +
                    "     /__\\__  //\n" +
                    "    //_____\\///\n" +
                    "   _| /-_-\\)|/_\n" +
                    "  (___\\ _ //___\\\n" +
                    "  (  |\\\\_/// * \\\\\n" +
                    "   \\_| \\_((*   *))\n" +
                    "   ( |__|_\\\\  *//\n" +
                    "   (o/  _  \\_*_/\n" +
                    "   //\\__|__/\\\n" +
                    "  // |  | |  |\n" +
                    " //  _\\ | |___)\n" +
                    "//  (___|";

    /**
     * Constructor to construct a player object.
     *
     * @param health an integer array to represent the health bar of player.
     * @param attack an integer to represent the damage value in an attack.
     * @param defense an integer to represent the defense value.
     * @param name a string to represent the name of the player.
     */
    public Player(String name) {
        super(new int[] {100}, 10, 10, name);
    }

    /**
     * Get the defense value of player
     *
     * @return current defenseValue depending on whether player used DefendAction the previous turn
     */
    @Override
    public int getDefenseValue() {
        if (isDefending) {
            return defenseValue * 3;
        }
        return defenseValue;
    }

    @Override
    public String toString() {
        return playerModel + "\n" + super.toString();
    }
}
