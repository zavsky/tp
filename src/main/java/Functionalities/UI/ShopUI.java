package Functionalities.UI;

import Functionalities.UI.UI;
import Game.Equipment.Equipment;

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

    public static void printPlayerBoughtEquipment(Character player, Equipment equipment) {
        System.out.println(lineSeparator);
    }
}
