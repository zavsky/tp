package Game.Equipment;

public abstract class Equipment {
    protected int attack;
    protected int health;
    protected int defense;
    protected int agility;
    protected int value;
    protected String name;

    public int getAttack() {
        return attack;
    }
    public int getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
    }
    public int getAgility() {
        return agility;
    }
    public int getValue() { return value; }
    public abstract String getEquipmentType();
    public String getName() { return name; }
}
