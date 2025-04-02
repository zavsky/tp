package Game.Events.Battle;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Game.Actions.Action;
import Game.Actions.BattleAction.AttackAction;
import Game.Actions.BattleAction.DefendAction;
import Game.Actions.BattleAction.FleeAction;
import Game.Characters.Character;
import Game.Characters.Player;
import Game.RollDice;

import static Functionalities.Parser.getAction;

/**
 * Represents the player's turn. Player can select different moves during his/her turn.
 */
public class PlayerTurn extends Turn {
    private final Character player;
    private final Character enemy;


    /**
     * Constructs a PlayerTurn object to represent current player's turn.
     * Clears isDefending buff on player to prevent stacking defense.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character in battle.
     */
    public PlayerTurn(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
        if (player.getDefending()) {
            player.setDefending(false);
        }
    }

    /**
     * Uses parser to scan for user input to carry out an action.
     *
     * @return A String representing the action of the player.
     */
    private Action getCurrAction() {
        String inputString = Parser.readInput();
        return getAction(inputString);
    }

    /**
     * Handles the user input during user's turn
     *
     */
    public void handleAction() throws RolladieException {
        ((Player) player).setAttackBonus(0);
        ((Player) player).setDefenseBonus(0);

        Action currentAction = getCurrAction();
        int diceValue = RollDice.rollDice();
        int diceOutcome = RollDice.diceOutcome(diceValue);

        switch (currentAction.getName()) {
        case "defend":
            ((Player) player).setDefenseBonus(diceOutcome);
            player.setDefending(true);
            break;
        case "attack":
            ((Player) player).setAttackBonus(diceOutcome);
            player.attack(enemy);
            break;
        case "flee":
            hasSurrendered = true;
            break;
        default:
            throw new RolladieException("You can only use \"defend\", \"attack\" or \"flee\" bro");
        }

        /*
        if (currentAction instanceof AttackAction) {
            ((Player) player).setAttackBonus(diceOutcome);
            player.attack(enemy);
        }
        else if (currentAction instanceof DefendAction) {
            ((Player) player).setDefenseBonus(diceOutcome);
            player.setDefending(true);
            //System.out.println("defense, defenseBonus" +((Player) player).getDefenseValue()+" "+((Player) player).defenseBonus );
        }
        else if (currentAction instanceof FleeAction) {
            hasSurrendered = true;
        }
        */
    }
}
