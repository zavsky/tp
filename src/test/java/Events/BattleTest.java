package Events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import Characters.Players.Player;
import Equipment.DragonShield;
import Equipment.EquipmentList;
import Equipment.FlamingSword;



class BattleTest {


    @Test
    public void startGameLoop_noEnemy_assertionThrown() throws InterruptedException, exceptions.RolladieException {

        EquipmentList equipmentList = new EquipmentList(new DragonShield(), null, new FlamingSword());
        Player player = new Player("Hero", 100, 5, 2, equipmentList, true);

        try {
            int numberOfEnemy = 0;
            Battle battle = new Battle(player, numberOfEnemy);
            battle.startGameLoop(player, numberOfEnemy);

        } catch (AssertionError e) {
            assertEquals("Number of enemy encountered must be at least 1", e.getMessage());
        }
    }

    @Test
    public void generateNewEnemy_waveEqualsFive_enemyHasCrush() {
        int wave = 5;
        Player enemy = Battle.generateNewEnemy(wave);
        assertTrue(enemy.hasAbility("crush"));
    }

    @Test
    public void generateNewEnemy_waveEqualsZero_assertionThrown() {
        int wave = 0;
        try {
            Battle.generateNewEnemy(wave);
        } catch (AssertionError e) {
            assertEquals("Number of enemy encountered must be at least 1", e.getMessage());
        }
    }

}