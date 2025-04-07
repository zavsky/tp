package functions;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import exceptions.RolladieException;
import functions.UI.UI;
import game.Game;
import players.Player;

/**
 * Saving and loading games
 */
public class Storage {
    public static final String SAVE_DELIMITER = " | ";
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "savefile";
    private static final String FILE_TYPE = ".txt";
    private static final String LOAD_DELIMITER = " \\| ";

    /**
     * Saves the attributes of the game into a text file
     * defined by FILE_NAME + saveSLOT + FILE_TYPE in FILE_DIRECTORY
     *
     * @param saveSlot
     * @param player
     * @param wave
     * @throws RolladieException
     */
    public static void saveGame(int saveSlot, int wave, Player player) throws RolladieException {
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filename = FILE_NAME + saveSlot + FILE_TYPE;
        File file = new File(FILE_DIRECTORY, filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);

            String waveText = Integer.toString(wave);
            fw.write(waveText + System.lineSeparator());

            String playerText = player.toText();
            fw.write(playerText + System.lineSeparator());

            fw.close();
        } catch (IOException e) {
            throw new RolladieException("❌ Save failed: " + e.getMessage());
        }
    }

/*    *//**
     * Returns Game object after decoding text from savefile into game parameters
     *
     * @return Game
     *//*
    public static Game loadGame() throws RolladieException {
        File f = new File(FILE_DIRECTORY + FILE_NAME);
        Scanner s;
        try {
            s = new Scanner(f);

            int wave = Integer.parseInt(s.nextLine().trim());

            String[] playerData = s.nextLine().split(LOAD_DELIMITER);
            Player player = parsePlayerFromText(playerData);


            return new Game(player, wave);

        } catch (FileNotFoundException e) {
            throw new RolladieException("savefile.txt not found!");
        } catch (RolladieException e) {
            UI.printErrorMessage("savefile.txt corrupted\nStarting new game");
        }
        return new Game();
    }

    private static Player parsePlayerFromText(String[] playerData) throws RolladieException {

    }*/
/*
    public static void saveGame(int saveSlot, Game game) {
        String filename = "save_slot_" + saveSlot + ".dat";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(game);
            System.out.println("✅ Game saved to save slot " + saveSlot);
        } catch (IOException e) {
            System.out.println("❌ Save failed: " + e.getMessage());
        }
    }

    public static Game loadGame(int saveSlot) throws RolladieException {
        String filename = "save_slot_" + saveSlot + ".dat";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Game game = (Game) in.readObject();
            return game;
        } catch (IOException | ClassNotFoundException e) {
            throw new RolladieException("❌ Load failed: " + e.getMessage());
        }
    }*/
}
