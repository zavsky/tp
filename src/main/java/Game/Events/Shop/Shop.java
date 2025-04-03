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
import Game.Game;
import Game.RollDice;

import static Functionalities.Parser.getShopAction;
import static Functionalities.Storage.SAVE_DELIMITER;

public class Shop extends Event {
    private Equipment[] equipments;
    private boolean isDone;

    public Shop(Player player, Equipment[] equipments) {
        super(player);
        this.equipments = equipments;
        isDone = false;
    }

    @Override
    public void run() throws RolladieException {
        ShopUI.printShopEntry();
        startShopping();
        ShopUI.printShopExit();
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
        String inputString = Parser.readInput();
        return getShopAction(inputString);
    }

    public void handleAction() throws RolladieException {
        Action currentAction = getCurrAction();

        switch (currentAction.getName()) {
        case "buy":
            int equipmentIndex = Integer.parseInt(currentAction.getArgs()) - 1;
            Equipment equipment = equipments[equipmentIndex];
            if (player.hasEnoughGold(equipment.getValue())) {
                player.buyEquipment(equipment);
                ShopUI.printBuyEquipment(equipment);
            } else {
                UI.printErrorMessage("You don't have enough gold to buy this equipment!");
            }
            break;
        case "sell":
            String equipmentType = currentAction.getArgs();
            player.sellEquipment(equipmentType);
            ShopUI.printSellEquipment(player.getEquipment(equipmentType));
            break;
        case "leave":
            isDone = true;
        default:
            throw new RolladieException("You can only use \"buy\", \"sell\" or \"leave\" bro");
        }
    }


    public void startShopping() throws RolladieException {
        while (!isDone) {
            ShopUI.printShopCollection(player, equipments);
            handleAction();
        }
    }
}
