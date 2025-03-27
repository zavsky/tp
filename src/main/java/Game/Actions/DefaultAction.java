package Game.Actions;

import Exceptions.RolladieException;

public class DefaultAction extends Action {
    public void execute() throws RolladieException {
        throw new RolladieException("What kind of Action is that? Do you need \"Help\"?");
    }
}


