//@@author Vincesum
package equipments.weapons;

import equipments.Equipment;

import java.io.Serializable;

/**
 * Represents Weapon that Player can equip
 */
public class Weapon extends Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    public Weapon(String name, int defense, int attack, int health, int value) {
        super(name, defense, attack, health, value);
    }

    public Weapon(String name, int attack) {
        super(name, 0, attack, 0, 0);
    }

    @Override
    public String getEquipmentType() {
        return "weapon";
    }

    @Override
    public int getId() {
        return 2;
    }

    public String toString() {
        return "Weapon  : " + name + " (" + attack + " atk)";
    }
}
