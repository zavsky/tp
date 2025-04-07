package Characters.Players;

import Equipment.DragonShield;
import Equipment.EquipmentList;
import Equipment.FlamingSword;

public class Aria extends Player {
    public Aria(boolean isHuman) {
        super("Aria", 30, 5, 2, new EquipmentList(new DragonShield(), null, new FlamingSword()), isHuman);
    }
}
