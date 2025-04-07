package Events;

import Characters.Players.Player;
import exceptions.RolladieException;
import UI.LootUI;

import java.util.Random;

public class Loot extends Event {
    private int baseLoot;

    public Loot(Player player, int loot) {
        super(player);
        baseLoot = loot;
    }



    /**
     * Runs the loot event, which generates loot from 30 to 70 gold and returns it to player.
     * @throws RolladieException
     */
    @Override
    public void run() throws RolladieException {
        if (hasWon) {
            int loot = generateRandomLoot();
            player.addGold(loot);
            LootUI.printLoot(loot);
        }
        else {
            LootUI.printNoLoot();
        }
    }

    private int generateRandomLoot() throws RolladieException {
        Random rand = new Random();
        int bonusLoot = rand.nextInt(10);
        return baseLoot + bonusLoot;
    }
}
