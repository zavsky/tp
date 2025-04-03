package Functionalities;

import Exceptions.RolladieException;
import Functionalities.UI.UI;
import Game.Characters.Character;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Equipment.Equipment;
import Game.Equipment.EquipmentList;
import Game.Events.Battle.Battle;
import Game.Events.Event;
import Game.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

/**
 * Translates the game data from and into text save file
 * savefile.txt is structured as such:
 * 1. player
 * 2. currentEvent
 * 3. remaining events in eventsQueue
 */
public class Storage {
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "savefile.txt";
    private static final String LOAD_DELIMITER = " \\| ";
    public static final String SAVE_DELIMITER = " | ";

    /**
     * Saves the attributes of the game into a text file
     * defined by FILE_NAME in FILE_DIRECTORY
     *
     * @param player
     * @param currentEvent
     * @param eventsQueue
     */
    public static void saveGame(Player player, Event currentEvent, Queue<Event> eventsQueue) throws RolladieException {
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(FILE_DIRECTORY, FILE_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);

            // Encoding and saving of player data
            String playerText = player.toText();
            fw.write(playerText + System.lineSeparator());

            // Encoding and saving of currentEvent data
            String currentEventText = currentEvent.toText();
            fw.write(currentEventText + System.lineSeparator());

            // Encoding and saving of remaining event in eventsQueue
            for (Event event : eventsQueue) {
                String textToAdd = event.toText();
                fw.write(textToAdd + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new RolladieException("savefile.txt failed to save");
        }
    }
    private static EquipmentList parseEquipmentListFromText(String[] parameters) {
        return null;
    }

    /**
     * Returns subclass of Character defined by characterType
     * Decodes Character from text within savefile
     *
     * @param characterType
     * @param parameters
     * @return subclass of Character
     * @throws RolladieException
     */
    private static Character parseCharacterFromText(String characterType, String[] parameters) throws RolladieException {
        String healthBarsString = parameters[0];
        // https://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java
        String[] values = healthBarsString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
        int[] healthBars = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            try {
                healthBars[i] = Integer.parseInt(values[i]);
            } catch (NumberFormatException nfe) {
                throw new RolladieException("Parsing failed");
            }
        }
        int attackValue = Integer.parseInt(parameters[1]);
        int defenseValue = Integer.parseInt(parameters[2]);
        String characterName = parameters[3];
        int maxHealth = Integer.parseInt(parameters[4]);
        if (characterType.equals("Player")) {
            int amount = Integer.parseInt(parameters[5]);
            String[] equipmentParameters = Arrays.copyOfRange(parameters, 6, parameters.length + 1);
            EquipmentList equipments = parseEquipmentListFromText(equipmentParameters);
            return new Player(healthBars, attackValue, defenseValue, characterName, maxHealth, amount, equipments);
        } else if (characterType.equals("Enemy")) {
            return new Enemy(healthBars, attackValue, defenseValue, characterName, maxHealth);
        } else {
            throw new RolladieException("Invalid Character Type");
        }
    }

    /**
     * Returns subclass of Event defined by eventType
     * Decodes Event from text within savefile
     *
     * @param player
     * @param parameters
     * @return subclass of Event
     * @throws RolladieException
     */
    private static Event parseEventFromText(Player player, String[] parameters) throws RolladieException {
        String eventType = parameters[0];
        switch (eventType) {
        case "Battle":
            try {
                Enemy enemy = (Enemy) parseCharacterFromText("Enemy", Arrays.copyOfRange(parameters, 1, parameters.length + 1));
                return new Battle(player, enemy);
            } catch (RolladieException e) {
                System.out.println(e.getMessage());
            }
        default:
            throw new RolladieException("Invalid Event Type");
        }
    }

    /**
     * Returns Game object after decoding text from savefile into game parameters
     *
     * @return Game
     */
    public static Game loadGame() throws RolladieException {
        File f = new File(FILE_DIRECTORY + FILE_NAME);
        Scanner s;
        try {
            s = new Scanner(f);
            // 1st line is player data
            String[] playerData = s.nextLine().split(LOAD_DELIMITER);
            Player player = (Player) parseCharacterFromText("Player", playerData);

            // 2nd line is currentEvent data
            String[] currentEventData = s.nextLine().split(LOAD_DELIMITER);
            Event currentEvent = parseEventFromText(player, currentEventData);

            // 3rd line onwards is eventsQueue data
            Queue<Event> eventsQueue = new LinkedList<>();
            while (s.hasNext()) {
                String[] remainingLine = s.nextLine().split(LOAD_DELIMITER);
                Event event = parseEventFromText(player, remainingLine);
                eventsQueue.add(event);
            }
            return new Game(player, currentEvent, eventsQueue);

        } catch (FileNotFoundException e) {
            throw new RolladieException("savefile.txt not found!");
        } catch (RolladieException e) {
            UI.printErrorMessage("savefile.txt corrupted\nStarting new game");
        }
        return new Game();
    }
}
