package Equipment;

import java.io.Serializable;

/**
 * Represents Weapon that Player can equip
 */
public class Weapon implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String name;
    public int bonusPerDie;

    public Weapon(String name, int bonusPerDie) {
        this.name = name;
        this.bonusPerDie = bonusPerDie;
    }
}
