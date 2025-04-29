package kingdom;

import java.util.List;
import java.util.ArrayList;

public class Kingdom {
    private Adventurer adventurer;
    private Item item;
    private List<Adventurer> fellowship = new ArrayList<>();
    private List<Item> storage = new ArrayList<>();

    public Kingdom(List<Adventurer> fellowship, List<Item> storage) {
        this.fellowship = fellowship;
        this.storage = storage;
    }
    
    public void addAdventurer(Adventurer adventurer) {
        fellowship.add(adventurer);
    }

    public void addItem(Item item) {
        storage.add(item);
    }

    public List<Adventurer> assignItems() {

    }

    public List<Adventurer> getFellowship() {

    }

    public List<Adventurer> importAdventurersFromCSV(String) {

    }

    public void removeItem(Item item) {
        storage.remove(item);
    }

    public Adventurer removeAdventurer(int index) {

        return fellowship.remove(index);
    }

}

