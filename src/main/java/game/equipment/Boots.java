package game.equipment;

import static storage.Storage.SAVE_DELIMITER;

public class Boots extends Equipment{

    /**
     * Constructor for a boots equipment. Boots can have defense and agility values.
     * @param name Name of the boots.
     * @param defense Defense value associated with boots.
     * @param agility Agility value associated with boots.
     */
    public Boots(String name, int defense, int agility, int value) {
        this.name = name;
        this.defense = defense;
        this.agility = agility;
        this.attack = 0;
        this.health = 0;
        this.value = value;
    }

    @Override
    public String getEquipmentType() {
        return "boots";
    }

    /**
     * Returns the encoded string of boots attributes to be saved
     * @return encoded string
     */
    @Override
    public String toText() {
        return this.getEquipmentType() + SAVE_DELIMITER +
                this.name;
    }
}
