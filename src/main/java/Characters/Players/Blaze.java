package Characters.Players;

import Equipment.*;

import java.util.ArrayList;
import java.util.List;

public class Blaze extends Player {
    public Blaze(Boolean isHuman) {
        super("Blaze", 30, 3, 3, new ArrayList<Equipment>(List.of(new IronChainmail(), null, new ThunderAxe())), isHuman);
    }
}
