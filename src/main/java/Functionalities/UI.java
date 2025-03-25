package Functionalities;

import Game.Characters.Player;
import Game.Characters.Enemy;
import Game.Characters.Character;

public class UI {
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

    // Printing methods
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(LINE_SEPARATOR);
        System.out.println(LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println(GAME_DESCRIPTION);
    }

    public void battleEntry(Enemy enemy) {
        System.out.println(String.format(BATTLE_ENTRY_MSG, enemy.getCharacterName()));
    }

    public void battleExit(Enemy enemy, Player player) {
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

    public void printPlayerAttack(Player player, Enemy enemy, int damage) {
        System.out.println(String.format(PLAYER_ATTACK_MSG, enemy.getCharacterName()));

        if (damage > 30) {
            System.out.println(String.format(PLAYER_CRITICAL_HIT_MSG, enemy.getCharacterName(), damage));
        } else if (damage > 10) {
            System.out.println(String.format(PLAYER_NORMAL_HIT_MSG, damage));
        } else {
            System.out.println(String.format(PLAYER_WEAK_HIT_MSG, enemy.getCharacterName(), damage));
        }
    }

    public void printEnemyAttack(Player player, Enemy enemy, int damage) {
        System.out.println(String.format(ENEMY_ATTACK_MSG, enemy.getCharacterName()));

        if (damage > 30) {
            System.out.println(String.format(ENEMY_CRITICAL_HIT_MSG, enemy.getCharacterName(), damage));
        } else if (damage > 10) {
            System.out.println(String.format(ENEMY_NORMAL_HIT_MSG, damage));
        } else {
            System.out.println(String.format(ENEMY_WEAK_HIT_MSG, enemy.getCharacterName(), damage));
        }
    }

    public void printCharacterInfo(Character c) {
        System.out.println(c.getCharacterName());
        System.out.print("Health: ");
        int[] healthbars = c.getHealthBars();
        for (int health : healthbars) {
            int maxhealth = 100;
            System.out.print("[");
            for (int i = 0; i < health; i++) {
                System.out.print("#");
            }
            for (int i = health; i < maxhealth; i++) {
                System.out.print("_");
            }
            System.out.print("] ");
        }
        System.out.println();
    }

    public void exit() {
        System.out.println("Until next time...");
    }
}
