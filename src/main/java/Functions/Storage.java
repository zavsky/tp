package Functions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Characters.Abilities.Ability;
import Characters.Players.Player;

/**
 * Saving and loading games
 */
public class Storage {
    public static void saveGame(Player player, int wave, Scanner scanner) {
        System.out.print("Choose save slot (1–3): ");
        int slot = Integer.parseInt(scanner.nextLine());

        String filename = "save_slot_" + slot + ".dat";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(player);
            out.writeInt(wave);
            System.out.println("✅ Game saved to slot " + slot);
        } catch (IOException e) {
            System.out.println("❌ Save failed: " + e.getMessage());
        }
    }

    public static Pair<Player, Integer> loadGame(Scanner scanner) {
        System.out.print("Choose save slot to load (1–3): ");
        int slot = Integer.parseInt(scanner.nextLine());
    
        String filename = "save_slot_" + slot + ".dat";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Player player = (Player) in.readObject();
            int wave = in.readInt();
            System.out.println("✅ Game loaded from slot " + slot);
            showContinueScreen(player, wave, scanner);
            return new Pair<>(player, wave);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Load failed: " + e.getMessage());
            return null;
        }
    }    

    public static void showContinueScreen(Player player, int wave, Scanner scanner) {
        System.out.println("\n===== CONTINUE SCREEN =====");
        System.out.println("👤 Name: " + player.name);
        System.out.println("❤️ HP: " + player.hp + " / " + player.maxHp);
        System.out.println("⚡ Power: " + player.power + " / " + player.maxPower);
        System.out.println("🗡️ Weapon: " + player.weapon.name + " (+" + player.weapon.bonusPerDie + " dmg)");
        System.out.println("🛡️ Armor: " + player.armor.name + " (+" + player.armor.defense + " def)");

        System.out.println("🧪 Abilities:");
        for (Ability a : player.abilities) {
            System.out.printf("   • %s (%s) | Cooldown: %d | Cost: %d\n", a.name, a.icon, a.cooldown, a.powerCost);
        }

        System.out.println("🌊 Current Wave: " + wave);
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
