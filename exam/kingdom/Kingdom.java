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
        List<Adventurer>  assignedAdventureres = new ArrayList<>();

        for (Adventurer adventurer : fellowship) { 
            // skip adventurers who already have an item
            if (adventurer.hasItem()) {
                continue;
            }

            // determine item type based on adventurer class
            ItemType targetType = null;

            if (adventurer instanceof Hobbit) {
                targetType = ItemType.HANDHELD;
            }
            else if (adventurer instanceof Elf) {
                targetType = ItemType.RANGED;
            }
            else if (adventurer instanceof Wizard) {
                targetType = ItemType.MAGICAL;
            }

            if (targetType == null) {
                continue;
            }

            // assign the first compatible item in storage

            for (Item item : storage) {
                if (item.getType() == targetType) {
                    adventurer.assignItem(item);
                    storage.remove(item);
                    assignedAdventureres.add(adventurer);
                    break; // break statement so only added is item assigned targetItem
                }
            }

            return assignedAdventureres;
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

