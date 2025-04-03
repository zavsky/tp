package Functionalities.UI;

import Game.Characters.Player;

// public class LootUI extends UI {
//     public static void printLootEntry() {
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Narrator: A mysterious chest lies before you... you open it.");
//         System.out.println(LINE_SEPARATOR);
//     }

//     public static void printLootExit(String chest, int amount, Player player) {
//         System.out.println("\n" + chest + "\n");
        // printPlayerEarnGold(player, amount);
//     }
// }

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

public class LootUI extends UI {
    private static final String LINE_SEPARATOR = "-----------------------------";

    public static void printLootEntry(Terminal terminal) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            tg.putString(0, 1, LINE_SEPARATOR);
            tg.putString(0, 2, "Narrator: A mysterious chest lies before you... you open it.");
            tg.putString(0, 3, LINE_SEPARATOR);
            terminal.flush();
        } catch (Exception e) {
            handleException("printLootEntry", e);
        }
    }
    
    public static void printLootExit(Terminal terminal, String chest, int amount, Player player) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            tg.putString(0, 5, chest);
            printPlayerEarnGold(terminal, player, amount);
            terminal.flush();
        } catch (Exception e) {
            handleException("printLootExit", e);
        }
    }
    
    private static void printPlayerEarnGold(Terminal terminal, Player player, int amount) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            tg.putString(0, 7, player.getCharacterName() + " earns " + amount + " gold!");
            terminal.flush();
        } catch (Exception e) {
            handleException("printPlayerEarnGold", e);
        }
    }
    
    private static void typewrite(Terminal terminal, int x, int y, String text, int delay) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            for (int i = 0; i < text.length(); i++) {
                tg.putString(x + i, y, String.valueOf(text.charAt(i)));
                terminal.flush();
                Thread.sleep(delay); // Controls speed (lower = faster)
            }
        } catch (Exception e) {
            handleException("typewrite", e);
        }
    }
    
    private static void handleException(String methodName, Exception e) {
        System.err.println("Error in " + methodName + ": " + e.getMessage());
        e.printStackTrace();
    }
    
}
