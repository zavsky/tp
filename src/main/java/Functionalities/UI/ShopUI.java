package Functionalities.UI;

import Functionalities.UI.UI;
import Game.Equipment.Equipment;

import java.util.Collection;

public class ShopUI extends UI {
    public static void printShopEntry() {
        System.out.println(lineSeparator);
        System.out.println("Narrator: You enter the equipment shop.");
        System.out.println(lineSeparator);
        System.out.println("Shopkeeper: Welcome to my shop! Take your time!");
        System.out.println(lineSeparator);
    }

    public static void printShopExit() {
        System.out.println(lineSeparator);
        System.out.println("Shopkeeper: Thanks for coming! Hope to see you again!");
        System.out.println(lineSeparator);
        System.out.println("Narrator: You leave the equipment shop.");
        System.out.println(lineSeparator);
    }

    public static void printBuyEquipment(Equipment equipment) {
        System.out.println(lineSeparator);
        System.out.println("Narrator: You bought " + equipment.getName() + " for " + equipment.getValue() + " gold.");
        System.out.println(lineSeparator);
    }

    public static void printSellEquipment(Equipment equipment) {
        System.out.println(lineSeparator);
        System.out.println("Narrator: You sold " + equipment.getName() + " for " + equipment.getValue() / 2 + " gold.");
        System.out.println(lineSeparator);
    }

    public static void printShopCollection(Equipment[] equipments) {
        System.out.println(lineSeparator);
        for (int i = 0; i < equipments.length; i++) {
            System.out.println((i + 1) + ". " + equipments[i].toString());
        }
        System.out.println(lineSeparator);
    }
}
