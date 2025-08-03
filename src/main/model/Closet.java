package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

// Represents a Closet that does not have a person or clothes initially.
public class Closet {
    private Person person;
    private List<Shirt> shirtsList;
    private List<Pants> pantsList;
    private List<Shoes> shoesList;
    private Map<String, List<Outfit>> savedOutfits;

    // EFFECTS: Creates a closet that has no person, clothing, or saved outfits.
    public Closet() {
        shirtsList = new ArrayList<>();
        pantsList = new ArrayList<>();
        shoesList = new ArrayList<>();
        savedOutfits = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a shirt to the list of existing shirts
    public void addShirt(Shirt shirt) {
        shirtsList.add(shirt);
    }

    // MODIFIES: this
    // EFFECTS: adds a pants to the list of existing pants
    public void addPants(Pants pants) {
        pantsList.add(pants);
    }

    // MODIFIES: this
    // EFFECTS: adds a pair of shoes to the list of existing shoes
    public void addShoes(Shoes shoes) {
        shoesList.add(shoes);
    }

    // MODIFIES: this
    // EFFECTS: adds an outfit to the list of existing saved outfits
    public void addOutfit(String name, Outfit outfit) {
        if (!savedOutfits.containsKey(name)) {
            savedOutfits.put(name, new ArrayList<>());
        }

        savedOutfits.get(name).add(outfit);
    }

    // Getters and Setters below
    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() throws NoPersonException {
        if (this.person != null) {
            return this.person;
        }
        throw new NoPersonException();
    }

    public List<Shirt> getShirts() {
        return this.shirtsList;
    }

    public List<Pants> getPants() {
        return this.pantsList;
    }

    public List<Shoes> getShoes() {
        return this.shoesList;
    }

    public Map<String, List<Outfit>> getSavedOutfits() {
        return this.savedOutfits;

    }

    public List<Outfit> getOutfitCategory(String name) {
        if (savedOutfits.get(name) != null) {
            return savedOutfits.get(name);
        }
        return new ArrayList<>();
    }

    // EFFECTS: Gets a specific outfit in an outfit category. Throws OutfitException
    // if not found.
    public Outfit getAnOutfit(String categoryName, String outfitName) throws OutfitException {
        List<Outfit> outfits = getOutfitCategory(categoryName);

        for (Outfit o : outfits) {
            if (o.getName().equals(outfitName)) {
                return o;
            }
        }

        throw new OutfitException();
    }
}
