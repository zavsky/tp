package Game.Events.Shop;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI.ShopUI;
import Functionalities.UI.UI;
import Game.Actions.Action;
import Game.Actions.ShopAction.BuyAction;
import Game.Characters.Player;
import Game.Equipment.Equipment;
import Game.Events.Event;
import Game.Menu.MenuSystem;
import Game.Game;
import Game.RollDice;

import static Functionalities.Storage.SAVE_DELIMITER;

import com.googlecode.lanterna.terminal.Terminal;

public class Shop extends Event {
    private Equipment[] equipments;
    private boolean isDone;
    private Terminal terminal;
    private MenuSystem menuSystem;
    private Parser parser = new Parser();

    public Shop(Terminal terminal, MenuSystem menuSystem, Player player, Equipment[] equipments) {
        super(player);
        this.equipments = equipments;
        isDone = false;
        this.terminal = terminal;
        this.menuSystem = menuSystem;
    }

    @Override
    public void run() throws RolladieException {
        ShopUI.printShopEntry(terminal);
        startShopping();
        ShopUI.printShopExit(terminal);
    }

    @Override
    public String getEventIcon() {
        return "Shop";
    }

    @Override
    public String toText() {
        return this.getEventIcon();
    }

    private Action getCurrAction() {
        return parser.getShopAction(menuSystem, player.getEquipments(), equipments);
    }

    public void handleAction() throws RolladieException {
        Action currentAction = getCurrAction();

        switch (currentAction.getName()) {
        case "buy":
            int equipmentIndex = Integer.parseInt(currentAction.getArgs()) - 1;
            player.buyEquipment(equipments[equipmentIndex]);
            ShopUI.printBuyEquipment(terminal, equipments[equipmentIndex]);
            break;
        case "sell":
            String equipmentType = currentAction.getArgs();
            player.sellEquipment(equipmentType);
            ShopUI.printSellEquipment(terminal, player.getEquipment(equipmentType));
            break;
        case "leave":
            isDone = true;
            break;
        default:
            throw new RolladieException("You can only use \"buy\", \"sell\" or \"leave\" bro");
        }
    }


    public void startShopping() throws RolladieException {
        while (!isDone) {
            ShopUI.printShopCollection(terminal, player, equipments);
            handleAction();
        }
    }
}
