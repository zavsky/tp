package Characters.Abilities;

import Characters.Players.Player;

public class BasicAttack extends Ability {
    public BasicAttack() {
        super(AbilityType.BASIC_ATTACK, "Basic Attack", "", "🛡️", 0, 1, 15);
    }

    public void additionalFeatures(Player player) {

    }
}
