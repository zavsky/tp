package Characters.Abilities;

import Characters.Players.Player;

public class PowerStrike extends Ability {
    public PowerStrike() {
        super(AbilityType.POWER_STRIKE, "Power Strike", "Double damage", "💥", 3, 2, 30);
    }

    public void additionalFeatures(Player player) {
        
    }
}
