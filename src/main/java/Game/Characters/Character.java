package Game.Characters;

public class Character {
    private int[] healthBars;
    private int attackValue;
    private int defenseValue;
    private String characterName;
    public boolean isAlive;
    public int currentHealthIndex = 0;

    public Character(int[] health, int attack, int defense, String Name) {
        healthBars = health;
        attackValue = attack;
        defenseValue = defense;
        characterName = Name;
        isAlive = true;
    }

    public String getCharacterName() {
        return characterName;
    }

    public int[] getHealthBars() {
        return healthBars;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public boolean getCurrentStatus() {
        return isAlive;
    }
    
    public void attack(Character defender) {
        int damage = calculateDamage(defender);
        defender.takeDamage(damage);
        System.out.println(this.characterName + " attacks " + defender.getCharacterName() +
                " for " + damage + " damage.");

    }

    public int calculateDamage(Character defender){
        double damageReduction = (double) 100 / (100 + defender.defenseValue);
        int damageTaken = (int) (this.attackValue * damageReduction);
        return damageTaken;

    }

    public void takeDamage(int damage) {

        int remainingDamage = damage;
        while (remainingDamage > 0) {

            /* Case 1 */
            if (currentHealthIndex >= healthBars.length) {
                isAlive = false;
                break;
            }
            /* Case 2 */
            if (healthBars[currentHealthIndex] - remainingDamage <= 0) {
                remainingDamage -= healthBars[currentHealthIndex];
                healthBars[currentHealthIndex] = 0;
                currentHealthIndex++;
            }
            else {
                healthBars[currentHealthIndex] -= remainingDamage;
                break;
            }
        }
    }

}
