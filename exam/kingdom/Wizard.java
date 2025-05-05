package kingdom;

public class Wizard extends Adventurer {
    private MagicType skill;

    public enum MagicType {
        FIRE, WATER, EARTH
    }

    public Wizard(String name, MagicType skill) {
        this.name = name;
        this.skill = skill;
    }

    public float calculatePower() {
        float power = item.calculatePower();
        
        if (skill ==  MagicType.WATER) {
            return power;
        }
        else if (skill == MagicType.EARTH) {
            return power * 2;
        }

        else if (skill == MagicType.FIRE) {
            return power * 3;
        }
    }

    public MagicType getSkill() {
        return skill;
    }

}
