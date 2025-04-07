package Game.Events.Battle;

import Characters.Abilities.Ability;
import Characters.Abilities.BasicAttack;
import Characters.Abilities.Crush;
import Characters.Abilities.Heal;
import Characters.Abilities.PowerStrike;
import Characters.Abilities.Whirlwind;
import Characters.Players.Player;
import Equipment.Armor;
import Equipment.DragonShield;
import Equipment.FlamingSword;
import Equipment.Weapon;
import Functions.DiceBattleAnimation;
import Functions.Storage;
import Functions.TypewriterEffect;
import UI.BattleDisplay;
import UI.HpBar;
import UI.Narrator;
import java.util.Scanner;

public class NewGame {


    public Scanner scanner;
    public NewGame(Scanner scanner) {
        this.scanner = scanner;

    }

    /**
     * Creates a new human player when starting from new game
     *
     * @return Player character
     */
    public Player createNewPlayer() {
        System.out.print("Enter your hero's name: ");
        String name = scanner.nextLine();

        // todo: choose character class to vary these starting stats
        Player player = new Player(name, 100, 5, 2,
                new FlamingSword(), new DragonShield(), true);

        player.abilities.add(new BasicAttack());
        player.abilities.add(new PowerStrike());
        player.abilities.add(new Heal());

        return player;
    }

    /**
     * Main game loop logic
     *
     * @param player1 player character
     * @param wave the number of enemies encountered so far
     * @throws InterruptedException
     */
    public void startGameLoop(Player player1, int wave) throws InterruptedException {
        Player player2 = new Player("AI Bot", 60, 3, 3, new Weapon("Axe", 1), new Armor("Mail", 2), false);

        while (player1.isAlive()) {
            System.out.println("🌊 Encounter " + wave + " begins!");

            if (!player2.isAlive()) {
                player2 = generateNewEnemy(wave); // todo make tougher per wave
            }
            startBattle(player1, player2);

            if (!player1.isAlive()) {
                TypewriterEffect.print("💀 You fell at encounter " + wave, 1000);
                break;
            }

            // Heal partially, recharge power
            System.out.println("🍃 You survived! Regaining strength...");
            player1.resetAllCooldowns();
            player1.hp = Math.min(player1.maxHp, player1.hp + 10);
            player1.power = Math.min(player1.maxPower, player1.power + 20);

            wave++;

            if (wave == 3 && !player1.hasAbility("Whirlwind")) {
                player1.abilities.add(new Whirlwind());
                TypewriterEffect.print("🔥 You’ve learned Whirlwind!", 1000);
            }

            if (wave == 5) {
                player1.weapon = new Weapon("Flame Blade", 5);
                TypewriterEffect.print("🗡️ You obtained the Flame Blade!", 1000);
            }

            System.out.print("💾 Save game? (y/n): ");
            String saveInput = scanner.nextLine();
            if (saveInput.equalsIgnoreCase("y")) {
                Storage.saveGame(player1, wave, scanner);
            }
        }
    }


    /**
     * Creates a new enemy when the previous one is defeated, increasing difficulty as wave progresses
     * @param wave the number of enemies encountered so far
     * @return a new enemy player
     */
    public Player generateNewEnemy(int wave) {
        Weapon claws = new Weapon("Claws", 2 + wave);
        Armor hide = new Armor("Hide", 1 + wave / 2);
        Player enemy = new Player("Enemy " + wave, 20 + wave * 30, (3 + wave) / 2, 3, claws, hide, false);

        enemy.abilities.add(new PowerStrike());
        if (wave >= 2) enemy.abilities.add(new Heal());
        if (wave >= 3) enemy.abilities.add(new Crush());

        return enemy;
    }

    /**
     * Begins a loop battle scenario with an opponent. Exits when either one is killed
     *
     * @param player1 player character in the game
     * @param player2 opponent character in the game
     * @throws InterruptedException
     */
    private static void startBattle(Player player1, Player player2) throws InterruptedException {
        int round = 1;

        while (player1.isAlive() && player2.isAlive()) {
            System.out.println("\n================ ROUND " + round + " ================\n");

            BattleDisplay.showPlayerStatus(player1);
            BattleDisplay.showPlayerStatus(player2);

            // Choose Abilities
            Ability p1Ability = player1.chooseAbility();
            Ability p2Ability = player2.chooseAbility();
            System.out.println();

            System.out.println("[Narrator] Dice roll determines the fate of this round!");
            Thread.sleep(1000);

            // Dice Roll + Animation
            String diceDisplay = DiceBattleAnimation.animateBattle(player1.getDiceRolls(), player2.getDiceRolls());

            // Store HP before damage
            int prevHp1 = player1.hp;
            int prevHp2 = player2.hp;

            // Damage
            int p1Damage = player1.computeDamageTo(player2);
            int p2Damage = player2.computeDamageTo(player1);

            diceDisplay = player2.applyDamage(p1Damage, player1, diceDisplay);
            Narrator.commentOnMomentum(player1, player2, p1Damage, player2.hp);

            diceDisplay = player1.applyDamage(p2Damage, player2, diceDisplay);
            Narrator.commentOnMomentum(player2, player1, p2Damage, player1.hp);

            // Show result messages
            // System.out.printf("\n%s dealt %d damage to %s\n", player1.name, p1Damage, player2.name);
            // System.out.printf("%s dealt %d damage to %s\n", player2.name, p2Damage, player1.name);

            // Animate HP bars *beneath* dice
            HpBar.animate(player1, player2, prevHp1, prevHp2, diceDisplay);

            Narrator.commentOnHealth(player1);
            Narrator.commentOnHealth(player2);

            round++;
            Thread.sleep(500);

            if (round == 5 && !player1.hasAbility("Whirlwind")) {
                player1.abilities.add(new Whirlwind());
                TypewriterEffect.print("[Narrator] 🔥 " + player1.name + " has unlocked a new ability: Whirlwind!");
            }
        }

        TypewriterEffect.print("\n🏁 " + (player1.isAlive() ? player1.name : player2.name) + " wins the battle!");
    }
}



