package Loot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import Characters.Players.Player;
import Events.Loot;
import org.junit.jupiter.api.Test;

public class LootTest {
    @Test
    public void lootGranted_updatesPlayerGold() {
        // Simulate enough lines for every input expected in your test flow:
        String simulatedInput = "PlayerName\n\n\n\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        System.setIn(inputStream);

        Player player = Player.createNewPlayer(); // reads "PlayerName"
        Loot loot = new Loot(player, 10);
        loot.setHasWon(true);


        try {
            loot.simulateRun(); // possibly triggers more input reads (e.g., in LootUI.halt)
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(player.getGold() >= 10);
    }
}
