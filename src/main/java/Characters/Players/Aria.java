package Characters.Players;

import Equipment.DragonShield;
import Equipment.FlamingSword;
import Equipment.Equipment;

import java.util.List;
import java.util.ArrayList;

public class Aria extends Player {
    public Aria(boolean isHuman) {
        super("Aria", 30, 5, 2, new ArrayList<Equipment>(List.of(new DragonShield(), null, new FlamingSword())), isHuman);
    }
}
