package oldgame.equipment;

import static storage.Storage.SAVE_DELIMITER;

public class Weapon extends Equipment {

    /**
     * Constructor for a weapon, weapon can have attack, health and agility stats.
     * @param name Name of the weapon.
     * @param attack Attack value the weapon possesses.
     * @param health Health value the weapon possesses.
     * @param agility Agility value the weapon possesses.
     */
    public Weapon(String name, int attack, int health, int agility, int value) {
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.agility = agility;
        this.defense = 0;
        this.value = value;
    }

    public String getEquipmentType() {
        return "weapon";
    }

    /**
     * Returns the encoded string of weapon attributes to be saved
     * @return encoded string
     */
    @Override
    public String toText() {
        return this.getEquipmentType() + SAVE_DELIMITER +
                this.name;
    }
}
