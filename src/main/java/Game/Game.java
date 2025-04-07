package Game;

import Events.Shop;
import exceptions.RolladieException;
import Functions.Storage;
import Functions.UI;
import Characters.Players.Player;
import Equipment.ArmorDatabase;
import Equipment.BootsDatabase;
import Equipment.Equipment;
import Equipment.WeaponDatabase;
import Events.Battle;
import Events.Event;

import java.io.Serializable;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Manages all game logic specifically: Event Generation and Sequence
 */
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_NUMBER_OF_WAVES = 5;
    private Queue<Event> eventsQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private int wave;
    private int turnsWithoutShop = 0;

    /**
     * Constructor to instantiate a new game
     * Creates a new player based on a Hero preset
     * Generates the event queue
     * Polls the first event from the queue to be the current event
     */
    public Game() {
        this.player = Player.createNewPlayer();
        this.eventsQueue = generateEventQueue();
        this.currentEvent = nextEvent();
        this.wave = 1;
    }

    public int getWave() {
        return this.wave;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Overloaded constructor used to generate defined game
     * Main usage is within the Storage class to load game from save file
     *
     * @param player
     * @param currentEvent
     * @param eventsQueue
     */
    public Game(Player player, Event currentEvent, Queue<Event> eventsQueue) {
        this.player = player;
        this.eventsQueue = eventsQueue;
        this.currentEvent = currentEvent;
    }

    /**
     * Runs the current game until the event sequence is completed
     * Ends the game prematurely if the player died within the event
     */
    public void run() {
        while (this.currentEvent != null && this.player.isAlive()) {
            try {
                saveGame();
                this.currentEvent.run();
                if (!player.isAlive()) {
                    break;
                }
                this.currentEvent = optionalShopEvent();
                saveGame();
                // To run the shop event
                // this.currentEvent.run();
                this.currentEvent = nextEvent();
            } catch (RolladieException | InterruptedException e) {
                UI.printErrorMessage(e.getMessage());
            }
            this.wave++;
        }
        UI.printDeathMessage();
    }


    /**
     * Returns a filled queue of events
     * Used during the construction of a new game
     *
     * @return eventsQueue
     */
    private Queue<Event> generateEventQueue() {
        Queue<Event> eventsQueue = new LinkedList<>();
        for (int i = 1; i < MAX_NUMBER_OF_WAVES + 1; i++) {
            eventsQueue.add(generateBattleEvent(i));
            if (wave % 2 == 0) {
                //eventsQueue.add(generateShopEvent(i));
            }
        }
        return eventsQueue;
    }

    /**
     * Returns an event to insert into the event queue
     * Current version only has a Battle event
     * Future development would include a more robust event generation
     * Idea: Interleaving the event queue with Battle and Non-Battle events
     *
     * @return Event
     */
    private Event generateBattleEvent(int wave) {
        return new Battle(this.player, wave);
    }

    public Event generateShopEvent(int wave) {
        Equipment[] equipmentsForSale = {
                ArmorDatabase.getArmorByIndex(wave / 2),
                BootsDatabase.getBootsByIndex(wave / 2),
                WeaponDatabase.getWeaponByIndex(wave / 2),
        };
        return new Shop(this.player, equipmentsForSale);
    }

    /**
     * returns the next event inside the event queue
     *
     * @return Event
     */
    private Event nextEvent() {
        return this.eventsQueue.poll();
    }

    /**
     * Calls the Storage class to save the current game status
     */
    private void saveGame() {
        UI.printMessage("💾 Save game? (y/n): ");
        String saveInput = UI.readInput();
        if (saveInput.equalsIgnoreCase("y")) {
            int saveSlot = Integer.parseInt(UI.promptSaveFile());
            Storage.saveGame(saveSlot, this);
        }
    }

    private Event optionalShopEvent() {
        if (Math.random() <= (0.3 + 0.2 * turnsWithoutShop)) {
            turnsWithoutShop = 0;
            // shop entered
            Equipment[] equipmentsForSale = {
                    ArmorDatabase.getArmorByIndex((int) (Math.random() * ArmorDatabase.getNumberOfArmorTypes())),
                    BootsDatabase.getBootsByIndex((int) (Math.random() * BootsDatabase.getNumberOfBootsTypes())),
                    WeaponDatabase.getWeaponByIndex((int) (Math.random() * WeaponDatabase.getNumberOfWeaponTypes()))
            };
            UI.printMessage("SHOP EVENT");
        } else {
            // shop not provisioned
            turnsWithoutShop++;
        }
        return null;
    }
}
