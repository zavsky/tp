import static org.junit.jupiter.api.Assertions.*;

import Characters.Players.Player;
import Equipment.DragonShield;
import Equipment.FlamingSword;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

class RolladieTest {

    @Test
    public void createNewPlayer_testStatusOfNewPlayer_playerIsAlive() {
        String simulatedName = "HeroName\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedName.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Player player = Rolladie.createNewPlayer(scanner);
        assertTrue(player.isAlive());
    }

    @Test
    public void createNewPlayer_testStatusOfNewPlayer_playerIsHuman(){
        String simulatedName = "HeroName\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedName.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Player player = Rolladie.createNewPlayer(scanner);
        assertTrue(player.isHuman);
    }

    @Test
    public void createNewPlayer_scannerIsNull_assertionThrown() {
       Scanner scanner = null;
        try {
            Rolladie.createNewPlayer(scanner);
        } catch (AssertionError e) {
            assertEquals("Scanner object cannot be null", e.getMessage());
        }

    }


    @Test
    public void startGameLoop_noEnemy_assertionThrown() throws InterruptedException {
        String simulatedInput = "n\nn\nn\n"; // adjust for how many waves you want
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        Player player = new Player("Hero", 100,
                5, 2, new FlamingSword(), new DragonShield(), true);
        int numberOfEnemy = 0;
        try {
            Rolladie.startGameLoop(player, numberOfEnemy, scanner);
        } catch (AssertionError e) {
            assertEquals("Number of enemy encountered must be at least 1", e.getMessage());
        }
    }

    @Test
    public void generateNewEnemy_waveEqualsFive_enemyHasCrush() {
        int wave = 5;
        Player enemy = Rolladie.generateNewEnemy(wave);
        assertTrue(enemy.hasAbility("crush"));
    }

    @Test
    public void generateNewEnemy_waveEqualsZero_assertionThrown() {
        int wave = 0;
        try {
            Rolladie.generateNewEnemy(wave);
        } catch (AssertionError e) {
            assertEquals("Number of enemy encountered must be at least 1", e.getMessage());
        }
    }
}