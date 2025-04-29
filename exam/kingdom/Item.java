package kingdom;

public abstract record Item() {
    private String name;
    private float power;
    private ItemType itemType;

    private enum ItemType {
        HANDHELD, RANGED, MAGICAL
    }

    public Item(String name, float power, ItemType itemType) {
        this.name = name;
        this.power = power;
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return itemType +": " +name+ "with "+power+" power"
    }

}