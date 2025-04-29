package kingdom;

public class Adventurer {
    private String name;
    private Item item;

    public Adventurer(String name, Item item) {
        this.name = name;
        this.item = item;
    }
    
    public float calculatePower() {
        if (item != null) {
            return item.power();
        }
        else {
            return 0;
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return name;
    }
}
