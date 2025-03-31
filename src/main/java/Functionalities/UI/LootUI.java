package Functionalities.UI;

import Game.Characters.Player;

public class LootUI extends UI {
    public static void printLootEntry() {
        System.out.println(lineSeparator);
        System.out.println("Narrator: A mysterious chest lies before you... you open it.");
        System.out.println(lineSeparator);
    }

    public static void printLootExit(String chest, int amount, Player player) {
        System.out.println("\n" + chest + "\n");
        printPlayerEarnGold(player, amount);
    }
}
