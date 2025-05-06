package kingdom;

public class Hobbit extends Adventurer {
    private int strength;

    public Hobbit(String name, int strength) {
        super(name, null);
        this.strength = strength;
    }

    @Override
    public float calculatePower() {
        Item item = getItem();
        
        if (item != null) {
            float power = item.power();
            return power * strength;
        }
        return 0;
    }

}
