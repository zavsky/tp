package Game;

import Events.Loot;
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
    private boolean winCurrBattle = false;

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
                //If current battle is won, sets loot event to give rewards
                currentEvent.setHasWon(winCurrBattle);
                this.currentEvent.run();
                if (!player.isAlive()) {
                    break;
                }
                //Checks if current battle is won
                if (this.currentEvent instanceof Battle) {
                    winCurrBattle = currentEvent.getHasWon();
                }
                //Saves game on loot or shop screen after a battle.
                if (this.currentEvent instanceof Loot) {
                    saveGame();
                }

                //this.currentEvent = optionalShopEvent();
                // To run the shop event
                // this.currentEvent.run();
                this.currentEvent = nextEvent();
            } catch (RolladieException e) {
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
        Battle battle1 = generateBattle();
        Battle battle2 = generateBattle();
        Battle battle3 = generateBattle();
        eventsQueue.add(battle1);
        eventsQueue.add(generateLoot(20));
        eventsQueue.add(battle2);
        eventsQueue.add(generateLoot(30));
        eventsQueue.add(battle3);
        eventsQueue.add(generateLoot(40));
        return eventsQueue;
    }

    /**
     * Returns a battle to insert into the event queue.
     *
     * @return Event
     */
    private Battle generateBattle() {
        Battle newBattle = new Battle(this.player, wave);
        wave++; //increment wave after battle
        return newBattle;
    }

    private Event generateLoot(int loot) {
        return new Loot(this.player, loot);
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
