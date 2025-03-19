package Game.Events;

public class Battle extends Event {
    public Event next() {
        return new Battle();
    }
}
