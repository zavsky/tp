package Game;

import Exceptions.InvalidActionException;
import Game.Characters.Character;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Events.Battle.Battle;
import Game.Events.Event;
import Game.Events.EventType;
import Game.Events.Travel;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    private Queue<Event> eventsQueue = new LinkedList<>(); // Might not be needed
    private Queue<Character> characterQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private EventType eventType = EventType.TRAVEL;
    private int score = 0;

    public Game() {
        int[] health1 = {100};
        this.player = new Player(health1, 10, 10, "Hero");
        queueCharacter(this.player);
        this.currentEvent = new Travel();
    }

    public void move() throws InvalidActionException {
        if (this.currentEvent instanceof Travel) {
            Random rand = new Random();
            int chance = rand.nextInt(100);
            if (chance > 25) {
                int[] enemyHealth = {50};
                Enemy enemy = new Enemy(enemyHealth, 15, 5, "Goblin");
                currentEvent = new Battle(this.player, enemy);
            } else {
                currentEvent = new Travel();
            }
        } else {
            throw new InvalidActionException();
        }
    }

    public EventType getEventType() {
        return this.eventType;
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
}
