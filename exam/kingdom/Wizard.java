package kingdom;

public class Wizard extends Adventurer {
    private MagicType skill;

    public enum MagicType {
        FIRE, WATER, EARTH
    }

    public Wizard(String name, MagicType skill) {
        super(name, null);
        this.skill = skill;
    }

    public float calculatePower() {

        Item item = getItem();
        float power = 0;
        if (item != null) {
            power = item.power();
        }
        
        if (skill ==  MagicType.WATER) {
            return power;
        }
        else if (skill == MagicType.EARTH) {
            return power * 2;
        }

        else if (skill == MagicType.FIRE) {
            return power * 3;
        }

        return power;
    }

    public MagicType getSkill() {
        return skill;
    }

}
