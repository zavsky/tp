package Game.Characters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CharacterTest {
//method_condition_expected outcome

    private Character player;
    private Character enemy;


    @Test
    void calculateDamage_enemyHasDefense_damageReduced() {
        int[] playerHealth = {100};
        int[] enemyHealth = {50};
        player = new Character(playerHealth,20, 10, "Hero");
        enemy = new Character(enemyHealth, 15, 5, "Goblin");

        int expectedDamage = (int) (player.getAttackValue() * (100.0 / (100 + enemy.getDefenseValue())));
        int actualDamage = player.calculateDamage(enemy);

        assertEquals(expectedDamage, actualDamage);

    }

    @Test
    void takeDamage_playerHasHighAttack_enemyNotAlive(){
        int[] playerHealth = {100};
        int[] enemyHealth = {20, 30};
        player = new Character(playerHealth,2000, 10, "Hero");
        enemy = new Character(enemyHealth, 10, 5, "Goblin");

        boolean expectedEnemyStatus = false;
        enemy.takeDamage(1000);

        assertEquals(expectedEnemyStatus, enemy.getCurrentStatus());
    }






}