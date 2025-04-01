package Game.Equipment;

import Functionalities.UI.UI;
import Game.Actions.BattleAction.AttackAction;
import Game.Actions.BattleAction.DefendAction;
import Game.Actions.BattleAction.FleeAction;
import Game.Actions.DefaultAction;
import Game.Actions.ExitAction;
import Game.Actions.HelpAction;

import java.util.ArrayList;

/**
 * EquipmentList class to store information on the equipment that the player is using
 */
public class EquipmentList {
    private ArrayList<Equipment> equipmentList;
    private int maxNumberOfEquipment;
    private int totalAttack;
    private int totalDefense;
    private int totalHp;
    private int totalAgility;
    private Equipment[] equipmentSlot;
    //player only allowed to equip one armour, one pair of boots and one weapon
    private static final int AMOUR_SLOT = 0;
    private static final int BOOTS_SLOT = 1;
    private static final int WEAPON_SLOT = 2;

    public EquipmentList(int maxNumberOfEquipment) {
        this.maxNumberOfEquipment = maxNumberOfEquipment;
        equipmentList = new ArrayList<>();
    }

    public EquipmentList() {
        this.equipmentSlot = new Equipment[3];
    }

    public void addEquipment(Equipment equipment) {
        switch(equipment.getEquipmentType()) {
        case "armour":
            addArmour(equipment);
        case "boots":
            addBoots(equipment);
        case "weapon":
            addWeapon(equipment);
        default:
            UI.printErrorMessage("Invalid Equipment type!");
        }
    }

    private void addArmour(Equipment equipment) {
        if (equipmentSlot[AMOUR_SLOT] == null) {
            equipmentSlot[AMOUR_SLOT] = equipment;
        } else {
            UI.printErrorMessage("Equipment already exists!");
        }
    }

    private void addBoots(Equipment equipment) {
        if (equipmentSlot[BOOTS_SLOT] == null) {
            equipmentSlot[BOOTS_SLOT] = equipment;
        } else {
            UI.printErrorMessage("Equipment already exists!");
        }
    }

    private void addWeapon(Equipment equipment) {
        if (equipmentSlot[WEAPON_SLOT] == null) {
            equipmentSlot[WEAPON_SLOT] = equipment;
        } else {
            UI.printErrorMessage("Equipment already exists!");
        }
    }

    public void removeEquipment(Equipment equipment) {
        equipmentList.remove(equipment);
    }

}
