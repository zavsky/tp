package Characters.Players;

import Equipment.*;

public class Blaze extends Player {
    public Blaze(Boolean isHuman) {
        super("Blaze", 30, 3, 3, new EquipmentList(new IronChainmail(), null, new ThunderAxe()), isHuman);
    }
}
