package Game.Events.Shop;

import Functionalities.Parser;
import Functionalities.UI.ShopUI;
import Game.Characters.Player;
import Game.Equipment.Equipment;
import Game.Events.Event;

import static Functionalities.Storage.SAVE_DELIMITER;

public class Shop extends Event {
    private Equipment[] equipments;

    Shop(Player player, Equipment[] equipments) {
        super(player);
<<<<<<< HEAD
        this.equipments = epuipments;
=======
        this.epuipments = equipments;
>>>>>>> d833ec3eac813ac0ee394bd7d7c9b4556e831876
    }

    @Override
    public void run() {
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

    public void startShopping() {
        String inputString;
        do {
            inputString = Parser.readInput().trim().toLowerCase();
            if (Character.isDigit(inputString.charAt(0))) {
                int index = Integer.parseInt(inputString) - 1;
                player.buyEquipment(this.equipments[index]);

                //UI.printBuyEquipment(equipment);
            } //error handling
        } while(!inputString.equals("leave"));

    }
}
