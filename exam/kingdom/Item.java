package kingdom;

public abstract record Item() {
    private String name;
    private float power;
    private ItemType itemType;

    public Item(String name, float power, ItemType itemType) {
        this.name = name;
        this.power = power;
        this.itemType = itemType;
    
    @Override
    public String toString() {
        return itemType +": " +name+ "with "+power+" power"
    }

    }
}