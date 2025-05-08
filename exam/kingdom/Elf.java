package kingdom;

public class Elf extends Adventurer {
    private float accuracy;

    public Elf(String name, float accuracy) {
        super(name, null);
        
        // enforce accuracy range between 0 and 100

        if (accuracy < 0) {
            accuracy = 0;
        }
        else if (accuracy > 100) {
            accuracy = 100;
        }

        this.accuracy = accuracy;
    }

    /* commenting this out, it is not needed -> all of our elves have names (I think)
    /public Elf(float accuracy) {
       this(null, accuracy);
    }
    */

    public float calculatePower() {
        Item item = getItem();
        float power = 0;

        if (item!= null) {
            power = item.power();
        }

        return (power * accuracy) / 100.0f;
    }
}   
