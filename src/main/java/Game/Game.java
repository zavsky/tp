package Game;

import Game.Events.Event;

import java.util.Queue;
import java.util.LinkedList;

public class Game {
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Queue.html
    // Basic functions needed are .poll() and .add()
    private Queue<Event> eventsQueue = new LinkedList<Event>();
    private Queue<Character> charactersQueue = new LinkedList<Character>();
    private int score = 0;
}
