package Characters.Players;

import Equipment.IronChainmail;
import Equipment.ThunderAxe;

public class Blaze extends Player {
    public Blaze(Boolean isHuman) {
        super("Blaze", 30, 3, 3, new ThunderAxe(), new IronChainmail(), isHuman);
    }
}
