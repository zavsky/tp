package Exceptions;

public class InvalidActionException extends RolladieException {
    @Override
    public String getErrorMessage() {
        return "What kind of Action is that? Do you need \"Help\"?";
    }
}
