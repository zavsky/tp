package Loot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import Characters.Players.Player;
import Events.Loot;
import org.junit.jupiter.api.Test;

public class LootTest {
    @Test
    public void lootGranted_updatesPlayerGold() {
        String simulatedInput = "Player\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        Player player = Player.createNewPlayer();
        Loot loot = new Loot(player, 10);
        loot.setHasWon(true);
        try {
            loot.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(player.gold >= 10);
    }
}
