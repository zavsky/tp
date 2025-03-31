package Game.Events.Loot;

import Game.Characters.Player;
import Game.Currency.Gold;
import Game.Events.Event;
import Functionalities.UI.LootUI;
import static Functionalities.Storage.SAVE_DELIMITER;

public class Loot extends Event {
    private Gold loot;
    private static final String chest =
            "                            _.--.\n" +
                    "                        _.-'_:-'||\n" +
                    "                    _.-'_.-::::'||\n" +
                    "               _.-:'_.-::::::'  ||\n" +
                    "             .'`-.-:::::::'     ||\n" +
                    "            /.'`;|:::::::'      ||_\n" +
                    "           ||   ||::::::'     _.;._'-._\n" +
                    "           ||   ||:::::'  _.-!oo @.!-._'-.\n" +
                    "           \'.  ||:::::.-!()oo @!()@.-'_.|\n" +
                    "            '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n" +
                    "              `>'-.!@%()@'@_%-'_.-o _.|'||\n" +
                    "               ||-._'-.@.-'_.-' _.-o  |'||\n" +
                    "               ||=[ '-._.-\\U/.-'    o |'||\n" +
                    "               || '-.]=|| |'|      o  |'||\n" +
                    "               ||      || |'|        _| ';\n" +
                    "               ||      || |'|    _.-'_.-'\n" +
                    "               |'-._   || |'|_.-'_.-'\n" +
                    "                '-._'-.|| |' `_.-'\n" +
                    "                    '-.||_/.-'";


    Loot(Player player, int amount) {
        super(player);
        this.loot = new Gold(amount);
    }

    @Override
    public void run() {
        LootUI.printLootEntry();
        player.earnGold(loot);
        LootUI.printLootExit(chest, loot.getAmount(), player);
    }

    @Override
    public String getEventIcon() {
        return "Loot";
    }

    @Override
    public String toText() {
        return this.getEventIcon() + SAVE_DELIMITER +
                this.loot.getAmount();
    }
}
