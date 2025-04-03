package Game.Characters;

import java.util.Arrays;

import static Functionalities.Storage.SAVE_DELIMITER;

import Exceptions.RolladieException;
import Game.Currency.Gold;
import Game.Equipment.Equipment;
import Game.Equipment.EquipmentList;


/**
 * A class to create the player object.
 */
public class Player extends Character {
    private Gold gold;
    private int attackBonus;
    private int defenseBonus;
    private EquipmentList equipments;

    private static final String PLAYER_MODEL =
            "      __      _\n" +
                    "     /__\\__  //\n" +
                    "    //_____\\///\n" +
                    "   _| /-_-\\)|/_\n" +
                    "  (___\\ _ //___\\\n" +
                    "  (  |\\\\_/// * \\\\\n" +
                    "   \\_| \\_((*   *))\n" +
                    "   ( |__|_\\\\  *//\n" +
                    "   (o/  _  \\_*_/\n" +
                    "   //\\__|__/\\\n" +
                    "  // |  | |  |\n" +
                    " //  _\\ | |___)\n" +
                    "//  (___|";

    /**
     * Constructor to construct a player object.
     *
     * @param health an integer array to represent the health bar of player.
     * @param attack an integer to represent the damage value in an attack.
     * @param defense an integer to represent the defense value.
     * @param name a string to represent the name of the player.
     */
    public Player(int[] health, int attack, int defense, String name) {
        super(health, attack, defense, name);
        this.gold = new Gold(0);
        this.equipments = new EquipmentList();
    }

    /**
     * Overloaded Constructor to construct a player object with defined parameters
     * Usage within Storage Class for loading game
     * @param healthBars
     * @param attackValue
     * @param defenseValue
     * @param characterName
     * @param maxHealth
     */
    public Player(int[] healthBars, int attackValue, int defenseValue, String characterName, int maxHealth) {
        super(healthBars, attackValue, defenseValue, characterName, maxHealth);
    }

    /**
     * Gold amount earned by player
     * @param earnedGold An integer to represent the amount earned.
     */
    public void earnGold(Gold earnedGold) {
        this.gold = this.gold.earnGold(earnedGold);
    }

    /**
     * Gold amount spent by player.
     * @param spentGold An integer to represent the amount spent.
     */

    public boolean spendGold(Gold spentGold) {
        Gold tempGold = this.gold.spendGold(spentGold);
        if (tempGold.getAmount() >= 0) {
            this.gold = tempGold;
            return true;
        }
        return false;
    }

    public Gold getGold() {
        return gold;
    }

    public void buyEquipment(Equipment equipment) throws RolladieException {
        // if sufficient gold to purchase item, equip the item
        if (spendGold(new Gold(equipment.getValue()))) {
            this.equipments = this.equipments.addEquipment(equipment);
        }
        
    }

    public void sellEquipment(String equipmentType) throws RolladieException {
        this.equipments = this.equipments.removeEquipment(equipmentType);
        earnGold(new Gold(equipments.getEquipment(equipmentType).getValue() / 2));
    }

    public Equipment getEquipment(String equipmentType) throws RolladieException {
        return this.equipments.getEquipment(equipmentType);
    }

    /**
     * Get the defense value of player
     *
     * @return current defenseValue depending on whether player used DefendAction the previous turn
     */
    @Override
    public int getDefenseValue() {
        if (isDefending) {
            return (defenseValue + defenseBonus + equipments.getEquipmentDefense()) * 3;
        }
        return defenseValue + defenseBonus + equipments.getEquipmentDefense();
    }

    /**
     * Return the character's attack value.
     *
     * @return an integer representing the character's attack value.
     */
    public int getAttackValue() {
        return attackValue + attackBonus + equipments.getEquipmentAttack();
    }

    /**
     * Set player's attack bonus value.
     *
     * @param bonus dice outcome for attack action.
     */
    public void setAttackBonus(int bonus){
        attackBonus = bonus;
    }

    /**
     * Set player's defend bonus value.
     *
     * @param bonus dice outcome for defend action.
     */
    public void setDefenseBonus(int bonus){
        defenseBonus = bonus;
    }


    /**
     * Return the information of a player.
     *
     * @return A string that show information of a player.
     */
    @Override
    public String toString() {
        return super.toString() +  "\nEquipments: "
                + equipments.toString();
    }

    public Equipment[] getEquipments() {
        return equipments.getEquipmentArray();
    }
}
