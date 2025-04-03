package Game.Characters;

import Functionalities.UI.BattleUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import static Functionalities.Storage.SAVE_DELIMITER;

/**
 * A class to represent characters in the game.
 */
public class Character {
    protected boolean isDefending = false;
    protected int[] healthBars;
    protected int attackValue;
    protected int defenseValue;
    protected String characterName;
    protected int currentHealthIndex = 0;
    protected final int maxHealth;
    public boolean isAlive;
    private Terminal terminal;

    /**
     * Construct a Character object.
     *
     * @param health an array to represent the character's health bar.
     * @param attack attack value of the character.
     * @param defense defense value of the character.
     * @param name the name of the character.
     */
    public Character(int[] health, int attack, int defense, String name) {
        assert health.length > 0 : "Health bar array must not be empty";
        assert attack >= 0 : "Attack value must be non-negative";
        assert defense >= 0 : "Defense value must be non-negative";
        assert !name.isEmpty() : "Character name must not be empty";
        healthBars = health;
        attackValue = attack;
        defenseValue = defense;
        characterName = name;
        isAlive = true;
        maxHealth = healthBars[0];
        
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Overloaded constructor to construct character object with defined parameters
     * Usage within Storage class for loading game
     * @param health
     * @param attack
     * @param defense
     * @param name
     * @param maxHealth
     */

    public Character(int[] health, int attack, int defense, String name, int maxHealth) {
        this.healthBars = health;
        this.attackValue = attack;
        this.defenseValue = defense;
        this.characterName = name;
        this.isAlive = health[0] <= 0;
        this.maxHealth = maxHealth;
    }

    public Character(int health, int attack, int defense, String name) {
        assert attack >= 0 : "Attack value must be non-negative";
        assert defense >= 0 : "Defense value must be non-negative";
        assert !name.isEmpty() : "Character name must not be empty";
        healthBars = new int[health];
        attackValue = attack;
        defenseValue = defense;
        characterName = name;
        isAlive = true;
        maxHealth = healthBars[0];
    }

    /**
     * Return the character's name.
     *
     * @return a string showing the character's name.
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Return the character's health bar.
     *
     * @return integer array representing the character's health.
     */
    public int[] getHealthBars() {
        return getNonZeroElements(healthBars);
    }

    public static int[] getNonZeroElements(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int num : arr) {
            if (num > 0) {
                result.add(num);
            }
        }
        
        // Convert ArrayList to int array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Return the character's attack value.
     *
     * @return an integer representing the character's attack value.
     */
    public int getAttackValue() {
        return attackValue;
    }

    /**
     * Return the character's defense value.
     *
     * @return an integer representing the character's defense value.
     */
    public int getDefenseValue() {
        return defenseValue;
    }

    /**
     * Return the character's current status.
     * @return a boolean to indicate if the character is still alive.
     */
    public boolean getCurrentStatus() {
        return isAlive;
    }

    /**
     * Set the defending status of character
     *
     * @param isDefending Sets isDefending to a boolean status.
     */
    public void setDefending(boolean isDefending) {
        this.isDefending = isDefending;
    }

    /**
     * Get the defending status of character
     *
     * @return a boolean to indicate the defense status of character
     */
    public boolean getDefending() {
        return isDefending;
    }


    /**
     * Performs an attack on the defender.
     *
     * @param defender character being attacked in the attack event.
     */
    public void attack(Character defender) {
        assert defender != null: "Defender must not be null";
        assert defender.isAlive : "Defender must be alive";

        int damage = calculateDamage(defender);
        defender.takeDamage(damage);

        if (this instanceof Player) {
            BattleUI.printPlayerAttack(terminal, this, defender, damage);
        }
        else {
            BattleUI.printEnemyAttack(terminal, defender, this, damage);
        }

    }

    /**
     * Returns the calculated amount of damage dealt to the defender
     * based on the attacker's attack value and the defender's defense value.
     *
     * @param defender character being attacked in this attack event.
     * @return an integer representing the damage value imposed on defender.
     */
    public int calculateDamage(Character defender){
        assert defender != null: "Defender must not be null";
        assert defender.isAlive : "Defender must be alive";

        double damageReduction = (double) 100 / (100 + defender.getDefenseValue());
        return (int) (this.getAttackValue() * damageReduction);
    }

    /**
     * Apply damage to the character by updating its health bar.
     * Case 1: character is considered not alive if all the health bars are depleted.
     * Case 2: if the current health bar is depleted, update the next available health bar.
     *
     * @param damage an integer representing the damage value.
     */
    public void takeDamage(int damage) {
        assert damage > 0: "Damage must be non-negative";

        int remainingDamage = damage;
        while (remainingDamage > 0) {
            /* Case 1 */
            if (healthBars[currentHealthIndex] - remainingDamage <= 0) {
                remainingDamage -= healthBars[currentHealthIndex];
                healthBars[currentHealthIndex] = 0;
                currentHealthIndex++;
            }else {
                healthBars[currentHealthIndex] -= remainingDamage;
                break;
            }
            /* Case 2 */
            if (currentHealthIndex >= healthBars.length) {
                isAlive = false;
                break;
            }
        }
    }

    @Override
    public String toString() {
        String animatedHealthBars = "";
        for (int health : healthBars) {
            animatedHealthBars += "[";
            animatedHealthBars += "#".repeat(health);
            animatedHealthBars += "_".repeat(maxHealth - health);
            animatedHealthBars += "]";
        }

        return this.characterName + "'s stats:" +
                "\nHP: \t" + animatedHealthBars +
                "\nATK:\t" + this.getAttackValue() +
                "\nDEF:\t" + this.getDefenseValue();
    }

    /**
     * Returns the encoded string of player attributes to be saved
     * @return encoded string
     */
    public String toText() {
        return Arrays.toString(this.healthBars) + SAVE_DELIMITER +
                this.getAttackValue() + SAVE_DELIMITER +
                this.getDefenseValue() + SAVE_DELIMITER +
                this.characterName + SAVE_DELIMITER +
                this.maxHealth;
    }

}
