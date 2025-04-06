package Equipment;

import java.io.Serializable;

/**
 * Represents Armor that Player can augment
 */
public class Armor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String name;
    public int defense;

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }
}
