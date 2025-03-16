package Game.Actions;

import Functionalities.Storage;
import Functionalities.UI;

public interface Action {
    public String execute(Game game, Storage storage, UI ui);
}
