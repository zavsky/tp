package Game.Events.Shop;

import Functionalities.UI.ShopUI;
import Game.Characters.Player;
import Game.Equipment.Equipment;
import Game.Events.Event;

import static Functionalities.Storage.SAVE_DELIMITER;

public class Shop extends Event {
    private Equipment[] epuipments;

    Shop(Player player, Equipment[] equipments) {
        super(player);
        this.epuipments = equipments;
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

    }
}
