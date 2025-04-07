package Events;

import Characters.Players.Player;
import Equipment.Equipment;
import exceptions.RolladieException;
import Functions.UI;

import java.io.Serializable;

import static Functions.UI.readIntegerInput;


public class Shop extends Event {
    private Equipment[] equipments;
    private boolean isDone;

    public Shop(Player player, Equipment[] equipments) {
        super(player);
        this.equipments = equipments;
        isDone = false;
    }

    @Override
    public void run() throws RolladieException, InterruptedException {
        //ShopUI.printShopEntry();
        startShopping();
        //ShopUI.printShopExit();
    }


    public void handleShopInput(int input) throws RolladieException, InterruptedException {
        switch (input) {
        case 1: //buy
            //printbuyinstructions
            int buyInput = readIntegerInput();
            if (buyInput >= equipments.length || buyInput < 0) {
                UI.printErrorMessage("Buy index out of range!");
                break;
            }
            handleBuyInput(buyInput);
            break;
        case 2: //sell
            //printsellinstructions
            int sellInput = readIntegerInput();
            if (sellInput >= 3 || sellInput < 0) {
                UI.printErrorMessage("Buy index out of range!");
                break;
            }
            handleSellInput(sellInput);
            break;
        case 3: //sell
            isDone = true;
            break;
        default:
            throw new RolladieException("You can only use \"buy\", \"sell\" or \"leave\" bro");
        }
    }

    public void handleBuyInput(int buyInput) throws RolladieException, InterruptedException {
        if (buyInput >= equipments.length || buyInput < 0) {
            throw new RolladieException("Buy index out of range!");
        }
        Equipment equipment = equipments[buyInput];
        boolean hasBought = player.buyEquipment(equipment);
        if (hasBought) {
            //printBuyEquipment(equipment);
        } else {
            //printnotenuf gold
        }
    }

    public void handleSellInput(int sellInput) throws RolladieException, InterruptedException {
        if (sellInput >= 3 || sellInput < 0) {
            throw new RolladieException("Buy index out of range!");
        }
        Equipment equipment = player.getEquipment(sellInput);
        //printSellEquipment
        player.sellEquipment(sellInput);
    }


    public void startShopping() throws RolladieException, InterruptedException {
        while (!isDone) {
            //ShopUI.printShopCollection(player, equipments);
            int input = readIntegerInput();
            handleShopInput(input);
        }
    }
}
