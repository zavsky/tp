package players.abilities;

import java.util.ArrayList;

/**
 * Database class that maintains a list of Weapon objects.
 * This ensures easy access without the need for repeated instantiations.
 */
public class AbilitiesDatabase {
    /**
     * A static list containing predefined weapon objects.
     */
    private static final ArrayList<Ability> abilitiesList = new ArrayList<>();

    // Static block to initialize predefined weapons
    static {
        abilitiesList.add(new Weapon("Stick", 0, 10, 0, 5));
        abilitiesList.add(new Weapon("Iron Sword", 0, 20, 0, 10));
        abilitiesList.add(new Weapon("Kunai", 0, 30, 0, 20));
        abilitiesList.add(new Weapon("Executioner's Axe", 0, 40, 0, 30));
        abilitiesList.add(new Weapon("Dragon Tooth Sword", 0, 50, 0, 40));
    }
}

