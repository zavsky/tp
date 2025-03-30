package Game.Events.Shop;

import Functionalities.Parser;
import Game.Characters.Player;
import Game.Equipment.Equipment;

public class ShopInteraction {
    private Player player;
    private Equipment[] equipments;
    private boolean doneShopping;

    ShopInteraction(Player player, Equipment[] equipments) {
        this.player = player;
        this.equipments = equipments;
    }

    public void run() {
        //UI.printShopEquipment(equipments);
        String inputString;
        do {
            inputString = Parser.readInput().trim().toLowerCase();
            if (Character.isDigit(inputString.charAt(0))) {
                int index = Integer.parseInt(inputString) - 1;
                player.buyEquipment(equipments[index]);

                //UI.printBuyEquipment(equipment);
            } //error handling
        } while(!inputString.equals("leave"));

    }
}
