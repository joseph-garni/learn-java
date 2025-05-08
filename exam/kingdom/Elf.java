package kingdom;

public class Elf extends Adventurer {
    private float accuracy;

    public Elf(String name, float accuracy) {
        super(name, null);
        
        // enforce accuracy range between 0 and 100

        if (accuracy < 0) {
            this.accuracy = 0;
        }
        else if (this.accuracy > 100) {
            this.accuracy = 100;
        }
        else {
            this.accuracy = accuracy;
        }
    }

    public Elf(float accuracy) {
        this(null, accuracy);
    }

    public float calculatePower() {
        float power = getItem() != null ? getItem().power() : 0;
        return (power * accuracy) / 100.0f;
    }
}
