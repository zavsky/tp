package Game;

import Game.Characters.Character;
import Game.Characters.Player;
import Game.Events.Event;
import Game.Events.Travel;

import java.util.Queue;
import java.util.LinkedList;

public class Game {
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Queue.html
    private Queue<Event> eventsQueue = new LinkedList<Event>();
    private Queue<Character> charactersQueue = new LinkedList<Character>();
    private Character currentCharacter;
    private Event currentEvent;
    private int score = 0;

    public void init() {
        int[] health1 = {100};
        Player player = new Player(health1, 10, 10, "Player");
        this.eventsQueue.add(new Travel());
        this.charactersQueue.add(player);
    }

    /* Should we implement actually implement eventsQueue
     * or should we make an abstract method in Event, public abstract Event next()
     * The latter makes it easier to dictate what happens according to the current event
     * while the former is easier for development
     */
    public void queueEvent(Event event) {
        eventsQueue.add(event);
    }

    public Event nextEvent() {
        return eventsQueue.poll();
    }

    public void queueCharacter(Character character) {
        charactersQueue.add(character);
    }

    public Character nextCharacter() {
        return charactersQueue.poll();
    }
}
