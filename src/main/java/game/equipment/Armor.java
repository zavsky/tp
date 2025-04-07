package game.equipment;

import static storage.Storage.SAVE_DELIMITER;

public class Armor extends Equipment {
    /**
     * Constructor for an armor piece. Armor can have health, defense and agility stats.
     * @param name Name of the armor
     * @param defense Defense Value associated with armor.
     * @param health Health Value associated with armor.
     * @param agility Agility Value associated with armor.
     */
    public Armor(String name, int defense, int health, int agility, int value) {
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.agility = agility;
        this.attack = 0;
        this.value = value;
    }

    @Override
    public String getEquipmentType() {
        return "armor";
    }


    /**
     * Returns the encoded string of armor attributes to be saved
     * @return encoded string
     */
    @Override
    public String toText() {
        return this.getEquipmentType() + SAVE_DELIMITER +
                this.name;
    }
}
