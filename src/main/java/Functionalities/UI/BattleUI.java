package Functionalities.UI;

import Game.Characters.Character;
import Game.Characters.Enemy;

// public class BattleUI extends UI {


//     /**
//      * Prints a message announcing the arrival of an enemy during battle.
//      *
//      * @param enemy The enemy the player is about to face.
//      */
//     public static void battleEntry(Enemy enemy) {
//         System.out.println(LINE_SEPARATOR);
//         System.out.println("Narrator: A villainous " + enemy.getCharacterName() + " stands in your way!");
//         System.out.println(LINE_SEPARATOR);
//     }

//     /**
//      * Prints the start of a battle turn, displaying both the player's and enemy's information.
//      * The information is printed with separators for clarity.
//      *
//      * @param player The player character involved in the battle.
//      * @param enemy The enemy character involved in the battle.
//      */
//     public static void printBattleTurnStart(Game.Characters.Character player, Character enemy) {
//         System.out.println(LINE_SEPARATOR);
//         printCharacterInfo(player);
//         System.out.println(LINE_SEPARATOR);
//         printCharacterInfo(enemy);
//         System.out.println(LINE_SEPARATOR);
//     }

//     /**
//      * Prints a message after the battle, showing the outcome and player's health status.
//      *
//      * @param enemy The enemy that the player fought.
//      * @param player The player involved in the battle.
//      */
//     public static void battleExit(Character player, Character enemy) {
//         if (!player.isAlive) {
//             System.out.println("Narrator: " + enemy.getCharacterName() + " has defeated you. Another one bites the dust!");
//             return;
//         } else {
//             System.out.println("Narrator: " + "You have vanquished " + enemy.getCharacterName());
//         }

//         if (player.getHealthBars()[0] > 90) {
//             System.out.println("Narrator: " + "\"Ha! that was easy!\", you thought to yourself, unaware of greater dangers that lurk ahead...");
//         } else if (player.getHealthBars()[0] > 50) {
//             System.out.println("Narrator: " + "You dust yourself off, ready for whatever challenge comes your way...");
//         } else {
//             System.out.println("Narrator: " + "You stumble away from the battlefield, severely wounded...");
//         }
//     }

//     public static void printPlayerAttack(Character player, Character enemy, int damage) {
//         System.out.println("Narrator: " + "You punch the " + enemy.getCharacterName() + " with your bare fist!");
//         if (damage > 30) {
//             System.out.println("Narrator: " + "WHAMMM!! The " + enemy.getCharacterName() + " took a whopping " + damage + " damage!");
//         } else if (damage > 10) {
//             System.out.println("Narrator: " + "You dealt " + damage + " damage.");
//         } else {
//             System.out.println("Narrator: " + "That tickled the " + enemy.getCharacterName() + ". You dealt a measly " + damage + " damage.");
//         }
//     }

//     public static void printPlayerDefend(Character player, Character enemy, int damage) {
//         System.out.println("Narrator: " + "You raise your shield and brace yourself for the " + enemy.getCharacterName() + "'s attack");
//         if (damage > 30) {
//             System.out.println("Narrator: " + "Your defence was ineffective. You took " + damage + " damage!");
//         } else if (damage > 10) {
//             System.out.println("Narrator: " + "You blocked some of the damage, taking " + damage + " damage.");
//         } else {
//             System.out.println("Narrator: " + "Perfect block! You only took " + damage + " damage.");
//         }
//     }

//     public static void printEnemyAttack(Character player, Character enemy, int damage) {
//         System.out.println("Narrator: " + "The " + enemy.getCharacterName() + " lunges forward and attacks!");
//         if (damage > 30) {
//             System.out.println("Narrator: " + "Ouch!! The " + enemy.getCharacterName() + " nearly sends you flying! It dealt " + damage + " damage!");
//         } else if (damage > 10) {
//             System.out.println("Narrator: " + "It dealt " + damage + " damage.");
//         } else {
//             System.out.println("Narrator: " + "You barely felt that attack... the " + enemy.getCharacterName() + " dealt only " + damage + " damage.");
//         }
//     }
// }

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class BattleUI extends UI {

    public static void battleEntry(Terminal terminal, Enemy enemy) {
        try {
            int y = 1;
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printMultiLineString(terminal, "Narrator: A villainous " + enemy.getCharacterName() + " stands in your way!", 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y);
        } catch (Exception e) {
            handleException("battleEntry", e);
        }
    }

    public static void printBattleTurnStart(Terminal terminal, Character player, Character enemy) {
        try {
            int y = 5;
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printCharacterInfo(terminal, player, 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y++);
            printCharacterInfo(terminal, enemy, 0, y++);
            printMultiLineString(terminal, LINE_SEPARATOR, 0, y);
        } catch (Exception e) {
            handleException("printBattleTurnStart", e);
        }
    }

    public static void battleExit(Terminal terminal, Character player, Character enemy) {
        try {
            int y = 15;
            if (!player.isAlive) {
                printMultiLineString(terminal, "Narrator: " + enemy.getCharacterName() + " has defeated you. Another one bites the dust!", 0, y++);
                return;
            } else {
                printMultiLineString(terminal, "Narrator: You have vanquished " + enemy.getCharacterName(), 0, y++);
            }

            if (player.getHealthBars()[0] > 90) {
                printMultiLineString(terminal, "Narrator: \"Ha! That was easy!\", you thought, unaware of greater dangers ahead...", 0, y++);
            } else if (player.getHealthBars()[0] > 50) {
                printMultiLineString(terminal, "Narrator: You dust yourself off, ready for whatever challenge comes your way...", 0, y++);
            } else {
                printMultiLineString(terminal, "Narrator: You stumble away from the battlefield, severely wounded...", 0, y++);
            }
        } catch (Exception e) {
            handleException("battleExit", e);
        }
    }

    public static void printPlayerAttack(Terminal terminal, Character player, Character enemy, int damage) {
        try {
            int y = 20;
            printMultiLineString(terminal, "Narrator: You punch the " + enemy.getCharacterName() + " with your bare fist!", 0, y++);
            if (damage > 30) {
                printMultiLineString(terminal, "Narrator: WHAMMM!! The " + enemy.getCharacterName() + " took a whopping " + damage + " damage!", 0, y++);
            } else if (damage > 10) {
                printMultiLineString(terminal, "Narrator: You dealt " + damage + " damage.", 0, y++);
            } else {
                printMultiLineString(terminal, "Narrator: That tickled the " + enemy.getCharacterName() + ". You dealt a measly " + damage + " damage.", 0, y++);
            }
        } catch (Exception e) {
            handleException("printPlayerAttack", e);
        }
    }

    public static void printPlayerDefend(Terminal terminal, Character player, Character enemy, int damage) {
        try {
            int y = 25;
            printMultiLineString(terminal, "Narrator: You raise your shield and brace for " + enemy.getCharacterName() + "'s attack", 0, y++);
            if (damage > 30) {
                printMultiLineString(terminal, "Narrator: Your defense was ineffective. You took " + damage + " damage!", 0, y++);
            } else if (damage > 10) {
                printMultiLineString(terminal, "Narrator: You blocked some of the damage, taking " + damage + " damage.", 0, y++);
            } else {
                printMultiLineString(terminal, "Narrator: Perfect block! You only took " + damage + " damage.", 0, y++);
            }
        } catch (Exception e) {
            handleException("printPlayerDefend", e);
        }
    }

    public static void printEnemyAttack(Terminal terminal, Character player, Character enemy, int damage) {
        try {
            int y = 30;
            printMultiLineString(terminal, "Narrator: The " + enemy.getCharacterName() + " lunges forward and attacks!", 0, y++);
            if (damage > 30) {
                printMultiLineString(terminal, "Narrator: Ouch!! The " + enemy.getCharacterName() + " nearly sends you flying! It dealt " + damage + " damage!", 0, y++);
            } else if (damage > 10) {
                printMultiLineString(terminal, "Narrator: It dealt " + damage + " damage.", 0, y++);
            } else {
                printMultiLineString(terminal, "Narrator: You barely felt that attack... the " + enemy.getCharacterName() + " dealt only " + damage + " damage.", 0, y++);
            }
        } catch (Exception e) {
            handleException("printEnemyAttack", e);
        }
    }

    private static void handleException(String methodName, Exception e) {
        System.err.println("Error in " + methodName + ": " + e.getMessage());
        e.printStackTrace();
    }
}
