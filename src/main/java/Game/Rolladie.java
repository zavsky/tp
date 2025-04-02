package Game;

import java.io.IOException;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UIHandler;
import Game.Battle.Battle;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Menu.MenuSystem;
import Game.Actions.Action;


public class Rolladie {
    // private static final Game GAME = new Game();
    private static final Parser PARSER = new Parser();
    private static final Storage STORAGE = new Storage();
    private static final UIHandler UI = new UIHandler();
    private static Game GAME;


    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";
        // Player player = new Player("Player");
        // GAME = new Game(player);
        // UI.printWelcomeMessage();
        // UI.printMessage(GAME.toString());
        // while (player.isAlive) {
        //     int[] enemyHealth = {10, 10};
        //     Enemy enemy = new Enemy(enemyHealth, 10, 10, "Enemy");
        //     UI.battleEntry(enemy);
        //     Battle battle = new Battle(player, enemy);
        //     battle.startBattle();
        //     UI.battleExit(enemy, player);
        // }

        // while(true) {
        //     try {
        //         String userInput = PARSER.readInput();
        //         Action action = PARSER.getAction(userInput);
        //         UI.printMessage("You chose to: " + action);

        //         boolean isTrue = true;

        //         if (action.execute(GAME, STORAGE, UI)) {
        //             UI.exit();
        //             break;
        //         }

        //         assert isTrue = false : "isTrue should be false";
        //     } catch (RolladieException e) {
        //         UI.printMessage(e.getErrorMessage());
        //     } catch (Exception e) {
        //         UI.printMessage(e.getMessage());
        //         break;
        //     }
        // }
        
        MenuSystem menuSystem = new MenuSystem();
        Game game = new Game(menuSystem);
        try {
            game.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
