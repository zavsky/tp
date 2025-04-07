//@@author vincesum
package equipments.boots;

import equipments.Equipment;

import java.io.Serializable;

/**
 * Represents Weapon that Player can equip
 */
public class Boots extends Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    public Boots(String name, int defense, int attack, int health, int value) {
        super(name, defense, attack, health, value);
    }

    public Boots(String name, int defense, int attack) {
        super(name, defense, attack, 0, 0);
    }

    @Override
    public String getEquipmentType() {
        return "weapon";
    }

    @Override
    public int getId() {
        return 1;
    }

    public String toString() {
        return "Boots   : " + name + " (" + defense + " def) (" + attack + " atk)";
    }
}
