package Functionalities;

import Exceptions.RolladieException;
import Game.Characters.Character;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Events.Battle.Battle;
import Game.Events.Event;
import Game.Game;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final String VALID_FILE_DIRECTORY = "test/data/StorageTest";
    private static final String VALID_FILE_NAME = "ValidData.txt";

    private static Player testPlayer = new Player(new int[]{100}, 10, 10, "Hero");
    private static Enemy testEnemy = new Enemy(new int[]{50}, 15, 5, "Goblin");
    private static Battle testBattle = new Battle(testPlayer, testEnemy);
    private Event testCurrentEvent = testBattle;
    private Queue<Event> testEventsQueue = new LinkedList<>();
}
