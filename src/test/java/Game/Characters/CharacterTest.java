package Game.Characters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CharacterTest {
//method_condition_expected outcome

    private Character player;
    private Character enemy;


    @Test
    public void calculateDamage_EnemyHasDefense_DamageReduced() {
        int[] playerHealth = {100};
        int[] enemyHealth = {50};
        player = new Character(playerHealth,20, 10, "Hero");
        enemy = new Character(enemyHealth, 15, 5, "Goblin");

        int expectedDamage = (int) (player.getAttackValue() * (100.0 / (100 + enemy.getDefenseValue())));
        int actualDamage = player.calculateDamage(enemy);

        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    public void calculateDamage_EnemyIsNull_AssertionThrown(){
        int[] playerHealth = {100};
        player = new Character(playerHealth,20, 10, "Hero");

        try {
            player.calculateDamage(null);

        } catch (AssertionError e) {
            assertEquals("Defender must not be null", e.getMessage());
        }
    }

    @Test
    public void takeDamage_PlayerHasHighAttack_EnemyNotAlive(){
        int[] playerHealth = {100};
        int[] enemyHealth = {20, 30};
        player = new Character(playerHealth,2000, 10, "Hero");
        enemy = new Character(enemyHealth, 10, 5, "Goblin");

        boolean expectedEnemyStatus = false;
        enemy.takeDamage(1000);

        assertEquals(expectedEnemyStatus, enemy.getCurrentStatus());
    }

    @Test
    public void takeDamage_NegativeDamage_AssertionThrown(){
        int[] playerHealth = {100};
        player = new Character(playerHealth,2000, 10, "Hero");

        try {
            int invalidDamage = -100;
            player.takeDamage(invalidDamage);

        } catch (AssertionError e) {
            assertEquals("Damage must be non-negative", e.getMessage());
        }

    }

    @Test
    public void attack_DefenderNotAlive_AssertionThrown(){
        int[] playerHealth = {100};
        int[] enemyHealth = {20, 30};
        player = new Character(playerHealth,2000, 10, "Hero");
        enemy = new Character(enemyHealth, 10, 5, "Goblin");

        try {
            player.isAlive = false;
            enemy.attack(player);

        } catch (AssertionError e) {
            assertEquals("Damage must be alive", e.getMessage());
        }
    }


}