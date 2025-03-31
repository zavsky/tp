package Game.Characters;

import java.util.Arrays;

import static Functionalities.Storage.SAVE_DELIMITER;

import Game.Currency.Gold;


/**
 * A class to create the player object.
 */
public class Player extends Character {
    private Gold gold;
    private int attackBonus;
    private int defenseBonus;

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
    public Player(int[] health, int attack, int defense, String name) {
        super(health, attack, defense, name);
        this.gold = new Gold(0);
    }

    public Player(int[] healthBars, int attackValue, int defenseValue, String characterName, int maxHealth) {
        super(healthBars, attackValue, defenseValue, characterName, maxHealth);
    }

    /**
     * Gold amount earned by player
     * @param amount An integer to represent the amount earned.
     */
    public void earnGold(int amount) {
        this.gold = gold.earnGold(amount);
    }

    /**
     * Gold amount spent by player.
     * @param amount An integer to represent the amount spent.
     */
    public void spendGold(int amount) {
        this.gold = gold.spendGold(amount);
    }

    /**
     * Get the defense value of player
     *
     * @return current defenseValue depending on whether player used DefendAction the previous turn
     */
    @Override
    public int getDefenseValue() {
        if (isDefending) {
            return (defenseValue + defenseBonus) * 3;
        }
        return defenseValue + defenseBonus;
    }

    /**
     * Return the character's attack value.
     *
     * @return an integer representing the character's attack value.
     */
    public int getAttackValue() {
        return attackValue + attackBonus;
    }

    /**
     * Set player's attack bonus value.
     *
     * @param bonus dice outcome for attack action.
     */
    public void setAttackBonus(int bonus){
        attackBonus = bonus;
    }

    /**
     * Set player's defend bonus value.
     *
     * @param bonus dice outcome for defend action.
     */
    public void setDefenseBonus(int bonus){
        defenseBonus = bonus;
    }


    /**
     * Return the information of a player.
     *
     * @return A string that show information of a player.
     */
    @Override
    public String toString() {
        return playerModel + "\n" + super.toString();
    }
}
