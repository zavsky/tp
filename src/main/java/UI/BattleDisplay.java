package UI;

import Characters.Abilities.Ability;
import Characters.Players.Player;

public class BattleDisplay {
    /**
     * Prints to console the Player status, including name, hp, power, weapon stats, 
     * armor stats and abilities
     * 
     * @param p Player to query status on
     */
    public static void showPlayerStatus(Player p) {
        System.out.println("🧍 " + p.name + (p.isHuman ? "" : " (AI)"));
        System.out.println("HP      : " + p.hp + "/" + p.maxHp + " ❤️");
        System.out.println("Power   : " + drawPowerBar(p.power, p.maxPower));
        System.out.println("Weapon  : " + p.weapon.name + " (+" + p.weapon.bonusPerDie + " dmg)");
        System.out.println("Armor   : " + p.armor.name + " (" + p.armor.defense + " def)");
        System.out.println("Abilities:");
        
        for (int i = 0; i < p.abilities.size(); i++) {
            Ability a = p.abilities.get(i);
            String status = a.isCDReady() ? "✅ ready" : "⏳ " + a.currentCooldown + " turn(s)";
            System.out.printf(" %d. %s %s (%s)\n", i + 1, a.icon, a.name, status);
        }
        System.out.println();
    }

    public static String drawPowerBar(int power, int maxPower) {
        int segments = 20;
        int filled = (int)((power / (double)maxPower) * segments);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < segments; i++) {
            sb.append(i < filled ? "|" : " ");
        }
        sb.append("] ");
        sb.append(power).append("/").append(maxPower);
        return sb.toString();
    }
}
