package Game.Currency;


public class Gold {
    private final int amount;

    public Gold(int amount) {
        this.amount = Math.max(amount, 0);
    }

    public int getAmount() {
        return amount;
    }

    public Gold earnGold(Gold earned) {
        return new Gold(amount + earned.getAmount());
    }

    public Gold spendGold(Gold spent) {
        return new Gold(amount - spent.getAmount());
    }

    public String toString() {
        return "Gold: " + amount;
    }
}