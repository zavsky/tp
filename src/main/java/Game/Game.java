package Game;

import Exceptions.RolladieException;
import Functionalities.Storage;
import Functionalities.UI;
import Functionalities.Parser;
import Game.Actions.Action;
import Game.Characters.Player;
import Game.Events.Battle.Battle;
import Game.Events.Event;

import java.util.Queue;
import java.util.LinkedList;

public class Game {
    private static final int MAX_NUMBER_OF_EVENTS = 10;
    private static final Player HERO = new Player(new int[]{100}, 10, 10, "Hero");

    private Queue<Event> eventsQueue = new LinkedList<>(); // Might not be needed
    private Player player;
    private Event currentEvent;
    private int score = 0;

    public Game() {
        this.player = HERO;
        this.eventsQueue = generateEventQueue();
        this.currentEvent = nextEvent();
        saveGame();
    }

    public Game(Player player, Queue<Event> eventsQueue, Event currentEvent) {
        this.player = player;
        this.eventsQueue = eventsQueue;
        this.currentEvent = currentEvent;
        saveGame();
    }

    public void run() {
        while(!eventsQueue.isEmpty() && this.player.isAlive) {
            this.currentEvent.run();
            this.currentEvent = nextEvent();
            saveGame();
        }
        if (!this.player.isAlive) {
            UI.printMessage("Game over, you've died! L");
        }
    }
    
    private Queue<Event> generateEventQueue() {
        Queue<Event> eventsQueue = new LinkedList<>();
        for (int i = 0; i < MAX_NUMBER_OF_EVENTS; i++) {
            eventsQueue.add(generateEvent());
        }
        return eventsQueue;
    }

    private Event generateEvent() {
        return new Battle(this.player);
    }

    private Event nextEvent() {
        return this.eventsQueue.poll();
    }

    private void saveGame(){
        Storage.saveGame(this.player, this.currentEvent, this.eventsQueue);
    }

    @Override
    public String toString() {
        return this.currentEvent.toString();
    }
}
