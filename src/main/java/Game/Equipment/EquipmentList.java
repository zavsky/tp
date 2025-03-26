package Game.Equipment;

import java.util.ArrayList;

/**
 * EquipmentList class to store information on the equipment that the player is using
 */
public class EquipmentList {
    private ArrayList<Equipment> equipmentList;
    private int maxNumberOfEquipment;

    public EquipmentList(int maxNumberOfEquipment) {
        this.maxNumberOfEquipment = maxNumberOfEquipment;
        equipmentList = new ArrayList<>();
    }
}
