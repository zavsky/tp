package equipments.armors;

import equipments.Equipment;

import java.io.Serializable;

/**
 * Represents Armor that Player can augment
 */
public class Armor extends Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    public Armor(String name, int defense, int attack, int health, int value) {
        super(name, defense, attack, health, value);
    }

    public Armor(String name, int defense) {
        super(name, defense, 0, 0, 0);
    }

    @Override
    public String getEquipmentType() {
        return "Armor";
    }

    @Override
    public int getId() {
        return 0;
    }

    public String toString() {
        return "Armor   : " + name + " (" + defense + " def)";
    }
}
