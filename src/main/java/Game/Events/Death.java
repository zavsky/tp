package Game.Events;

import Game.Characters.Player;

public class Death extends Event{
    private final Player player;

    public Death(Player player) {
        this.player = player;
    }

    @Override
    public EventType getEventType() {
        return EventType.DEATH;
    }

    @Override
    public String toString() {
        return super.toString() + "Death" + System.lineSeparator() +
                this.player.toString() + System.lineSeparator() +
                "Game over, you've died! L ";
    }
}
