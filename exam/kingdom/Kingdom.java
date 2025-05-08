package kingdom;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

import kingdom.Item.ItemType;

import java.util.Queue;
import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Kingdom {
    private List<Adventurer> fellowship = new ArrayList<>();
    private List<Item> storage = new ArrayList<>();

    public Kingdom(List<Adventurer> fellowship, List<Item> storage) {
        this.fellowship = fellowship;
        this.storage = storage;
    }
    public static void main(String[] args) {
        // Create a new Kingdom with empty lists
        Kingdom kingdom = new Kingdom(new ArrayList<>(), new ArrayList<>());
        
        // Add some adventurers
        kingdom.addAdventurer(new Hobbit("Frodo", 5));
        kingdom.addAdventurer(new Elf("Legolas", 0.95f));
        kingdom.addAdventurer(new Wizard("Gandalf", Wizard.MagicType.FIRE));
        
        // Test your methods
        System.out.println("Fellowship size: " + kingdom.getFellowship().size());
        
        // Test feeding order
        Queue<Adventurer> feedingOrder = kingdom.feedFellowship();
        System.out.println("Feeding order:");
        for (Adventurer adventurer : feedingOrder) {
            System.out.println(adventurer);
        }
    }
    
    public void addAdventurer(Adventurer adventurer) {
        fellowship.add(adventurer);
    }

    public void addItem(Item item) {
        storage.add(item);
    }

    private boolean isGardener(Adventurer adventurer) {
        if (adventurer instanceof Wizard wizard) {
            Wizard.MagicType skill = wizard.getSkill();
            return skill == Wizard.MagicType.EARTH || skill == Wizard.MagicType.WATER;
        }
        return false;
    }

    public Stream<Adventurer> getGardeningWizards(int minPower) {
        return fellowship.stream()
                .filter(this::isGardener)
                .filter(adventurer -> adventurer.calculatePower() >= minPower);
    }

    public List<Adventurer> assignItems() {
        List<Adventurer>  assignedAdventurers = new ArrayList<>();

        for (Adventurer adventurer : fellowship) { 
            // skip adventurers who already have an item
            if (adventurer.getItem() != null) {
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

            // need this here because if not we will get an error
            if (targetType == null) {
                continue;
            }

            // assign the first compatible item in storage

            for (Item item : storage) {
                if (item.itemType() == targetType) {
                    adventurer.setItem(item);
                    storage.remove(item);
                    assignedAdventurers.add(adventurer);
                    break; // break statement so only added is item assigned targetItem
                }
            }
        }

        return assignedAdventurers;
    }        

    public List<Adventurer> getFellowship() {
        return fellowship;
    }

    public Queue<Adventurer> feedFellowship() {
        Queue<Adventurer> feedingOrder = new LinkedList<>();

        // first round - hobbits
        for (Adventurer adventurer : fellowship) {
            if (adventurer instanceof Hobbit) {
                feedingOrder.add(adventurer);
            }
        }

        // second round - elfs
        for (Adventurer adventurer : fellowship) {
            if (adventurer instanceof Elf) {
                feedingOrder.add(adventurer);
            }
        }
        
        // third round - wizards
        for (Adventurer adventurer : fellowship) {
            if (adventurer instanceof Wizard) {
                feedingOrder.add(adventurer);
            }
        }

        // fourth round - hobbits eat again
        for (Adventurer adventurer : fellowship) {
            if (adventurer instanceof Hobbit) {
                feedingOrder.add(adventurer);
            }
        }

        return feedingOrder;
        
    }

    public List<Adventurer> importAdventurersFromCSV(String filePath) throws IOException {
        List<Adventurer> adventurers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            // skip the header line
            reader.readLine();

            String line;

            while ((line = reader.readLine()) !=null) {
                String[] fields = line.split(",");

                if (fields.length != 3) {
                    throw new IllegalArgumentException("Invalid number of fields in line: " + line);
                }       

                String name = fields[0].trim();
                String adventurerType = fields[1].trim();
                String extraData = fields[2].trim();
                
                try {
                    switch (adventurerType) {
                        case "Hobbit":
                                int strength = Integer.parseInt(extraData);
                                adventurers.add(new Hobbit(name, strength));
                                break;
                        case "Elf":
                                float accuracy = Float.parseFloat(extraData);
                                adventurers.add(new Elf(name, accuracy));
                                break;
                        case "Wizard":
                                Wizard.MagicType skill = Wizard.MagicType.valueOf(extraData);
                                adventurers.add(new Wizard(name, skill));
                                break;
                        default:
                            throw new IllegalArgumentException("Unknown adventurer type: " + adventurerType);
                        }
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid data for adventurer: "+ line, e);
                }
            }
        }

        return adventurers;
                  
    }

    public void removeItem(Item item) {
        storage.remove(item);
    }

    public Adventurer removeAdventurer(int index) {
        return fellowship.remove(index);
    }

}

