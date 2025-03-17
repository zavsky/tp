package Game.Characters;

/**
 * Represents an abstract character in the game with health bars, attack, and defense values.
 * Characters can attack and take damage, with damage being distributed across multiple health bars.
 */
public abstract class Character {
    private int[] healthBars;
    private int attackValue;
    private int defenseValue;
    private String characterName;
    private boolean isAlive;

    /**
     * Constructs a new Character with the specified attributes.
     *
     * @param health  An array representing the character's health bars.
     * @param attack  The attack value of the character.
     * @param defense The defense value of the character.
     * @param Name    The name of the character.
     */
    public Character(int health[], int attack, int defense, String Name) {
        healthBars = health;
        attackValue = attack;
        defenseValue = defense;
        characterName = Name;
        isAlive = true;
    }

    /**
     * Retrieves the character's health bars.
     *
     * @return An array representing the character's health bars.
     */
    public int[] getHealthBars() {
        return healthBars;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Performs an attack on another character.
     *
     * @param defender The character that is being attacked.
     */
    public void attack(Character defender) {
        int damage = attackValue;
        defender.takeDamage(damage);
    }

    /**
     * Reduces the character's health based on the incoming damage, accounting for defense.
     * Damage is distributed across multiple health bars sequentially.
     *
     * @param damage The amount of damage received.
     */
    public void takeDamage(int damage) {
        int index = 0;
        double damageReduction = (double) 100 / (100 + defenseValue);
        int remainingDamage = (int) (damage * damageReduction);

        while (remainingDamage > 0) {
            // If all health bars are depleted, the character is no longer alive
            if (index >= healthBars.length) {
                isAlive = false;
                break;
            }
            // If the current health bar is depleted, move to the next health bar
            if (healthBars[index] - remainingDamage <= 0) {
                remainingDamage -= healthBars[index];
                healthBars[index] = 0;
                index++;
            } else {
                healthBars[index] -= remainingDamage;
                break;
            }
        }
    }
}
