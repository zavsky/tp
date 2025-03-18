package Game;

import Game.Characters.Character;
import Game.Characters.Player;
import Game.Events.Event;
import Game.Events.EventType;
import Game.Events.Travel;

import java.util.Queue;
import java.util.LinkedList;

public class Game {
    private Queue<Event> eventsQueue = new LinkedList<>(); // Might not be needed
    private Queue<Character> characterQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private int score = 0;

    public Game() {
        int[] health1 = {100};
        this.player = new Player(health1, 10, 10, "Hero");
        queueCharacter(this.player);
        this.currentEvent = new Travel(this.player);
    }

    public String move() {
        String message = "Moving to ";
        this.currentEvent = this.currentEvent.move();
        return message + this.currentEvent.toString();
    }

    public String attack() {
        this.currentEvent = this.currentEvent.attack();
        return this.currentEvent.toString();
    }

    public String defend() {
        this.currentEvent = this.currentEvent.defend();
        return this.currentEvent.toString();
    }

    public String flee() {
        this.currentEvent = this.currentEvent.flee();
        return this.currentEvent.toString();
    }

    public EventType getEventType() {
        return this.currentEvent.getEventType();
    }
    public void queueEvent(Event event) {
        eventsQueue.add(event);
    }

    public Event nextEvent() {
        return eventsQueue.poll();
    }

    public void queueCharacter(Character character) {
        characterQueue.add(character);
    }

    public Character nextCharacter() {
        return characterQueue.poll();
    }

    @Override
    public String toString() {
        return this.currentEvent.toString();
    }
}
