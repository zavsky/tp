package Game;

import java.util.Arrays;

import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Battle.Battle;
import Game.Characters.Enemy;
import Game.Characters.Player;

public class Rolladie {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Game game = new Game();
        UI ui = new UI();
        Parser parser = new Parser();
        Storage storage = new Storage();

        ui.printWelcomeMessage();
        int[] health1 = {100};
        int[] health2 = {50};
        Player player = new Player(health1, 10, 10, "Player");
        Enemy enemy = new Enemy(health2, 10, 10, "Enemy");
        UI.battleEntry(enemy);
        Battle battle = new Battle(player, enemy);
        battle.startBattle();
        UI.battleExit(enemy, player);
    }
}
