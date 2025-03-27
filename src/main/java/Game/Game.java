package Game;

import Functionalities.Storage;
import Functionalities.UI;
import Game.Characters.Player;
import Game.Events.Battle.Battle;
import Game.Events.Event;

import java.util.Queue;
import java.util.LinkedList;

public class Game {
    private static final int MAX_NUMBER_OF_EVENTS = 5;
    private Queue<Event> eventsQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private int score = 0;

    public Game() {
        this.player = new Player(new int[]{100}, 10, 10, "Hero");;
        this.eventsQueue = generateEventQueue();
        this.currentEvent = nextEvent();
    }

    public Game(Player player, Event currentEvent, Queue<Event> eventsQueue) {
        this.player = player;
        this.eventsQueue = eventsQueue;
        this.currentEvent = currentEvent;
    }

    public void run() {
        while(this.currentEvent!=null && this.player.isAlive) {
            saveGame();
            this.currentEvent.run();
            this.currentEvent = nextEvent();
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
