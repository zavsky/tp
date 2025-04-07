package Functions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Characters.Abilities.Ability;
import Characters.Players.Player;
import exceptions.RolladieException;
import Game.Game;

/**
 * Saving and loading games
 */
public class Storage {
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

    }
}
