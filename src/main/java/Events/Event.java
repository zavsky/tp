package Events;

import exceptions.RolladieException;
import Characters.Players.Player;

import java.io.Serializable;

public abstract class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Player player;

    public Event(Player player) {
        this.player = player;
    }

    public abstract void run() throws RolladieException;
}
