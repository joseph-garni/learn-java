package kingdom;

public class Elf extends Adventurer {
    private float accuracy;

    public Elf(String name, float accuracy) {
        super(name, null);
        this.accuracy = accuracy;
    }

    public float calculatePower() {
        float power = getItem() != null ? getItem().power() : 0;
        return power * accuracy;
    }
}
