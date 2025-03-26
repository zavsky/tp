package Game.Equipment;

public abstract class Equipment {
    protected int attack;
    protected int health;
    protected int defense;
    protected int agility;

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
}
