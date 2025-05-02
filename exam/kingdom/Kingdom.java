package kingdom;

import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;

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
        List<Adventurer>  assignedAdventurers = new ArrayList<>();

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
                    assignedAdventurers.add(adventurer);
                    break; // break statement so only added is item assigned targetItem
                }
            }
            return assignedAdventurers;
        }

    public List<Adventurer> getFellowship() {

    }

    public List<Adventurer> importAdventurersFromCSV(String filePath) throws IOException {
        List<Adventurer> adventurersCSVList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // skip header line
            String line = reader.readLine();

            // process each data line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                String name = fields[0];
                String AdventurerTypeStr = fields[1];
                String Extra = fields[2];

                try {
                    switch (AdventurerTypeStr) {
                        case "Hobbit":
                            // validate Extra is correct value for Hobbit

                            try {
                                int stength = Integer.parseInt(Extra);
                                adventurersCSVList.add(new Hobbit(name, strength));
                            } catch (NumberFormatException e)

                    }

                }


            }
        }
    








    }

    public void removeItem(Item item) {
        storage.remove(item);
    }

    public Adventurer removeAdventurer(int index) {

        return fellowship.remove(index);
    }

}

