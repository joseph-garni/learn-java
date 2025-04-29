package kingdom;

public class Wizard extends Adventurer {
    private MagicType skill;

    public Wizard(String name, MagicType skill) {
        this.name = name;
        this.skill = skill;
    }

    private enum MagicType {
        FIRE, WATER, EARTH
    }

    public float calculatePower() {
        float power = item.calculatePower();
        
        if (skill ==  "WATER") {
            return power;
        }
        elif (skill == "EARTH") {
            return power * 2;
        }

        elif (skill == "FIRE") {
            return power * 3;
        }
    }

    public MagicType getSkill() {
        return skill;
    }

}
