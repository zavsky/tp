package Game;

import Game.Battle.Battle;
import Game.Characters.Character;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Events.Event;
import Game.Events.EventType;
import Game.Events.Travel;
import Game.Menu.MenuSystem;
import Game.Menu.MenuSystem.MenuAction;

import java.util.Queue;

import Functionalities.UIHandler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Game {
    private Queue<Event> eventsQueue = new LinkedList<>(); // Might not be needed
    private Queue<Character> characterQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private int score = 0;

    private UIHandler UI = new UIHandler();
    private MenuSystem menuSystem = new MenuSystem();

    public Game(MenuSystem menuSystem) {
        
        this.menuSystem = menuSystem;
    }

    public boolean gameStart() {
        this.player = new Player("Player");
        queueCharacter(this.player);
        this.currentEvent = new Travel(this.player);
        
        menuSystem.exitPrivateMode();
        UI.printWelcomeMessage();
        UI.printMessage(this.toString());

        while (player.isAlive) {
            int[] enemyHealth = {10, 10};
            Enemy enemy = new Enemy(enemyHealth, 10, 10, "Enemy");
            UI.battleEntry(enemy);
            Battle battle = new Battle(player, enemy);
            battle.startBattle();
            UI.battleExit(enemy, player);
        }
        menuSystem.enterPrivateMode();
        return false;
    }

    public void start() throws IOException {
        Map<String, MenuAction> mainMenu = new LinkedHashMap<>();
        mainMenu.put("Start Game", () -> { gameStart(); return false; });
        mainMenu.put("Load Game", () -> { System.out.println("Loading game..."); return false; });
        mainMenu.put("Exit", () -> { System.exit(0); return true; });

        menuSystem.displayMenu("Main Menu", mainMenu);
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
