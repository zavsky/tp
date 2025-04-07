package oldgame.events.shop;

import exceptions.RolladieException;
import functionalities.Parser;
import functionalities.ui.ShopUI;
import functionalities.ui.UI;
import oldgame.actions.Action;
import oldgame.characters.Player;
import oldgame.equipment.Equipment;
import oldgame.events.Event;

import static functionalities.Parser.getShopAction;
import static storage.Storage.SAVE_DELIMITER;

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
        String shopText = this.getEventIcon();
        for (Equipment equipment: equipments) {
            shopText += SAVE_DELIMITER + equipment.toText();
        }
        return shopText;
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
            break;
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
