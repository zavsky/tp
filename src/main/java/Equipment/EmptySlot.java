package Equipment;

public class EmptySlot extends Equipment {
    public EmptySlot() {
        super("empty", 0,0,0, 0);
    }

    @Override
    public String getEquipmentType() {
        return "empty";
    }

    @Override
    public int getId() {
        return -1;
    }
}
