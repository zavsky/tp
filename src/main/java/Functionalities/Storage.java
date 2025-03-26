package Functionalities;

import Game.Characters.Player;
import Game.Events.Event;
import Game.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Storage {
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "savefile.txt";
    private static final String LOAD_DELIMITER = " \\| ";
    public static final String SAVE_DELIMITER = " | ";


    public static void saveGame(Player player, Event currentEvent, Queue<Event> eventsQueue) {
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

            String playerText = player.toText();
            fw.write(playerText + System.lineSeparator());

            String currentEventText = currentEvent.toText();
            fw.write(currentEventText + System.lineSeparator());

            for (Event event: eventsQueue) {
                String textToAdd = event.toText();
                fw.write(textToAdd + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            UI.printMessage("savefile.txt failed to save");
        }
        UI.printMessage("savefile.txt saved successfully");
    }

/*    public static Game loadGame() {
        File f = new File(FILE_DIRECTORY + FILE_NAME);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            UI.printMessage("savefile.txt not found!");
            return new Game();
        }
        while (s.hasNext()) {
            try {
                Player player;
                Event currentEvent;
                Queue<Event> eventsQueue;
                String[] commandArgs = s.nextLine().split(LOAD_DELIMITER);
                temp = parseTasksFromText(commandArgs);
            } catch (Exception e) {
                return new Game();
            }
            tasks.add(tasks.size(), temp);
        }
        UI.printMessage("savefile.txt loaded successfully");
    }*/
}
