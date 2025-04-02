package Game.Actions;

public abstract class Action {
    private String args;


    public Action() {
        this.args = null;
    }

    public Action(String args){
        this.args = args;
    }


    public abstract String getName();
    public String getArgs() {
        return args;
    };
}
