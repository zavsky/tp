package Game.Equipment;

import Exceptions.RolladieException;
import Functionalities.UI.UI;
import Game.Actions.BattleAction.AttackAction;
import Game.Actions.BattleAction.DefendAction;
import Game.Actions.BattleAction.FleeAction;
import Game.Actions.DefaultAction;
import Game.Actions.ExitAction;
import Game.Actions.HelpAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * EquipmentList class to store information on the equipment that the player is using
 */
public class EquipmentList {
    private static final int MAX_EQUIPMENTS = 3;
    private List<Optional<Equipment>> equipmentSlot;
    //player only allowed to equip one armour, one pair of boots and one weapon
    private static final int ARMOUR_SLOT = 0;
    private static final int BOOTS_SLOT = 1;
    private static final int WEAPON_SLOT = 2;

    public EquipmentList() {
        equipmentSlot =  List.of(Optional.empty(), Optional.empty(), Optional.empty());
    }

    public EquipmentList(List<Optional<Equipment>> equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public EquipmentList addEquipment(Equipment equipment) throws RolladieException {
        switch(equipment.getEquipmentType()) {
        case "armor":
            return new EquipmentList(addToEquipmentList(equipment, ARMOUR_SLOT));
        case "boots":
            return new EquipmentList(addToEquipmentList(equipment, BOOTS_SLOT));
        case "weapon":
            return new EquipmentList(addToEquipmentList(equipment, WEAPON_SLOT));
        default:
            throw new RolladieException("Invalid Equipment type!");
        }
    }

    private List<Optional<Equipment>> addToEquipmentList(Equipment equipment, int equipmentType) throws RolladieException {
        Optional<Equipment> currSlot = equipmentSlot.get(equipmentType);
        if (currSlot.isPresent()) {
            throw new RolladieException(currSlot.get().getEquipmentType() + " is already equipped!");
        }
        return equipmentSlot.stream()
                .map(e -> equipmentSlot.indexOf(e) == equipmentType ? Optional.of(equipment) : e)
                .toList();
    }

    public EquipmentList removeEquipment(String equipmentType) throws RolladieException {
        Equipment equipment = getEquipment(equipmentType);
        List<Optional<Equipment>> newSlot = equipmentSlot.stream()
                .map(e -> e.filter(x -> !x.equals(equipment)))
                .toList();
        return new EquipmentList(newSlot);
    }

    public Equipment getEquipment(String equipmentType) throws RolladieException {
        int curr_index;
        switch(equipmentType) {
        case "armour":
            curr_index = ARMOUR_SLOT;
            break;
        case "boots":
            curr_index = BOOTS_SLOT;
            break;
        case "weapon":
            curr_index = WEAPON_SLOT;
            break;
        default:
            throw new RolladieException("Invalid Equipment type!");
        }

        Optional<Equipment> currSlot = equipmentSlot.get(curr_index);

        return currSlot.orElseThrow(() -> new RolladieException(equipmentType + " is not equipped!"));
    }

    public Equipment[] getEquipmentArray() {
        return equipmentSlot.stream()
                .filter(Optional::isPresent)  // Remove empty slots
                .map(Optional::get)           // Extract Equipment objects
                .toArray(Equipment[]::new);   // Convert to an array
    }
    

    public int getEquipmentAttack() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getAttack())
                .reduce(0, Integer::sum);
    }

    public int getEquipmentHealth() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getHealth())
                .reduce(0, Integer::sum);
    }

    public int getEquipmentDefense() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getDefense())
                .reduce(0, Integer::sum);
    }

    public int getEquipmentAgility() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getAgility())
                .reduce(0, Integer::sum);
    }

    public String toString() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().toString())
                .reduce("", (x, y) -> x + "\n" + UI.LINE_SEPARATOR + "\n" + y);
    }
}
