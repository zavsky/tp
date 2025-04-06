package Characters.Abilities;

import Characters.Players.Player;

public class Heal extends Ability {
    public Heal() {
        super(AbilityType.HEAL, "Heal", "+5 HP", "🛡️", 2, 1, 10);
    }

    public void additionalFeatures(Player player) {
        player.updatePower(20);
    }
}
