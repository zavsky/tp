package events;

import exceptions.RolladieException;
import players.Player;

import java.io.Serializable;

public abstract class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    public boolean isExit;
    protected Player player;
    protected boolean hasWon;

    public Event(Player player) {
        this.player = player;
        hasWon = false;
        isExit = false;
    }

    public boolean getHasWon() {
        return hasWon;
    }
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
    public abstract void run() throws RolladieException, InterruptedException;
}
