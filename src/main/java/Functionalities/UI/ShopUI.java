package Functionalities.UI;

import Game.Characters.Player;
import Game.Equipment.Equipment;

// public class ShopUI extends UI {
//     public static void printShopEntry() {
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Narrator: You enter the equipment shop.");
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Shopkeeper: Welcome to my shop! Take your time!");
//         System.out.println(LINE_SEPARATOR);
//     }

//     public static void printShopExit() {
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Shopkeeper: Thanks for coming! Hope to see you again!");
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Narrator: You leave the equipment shop.");
//         System.out.println(LINE_SEPARATOR);
//     }

//     public static void printBuyEquipment(Equipment equipment) {
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Narrator: You bought " + equipment.getName() + " for " + equipment.getValue() + " gold.");
//         System.out.println(LINE_SEPARATOR);
//     }

//     public static void printSellEquipment(Equipment equipment) {
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Narrator: You sold " + equipment.getName() + " for " + equipment.getValue() / 2 + " gold.");
//         System.out.println(LINE_SEPARATOR);
//     }

//     public static void printShopCollection(Player player, Equipment... equipments) {
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Shop Equipment Collection:");
//         for (int i = 0; i < equipments.length; i++) {
//             System.out.println((i + 1) + ". " + equipments[i].toString());
//         }
//         System.out.println(LINE_SEPARATOR + "\n");
//         System.out.println("Please enter \"buy [equipment_index]\"  or \"sell [equipmentType]\" or \"leave\".");
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Please do not buy equipment types you already own. Current Player info: ");
//         System.out.println(LINE_SEPARATOR);
//         System.out.println(player.toString());
//     }
// }

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

public class ShopUI extends UI {
    public static void printShopEntry(Terminal terminal) {
        try {
            int y = 1;
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Narrator: You enter the equipment shop.", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Shopkeeper: Welcome to my shop! Take your time!", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y);
        } catch (Exception e) {
            handleException("printShopEntry", e);
        }
    }
    
    public static void printShopExit(Terminal terminal) {
        try {
            int y = 10;
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Shopkeeper: Thanks for coming! Hope to see you again!", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Narrator: You leave the equipment shop.", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y);
        } catch (Exception e) {
            handleException("printShopExit", e);
        }
    }
    
    public static void printBuyEquipment(Terminal terminal, Equipment equipment) {
        try {
            int y = 15;
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Narrator: You bought " + equipment.getName() + " for " + equipment.getValue() + " gold.", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y);
        } catch (Exception e) {
            handleException("printBuyEquipment", e);
        }
    }
    
    public static void printSellEquipment(Terminal terminal, Equipment equipment) {
        try {
            int y = 20;
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Narrator: You sold " + equipment.getName() + " for " + (equipment.getValue() / 2) + " gold.", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y);
        } catch (Exception e) {
            handleException("printSellEquipment", e);
        }
    }
    
    public static void printShopCollection(Terminal terminal, Player player, Equipment... equipments) {
        try {
            int y = 25;
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Shop Equipment Collection:", 0, y++);
    
            for (int i = 0; i < equipments.length; i++) {
                printMultiLineString(terminal, (i + 1) + ". " + equipments[i].toString(), 0, y++);
            }
    
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Please enter \"buy [equipment_index]\" or \"sell [equipmentType]\" or \"leave\".", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Please do not buy equipment types you already own. Current Player info:", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, player.toString(), 0, y);
        } catch (Exception e) {
            handleException("printShopCollection", e);
        }
    }
    
    private static void handleException(String methodName, Exception e) {
        System.err.println("Error in " + methodName + ": " + e.getMessage());
        e.printStackTrace();
    }
    
}
