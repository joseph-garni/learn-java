package kingdom;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

import java.util.Queue;
import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    private boolean isGardener(Adventurer adventurer) {
        if (adventurer instanceof Wizard) {
            Wizard wizard = (Wizard) adventurer;
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

    public List<Adventurer> importAdventurersFromCSV(String filePath) throws java.io.IOException {
        List<Adventurer> adventurers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            // skip the header line
            reader.readLine();

            String line;

            while ((line = reader.readLine()) !=null) {
                String[] fields = line.split(",");

                if (fields.length != 3) {
                    throw new java.lang.IllegalArgumentException("Each line must contain exactly three fields");
                }

                String name = fields[0];
                String adventurerType = fields[1];
                String extraData = fields[2];
                
                try {
                    switch (adventurerType) {
                        case "Hobbit":
                            try {
                                int strength = Integer.parseInt(extraData);
                                adventurers.add(new Hobbit(name, strength));
                            }  
                            
                            catch (NumberFormatException e) {
                                throw new java.lang.IllegalArgumentException("Invalid Strength Value for Hobbit: " + extraData);
                            }

                            break;
                        
                        case "Elf":
                            try {
                                float accuracy = Float.parseFloat(extraData);
                                adventurers.add(new Elf(name, accuracy));
                            }

                            catch (NumberFormatException e) {
                                throw new java.lang.IllegalArgumentException("Invalid Accuracy Value for Elf: " + extraData);
                            }

                            break;

                        case "Wizard":
                            try {
                                Wizard.MagicType skill = Wizard.MagicType.valueOf(extraData);
                                adventurers.add(new Wizard(name, skill));
                            }

                            catch (IllegalArgumentException e) {
                                throw new java.lang.IllegalArgumentException("Invalid MagicType for Wizard: " + extraData);
                            }
                            break;

                        default:
                            throw new java.lang.IllegalArgumentException("Unknown adventurer type: " + adventurerType);
                    }

                    catch (Exception e) {

                        // rethrow as IllegalArgumentException
                        
                        if (e instanceof java.lang.IllegalArgumentException) {
                            throw e;
                        }
                        else {
                            throw new java.lang.IllegalArgumentException("Error creating adventurer: " + e.getMessage());
                        }
                    }
                }
            }

            catch (java.io.IOException e) {
                // claim all file I/O errors as required by the instructions

                throw e;
            }

            return adventurers;
        }
    }

    public void removeItem(Item item) {
        storage.remove(item);
    }

    public Adventurer removeAdventurer(int index) {
        return fellowship.remove(index);
    }

}

