package Game.Events;

public class Travel extends Event {
    public Event next() {
        return new Travel();
    }
}
