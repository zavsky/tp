package Game.Events;

import Game.Characters.Player;

public abstract class Event {
    public abstract EventType getEventType();

    public Event move() {
        return null;
    }

    public Event attack() {
        return null;
    }

    public Event defend() {
        return null;
    }

    @Override
    public String toString() {
        return "Event: ";
    }

    public Event flee() {
        return null;
    }
}
