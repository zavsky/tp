package Characters.Abilities;

import Characters.Players.Player;

public class Crush extends Ability {
    public Crush() {
        super(AbilityType.CRUSH, "Crush", "Flatten", "🔨", 3, 1.5, 40);
    }

    public void additionalFeatures(Player player) {
        // todo cause some debuff on opponent
    }
}
