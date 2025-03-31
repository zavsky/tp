package Game.Events;

import Game.Characters.Player;

public abstract class Event {
    protected Player player;

    public Event(Player player) {
        this.player = player;
    }

    public abstract void run();
    public abstract String getEventIcon();
    public abstract String toText();
}
