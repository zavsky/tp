package Equipment;

import java.io.Serializable;

/**
 * Represents Armor that Player can augment
 */
public abstract class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public int defense;
    public int attack;
    public int health;
    public int value;

    public Equipment(String name, int defense, int attack, int health, int value) {
        this.name = name;
        this.defense = defense;
        this.attack = attack;
        this.health = health;
        this.value = value;
    }

    public abstract String getEquipmentType();

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getHealth() {
        return this.health;
    }

    public int getValue() {
        return this.value;
    }
}
