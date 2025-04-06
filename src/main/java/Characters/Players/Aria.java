package Characters.Players;

import Equipment.DragonShield;
import Equipment.FlamingSword;

public class Aria extends Player {
    public Aria(boolean isHuman) {
        super("Aria", 30, 5, 2, new FlamingSword(), new DragonShield(), isHuman);
    }
}
