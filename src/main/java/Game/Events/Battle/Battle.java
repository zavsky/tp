package Game.Events.Battle;

import Characters.Abilities.Ability;
import Characters.Abilities.Whirlwind;
import Characters.Players.Player;
import Exceptions.RolladieException;
import Functionalities.UI.BattleUI;
import Functions.DiceBattleAnimation;
import Functions.TypewriterEffect;
import Game.Characters.EnemyDatabase;
import Game.Events.Event;
import UI.BattleDisplay;
import UI.HpBar;
import UI.Narrator;

import static Functionalities.Storage.SAVE_DELIMITER;

/**
 * Represents a battle event in which player fights an enemy.
 */
public class Battle extends Event {
    private boolean hasWon;
    protected Player enemy;

    /**
     * Constructs a valid Battle event object.
     * Initialises hasWon to be false at start of battle.
     * @param player1 The player character in battle.
     * @param player2 The enemy character that player is facing.
     */
    public Battle(Player player1, Player player2) throws RolladieException {
        super(player1);
        final int FIRST_HEALTH_BAR = 0;
        if (player1.hp <= 0) {
            throw new RolladieException("Player health bar is empty.");
        } else if (player2.hp <= 0) {
            throw new RolladieException("Enemy health bar is empty.");
        }
        if (player1.baseAttack < 0) {
            throw new RolladieException("Player attack value is negative.");
        } else if (player2.baseAttack < 0) {
            throw new RolladieException("Enemy attack value is negative.");
        }
        hasWon = false;
        enemy = player2;
    }


    /**
     * Begins a loop battle scenario with an opponent. Exits when either one is killed
     *
     * @param player1
     * @param player2
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

    public void run(Player player1, Player player2) {
        try {
            startBattle(player1, player2);
        }
        catch (InterruptedException e) {

        }
    }

}
