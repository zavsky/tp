package Game.Events;

import Game.Characters.Player;

public abstract class Event {
    protected Player player;

    public Event(Player player) {
        this.player = player;
    }

    public abstract void run();
    public abstract String getEventIcon();

    /**
     * returns encoded string of the Event attributes to be saved
     * @return Encoded String
     */
    public abstract String toText();
}
