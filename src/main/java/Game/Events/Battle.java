package Game.Events;

import Game.Characters.Character;
import Game.Characters.Enemy;
import Game.Characters.Player;

import java.util.Random;

public class Battle extends Event {
    private final Player player;
    private final Enemy enemy;

    public Battle(Player player) {
        this.player = player;
        int[] enemyHealth = {50};
        this.enemy = new Enemy(enemyHealth, 15, 5, "Goblin");
    }

    private Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public Event attack() {
        this.player.attack(this.enemy);
        if (this.enemy.isAlive) {
            this.enemy.attack(this.player);
            if (this.player.isAlive) {
                // Continue battle
                return new Battle(this.player, this.enemy);
            } else {
                // Enemy died
                return new Death(this.player);
            }
        } else {
            // Player died
            return new Travel(this.player);
        }
    }

    @Override
    public Event defend() {
        return new Battle(this.player, this.enemy);
    }

    @Override
    public Event flee() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < 25) {
            // Failed to flee
            this.enemy.attack(this.player);
            if (this.player.isAlive) {
                return new Battle(this.player, this.enemy);
            } else {
                // Player died
                return new Death(this.player);
            }
        } else {
            return new Travel(this.player);
        }
    }

    @Override
    public EventType getEventType() {
        return EventType.BATTLE;
    }

    @Override
    public String toString() {
        return super.toString() + "Battle" + System.lineSeparator() +
                this.player.toString() + System.lineSeparator() +
                this.enemy.toString() + System.lineSeparator();
    }
}
