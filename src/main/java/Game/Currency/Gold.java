package Game.Currency;


public class Gold {
    private final int amount;

    public Gold(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Gold earnGold(int earned) {
        return new Gold(amount + earned);
    }

    public Gold spendGold(int spent) {
        return new Gold(amount - spent);
    }

    public String toString() {
        return "Gold: " + amount;
    }
}