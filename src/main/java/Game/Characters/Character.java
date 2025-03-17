package Game.Characters;

public abstract class Character {
    private int[] healthBars;
    private int attackValue;
    private int defenseValue;
    private String characterName;
    private boolean isAlive;

    public Character(int health[], int attack, int defense, String Name) {
        healthBars = health;
        attackValue = attack;
        defenseValue = defense;
        characterName = Name;
        isAlive = true;
    }

    public String getCharacterName() { return characterName; }

    public int[] getHealthBars() {
        return healthBars;
    }

    public void attack(Character defender) {
        int damage = attackValue;
        defender.takeDamage(damage);
    }

    public boolean isAlive() { return isAlive; }

    public void takeDamage(int damageDealt) {
        double damageReduction = (double) 100 / (100 + defenseValue);
        int damage = (int) (damageDealt * damageReduction);
        int index = 0;
        int remainingDamage = damage;
        while (remainingDamage > 0) {
            //If all health bars are depleted, character is no longer alive
            if (index >= healthBars.length) {
                isAlive = false;
                break;
            }
            //If current health bar is depleted, update remaining damage to be dealt on next health bar
            if (healthBars[index] - remainingDamage <= 0) {
                remainingDamage -= healthBars[index];
                healthBars[index] = 0;
                index++;
            }
            else {
                healthBars[index] -= remainingDamage;
                break;
            }
        }
    }

}
