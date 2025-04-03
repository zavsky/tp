package Functionalities.UI;

import Game.Characters.Character;
import Game.Characters.Player;

/**
 * The UI class is responsible for handling the user interface in the text-based RPG game.
 * It displays messages related to the game, player, and enemy interactions, and renders the game's LOGO.
 */
// public class UI {
//     /**
//      * A separator string used to format the output in the UI.
//      */
//     public static String LINE_SEPARATOR = "======================================================================";



//     /**
//      * A string containing the ASCII art LOGO of the game.
//      */
//     public static String LOGO =
//         " ____    " + "  ___   " + " _      " + " _      " + "    _    " + " ____   " + " ___  " + " _____  " + "\n" +
//         "|  _ \\   " + " / _ \\  " + "| |     " + "| |     " + "   / \\   " + "|  _ \\  " + "|_ _| " + "| ____| " + "\n" +
//         "| |_) |  " + "| | | | " + "| |     " + "| |     " + "  / _ \\  " + "| | | | " + " | |  " + "|  _|   " + "\n" +
//         "|  _ <   " + "| |_| | " + "| |___  " + "| |___  " + " / /_\\ \\ " + "| |_| | " + " | |  " + "| |___  " + "\n" +
//         "|_| \\_\\  " + " \\___/  " + "|_____| " + "|_____| " + "/_/   \\_\\" + "|____/  " + "|___| " + "|_____| ";


//     public static String DIE_FACE_1 =
//         " _______ \n" +
//         "|       |\n" +
//         "|   O   |\n" +
//         "|       |\n" +
//         " ------- \n";

//     public static String DIE_FACE_2 =
//         " _______ \n" +
//         "| O     |\n" +
//         "|       |\n" +
//         "|     O |\n" +
//         " ------- \n";

//     public static String DIE_FACE_3 =
//         " _______ \n" +
//         "| O     |\n" +
//         "|   O   |\n" +
//         "|     O |\n" +
//         " ------- \n";

//     public static String DIE_FACE_4 =
//         " _______ \n" +
//         "| O   O |\n" +
//         "|       |\n" +
//         "| O   O |\n" +
//         " ------- \n";

//     public static String DIE_FACE_5 =
//         " _______ \n" +
//         "| O   O |\n" +
//         "|   O   |\n" +
//         "| O   O |\n" +
//         " ------- \n";

//     public static String DIE_FACE_6 =
//         " _______ \n" +
//         "| O   O |\n" +
//         "| O   O |\n" +
//         "| O   O |\n" +
//         " ------- \n";



//     /**
//      * Prints a message to the console.
//      *
//      * @param message The message to be printed.
//      */
//     public static void printErrorMessage(String message) {
//         System.out.println(message);
//     }

//     public static void printMessage(String message) {
//         System.out.println(message);
//     }


//     /**
//      * Prints a welcome message to the player, including the game LOGO and a description.
//      */
//     public static void printWelcomeMessage() {
//         System.out.println("Welcome to");
//         System.out.println(LINE_SEPARATOR);
//         System.out.println(LOGO);
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("A text-based RPG game where your fate is determined by the roll of a die!!");
//     }


//     public static void printCharacterInfo(Character c) {
//         System.out.println(c);
//     }

//     public static void printPlayerEarnGold(Player player, int amount) {
//         System.out.println("You gained " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
//     }

//     public static void printPlayerSpentGold(Player player, int amount) {
//         System.out.println("You spent " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
//     }

//     public static void printExitMessage() {
//         System.out.println("Narrator: Leaving so soon? I expected more from you!");
//     }

//     public static void printDeathMessage() {
//         System.out.println("Narrator: Game over, you've died! L");
//     }
// }

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

public class UI {
    /**
     * A separator string used to format the output in the UI.
     */
    public static final String LINE_SEPARATOR = "======================================================================";

    /**
     * A string containing the ASCII art LOGO of the game.
     */
    public static final String LOGO =
        " ____    " + "  ___   " + " _      " + " _      " + "    _    " + " ____   " + " ___  " + " _____  " + "\n" +
        "|  _ \\   " + " / _ \\  " + "| |     " + "| |     " + "   / \\   " + "|  _ \\  " + "|_ _| " + "| ____| " + "\n" +
        "| |_) |  " + "| | | | " + "| |     " + "| |     " + "  / _ \\  " + "| | | | " + " | |  " + "|  _|   " + "\n" +
        "|  _ <   " + "| |_| | " + "| |___  " + "| |___  " + " / /_\\ \\ " + "| |_| | " + " | |  " + "| |___  " + "\n" +
        "|_| \\_\\  " + " \\___/  " + "|_____| " + "|_____| " + "/_/   \\_\\" + "|____/  " + "|___| " + "|_____| ";

    private static final String[] DIE_FACES = {
        " _______ \n|       |\n|   O   |\n|       |\n ------- \n",
        " _______ \n| O     |\n|       |\n|     O |\n ------- \n",
        " _______ \n| O     |\n|   O   |\n|     O |\n ------- \n",
        " _______ \n| O   O |\n|       |\n| O   O |\n ------- \n",
        " _______ \n| O   O |\n|   O   |\n| O   O |\n ------- \n",
        " _______ \n| O   O |\n| O   O |\n| O   O |\n ------- \n"
    };

    /**
     * Prints a message in Lanterna.
     */
    public static void printMessage(Terminal terminal, String message, int x, int y) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            tg.putString(x, y, message);
            terminal.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints an error message in red.
     */
    public static void printErrorMessage(Terminal terminal, String message, int x, int y) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.RED);
            tg.putString(x, y, message);
            terminal.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the welcome message with the game logo.
     */
    public static void printWelcomeMessage(Terminal terminal) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            int startY = 1;
    
            tg.putString(0, startY++, "Welcome to");
            tg.putString(0, startY++, LINE_SEPARATOR);
            printMultiLineString(terminal, LOGO, 0, startY);
            startY += LOGO.split("\n").length;
            tg.putString(0, startY++, LINE_SEPARATOR);
            tg.putString(0, startY++, "A text-based RPG game where your fate is determined by the roll of a die!!");
    
            terminal.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints character info.
     */
    public static void printCharacterInfo(Terminal terminal, Character c, int x, int y) {
        printMessage(terminal, c.toString(), x, y);
    }

    /**
     * Prints the player earning gold.
     */
    public static void printPlayerEarnGold(Terminal terminal, Player player, int amount, int x, int y) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            tg.putString(x, y, "You gained " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
            terminal.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the player spending gold.
     */
    public static void printPlayerSpentGold(Terminal terminal, Player player, int amount, int x, int y) throws Exception {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.RED);
            tg.putString(x, y, "You spent " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
            terminal.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage(Terminal terminal, int x, int y) {
        printMessage(terminal, "Narrator: Leaving so soon? I expected more from you!", x, y);
    }

    /**
     * Prints the death message.
     */
    public static void printDeathMessage(Terminal terminal, int x, int y) {
        printErrorMessage(terminal, "Narrator: Game over, you've died! L", x, y);
    }

    /**
     * Prints a die face.
     */
    public static void printDieFace(Terminal terminal, int dieNumber, int x, int y) {
        if (dieNumber < 1 || dieNumber > 6) return;
        try {
            printMultiLineString(terminal, DIE_FACES[dieNumber - 1], x, y);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to print multi-line text.
     */
    public static void printMultiLineString(Terminal terminal, String text, int x, int y) {
        try {
            TextGraphics tg = terminal.newTextGraphics();
            String[] lines = text.split("\n");
            for (int i = 0; i < lines.length; i++) {
                tg.putString(x, y + i, lines[i]);
            }
            terminal.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
