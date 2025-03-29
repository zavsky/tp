package Game;

import Exceptions.ExceptionMessage;
import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI;
import Game.Battle.Battle;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Actions.Action;


public class Rolladie {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final Game GAME = new Game();
    private static final Parser PARSER = new Parser();
    private static final Storage STORAGE = new Storage();


    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";
        UI.printWelcomeMessage();
        int[] health1 = {100};
        int[] health2 = {50};
        Player player = new Player(health1, 10, 10, "Player");
        Enemy enemy = new Enemy(health2, 10, 10, "Enemy");
        try {
            UI.battleEntry(enemy);
            Battle battle = new Battle(player, enemy);
            battle.startBattle();
            UI.battleExit(enemy, player);
        }
        catch (ExceptionMessage e){
            System.out.println(e.getMessage());
        }
    }
}
