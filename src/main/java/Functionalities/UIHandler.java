package Functionalities;

import Game.Characters.Player;
import Game.Characters.Enemy;
import Game.Characters.Character;

/**
 * The UI class is responsible for handling the user interface in the text-based RPG game.
 * It displays messages related to the game, player, and enemy interactions, and renders the game's logo.
 */
public class UIHandler {
    // Constants
    private static final String LINE_SEPARATOR = "======================================================================";
    private static final String WELCOME_MESSAGE = "Welcome to";
    private static final String GAME_DESCRIPTION = "A text-based RPG game where your fate is determined by the roll of a die!!";
    
    private static final String BATTLE_ENTRY_MSG = "A villainous %s stands in your way!";
    private static final String PLAYER_DEFEATED_MSG = "%s has defeated you. Another one bites the dust!";
    private static final String ENEMY_DEFEATED_MSG = "You have vanquished %s.";

    private static final String PLAYER_ATTACK_MSG = "You punch the %s with your bare fist!";
    private static final String PLAYER_CRITICAL_HIT_MSG = "WHAMMM!! The %s took a whopping %d damage!";
    private static final String PLAYER_NORMAL_HIT_MSG = "You dealt %d damage.";
    private static final String PLAYER_WEAK_HIT_MSG = "That tickled the %s. You dealt a measly %d damage.";

    private static final String ENEMY_ATTACK_MSG = "The %s lunges forward and attacks!";
    private static final String ENEMY_CRITICAL_HIT_MSG = "Ouch!! The %s nearly sends you flying! It dealt %d damage!";
    private static final String ENEMY_NORMAL_HIT_MSG = "It dealt %d damage.";
    private static final String ENEMY_WEAK_HIT_MSG = "You barely felt that attack... the %s dealt only %d damage.";

    private static final String PLAYER_STATUS_HIGH = "\"Ha! that was easy!\", you thought to yourself, unaware of greater dangers that lurk ahead...";
    private static final String PLAYER_STATUS_MEDIUM = "You dust yourself off, ready for whatever challenge comes your way...";
    private static final String PLAYER_STATUS_LOW = "You stumble away from the battlefield, severely wounded...";

    private static final int HIGH_HEALTH_THRESHOLD = 90;
    private static final int MEDIUM_HEALTH_THRESHOLD = 50;

    // Logo
    private static final String LOGO =
            " ____      ___    _       _          _      ____    ___   _____  \n" +
            "|  _ \\    / _ \\  | |     | |        / \\    |  _ \\  |_ _| | ____| \n" +
            "| |_) |  | | | | | |     | |       / _ \\   | | | |  | |  |  _|   \n" +
            "|  _ <   | |_| | | |___  | |___   / /_\\ \\  | |_| |  | |  | |___  \n" +
            "|_| \\_\\   \\___/  |_____| |_____| /_/   \\_\\ |____/  |___| |_____| ";

    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a welcome message to the player, including the game logo and a description.
     */
    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(LINE_SEPARATOR);
        System.out.println(LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println(GAME_DESCRIPTION);
    }

    /**
     * Prints a message announcing the arrival of an enemy during battle.
     *
     * @param enemy The enemy the player is about to face.
     */
    public static void battleEntry(Enemy enemy) {
        System.out.println("A villainous " + enemy.getCharacterName() + " stands in your way!");
    }


    public static String dieFace1 =
        " _______ \n" +
        "|       |\n" +
        "|   O   |\n" +
        "|       |\n" +
        " ------- \n";

    public static String dieFace2 =
        " _______ \n" +
        "| O     |\n" +
        "|       |\n" +
        "|     O |\n" +
        " ------- \n";

    public static String dieFace3 =
        " _______ \n" +
        "| O     |\n" +
        "|   O   |\n" +
        "|     O |\n" +
        " ------- \n";

    public static String dieFace4 =
        " _______ \n" +
        "| O   O |\n" +
        "|       |\n" +
        "| O   O |\n" +
        " ------- \n";

    public static String dieFace5 =
        " _______ \n" +
        "| O   O |\n" +
        "|   O   |\n" +
        "| O   O |\n" +
        " ------- \n";

    public static String dieFace6 =
        " _______ \n" +
        "| O   O |\n" +
        "| O   O |\n" +
        "| O   O |\n" +
        " ------- \n";


    /**
     * A placeholder string for the player's model, which can be used for player-related information.
     */
    public static String playerModel =
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
     * Prints information about the player.
     *
     * @param player The player whose information is to be printed.
     */
    public static void printPlayerInfo(Player player) {
        System.out.println(playerModel);
    }

    /**
     * Prints the start of a battle turn, displaying both the player's and enemy's information.
     * The information is printed with separators for clarity.
     *
     * @param player The player character involved in the battle.
     * @param enemy The enemy character involved in the battle.
     */
    public static void printBattleTurnStart(Character player, Character enemy) {
        System.out.println(LINE_SEPARATOR);
        printCharacterInfo(player);
        System.out.println(LINE_SEPARATOR);
        printCharacterInfo(enemy);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints a message after the battle, showing the outcome and player's health status.
     *
     * @param enemy The enemy that the player fought.
     * @param player The player involved in the battle.
     */
    public static void battleExit(Character player, Character enemy) {
        if (!player.isAlive) {
            System.out.println(String.format(PLAYER_DEFEATED_MSG, enemy.getCharacterName()));
            return;
        }
        System.out.println(String.format(ENEMY_DEFEATED_MSG, enemy.getCharacterName()));

        if (player.getHealthBars()[0] > HIGH_HEALTH_THRESHOLD) {
            System.out.println(PLAYER_STATUS_HIGH);
        } else if (player.getHealthBars()[0] > MEDIUM_HEALTH_THRESHOLD) {
            System.out.println(PLAYER_STATUS_MEDIUM);
        } else {
            System.out.println(PLAYER_STATUS_LOW);
        }
    }

    public static void printPlayerAttack(Character player, Character enemy, int damage) {
        System.out.println("You punch the " + enemy.getCharacterName() + " with your bare fist!");
        if (damage > 30) {
            System.out.println(String.format(PLAYER_CRITICAL_HIT_MSG, enemy.getCharacterName(), damage));
        } else if (damage > 10) {
            System.out.println(String.format(PLAYER_NORMAL_HIT_MSG, damage));
        } else {
            System.out.println(String.format(PLAYER_WEAK_HIT_MSG, enemy.getCharacterName(), damage));
        }
    }

    public static void printPlayerDefend(Character player, Character enemy, int damage) {
        System.out.println("You raise your shield and brace yourself for the " + enemy.getCharacterName() + "'s attack");
        if (damage > 30) {
            System.out.println("Your defence was ineffective. You took " + damage + " damage!");
        } else if (damage > 10) {
            System.out.println("You blocked some of the damage, taking " + damage + " damage.");
        } else {
            System.out.println("Perfect block! You only took " + damage + " damage.");
        }
    }


    public static void printEnemyAttack(Character player, Character enemy, int damage) {
        System.out.println("The " + enemy.getCharacterName() + " lunges forward and attacks!");
        if (damage > 30) {
            System.out.println(String.format(ENEMY_CRITICAL_HIT_MSG, enemy.getCharacterName(), damage));
        } else if (damage > 10) {
            System.out.println(String.format(ENEMY_NORMAL_HIT_MSG, damage));
        } else {
            System.out.println(String.format(ENEMY_WEAK_HIT_MSG, enemy.getCharacterName(), damage));
        }
    }

    public static void printCharacterInfo(Character c) {
        System.out.println(c.getCharacterName());
        System.out.print("Heath: ");
        int[] healthBars = c.getHealthBars();
        int i = 0;
        for (int health : healthBars) {
            int maxHealth = 100;
            System.out.print("[");
            System.out.print("#".repeat(health));
            System.out.print("_".repeat(maxHealth - health));
            System.out.println("]");
            if (++i != healthBars.length) {
                System.out.print(" ".repeat(7));
            }
        }
        System.out.println("");
    }

    public void exit() {
        System.out.println("Until next time...");
    }
}

