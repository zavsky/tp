package Game.Events;

import Game.Characters.Player;
import Game.Events.Battle;

import java.util.Random;

public class Travel extends Event {
    private final Player player;

    public Travel(Player player) {
        this.player = player;
    }

    @Override
    public EventType getEventType() {
        return EventType.TRAVEL;
    }

    @Override
    public Event move() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < 25) {
            return new Travel(this.player);
        } else {
            return new Battle(this.player);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Travel" + System.lineSeparator() +
                this.player.toString();
    }
}
