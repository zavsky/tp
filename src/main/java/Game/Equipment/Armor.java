package Game.Equipment;

public class Armor extends Equipment {
    private String armorName;
    /**
     * Constructor for an armor piece
     * @param armorName Name of the armor
     * @param defense Defense Value associated with armor
     */
    public Armor(String armorName, int defense, int health, int agility) {
        this.armorName = armorName;
        this.health = health;
        this.defense = defense;
        this.agility = agility;
        this.attack = 0;
    }


    public String getArmorName() {
        return armorName;
    }
    public int getDefense() {
        return defense;
    }
}
