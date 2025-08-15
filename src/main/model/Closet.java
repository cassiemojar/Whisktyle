package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a Closet that does not have a person or clothes initially.
public class Closet implements Writable {
    private Person person;
    private List<Clothing> shirtsList;
    private List<Clothing> pantsList;
    private List<Clothing> shoesList;
    private Map<String, List<Outfit>> savedOutfits;

    private int shirtIndex;
    private int pantsIndex;

    private Outfit selectedOutfit;
    private String selectedOutfitCategory;

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

    // EFFECTS: puts variables as Json Objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        if (person != null) {
            json.put("Person", person.toJson());
        }

        json.put("Saved Shirts", listToJson(shirtsList));
        json.put("Saved Pants", listToJson(pantsList));
        json.put("Saved Shoes", listToJson(shoesList));
        json.put("Saved Outfits", savedOutfitsToJson());
        return json;
    }

    // EFFECTS: puts saved outfits and their categories as JSON objects
    private JSONObject savedOutfitsToJson() {
        JSONObject json = new JSONObject();
        for (String category : savedOutfits.keySet()) {
            json.put(category, listToJson(savedOutfits.get(category)));
        }
        return json;
    }

    // EFFECTS: converts lists into JSONArrays
    private JSONArray listToJson(List<? extends Writable> list) {
        JSONArray array = new JSONArray();

        for (Writable w : list) {
            array.put(w.toJson());
        }

        return array;
    }

    // Getters and Setters below
    public void setPerson(Person person) {
        this.person = person;
    }

    public void setShirtIndex(int index) {
        shirtIndex = index;
    }

    public void setPantsIndex(int index) {
        pantsIndex = index;
    }

    public Person getPerson() throws NoPersonException {
        if (this.person != null) {
            return this.person;
        }
        throw new NoPersonException();
    }

    public void setSelectedOutfit(Outfit outfit) {
        selectedOutfit = outfit;
    }

    public void setSelectedOutfitCategory(String category) {
        selectedOutfitCategory = category;
    }

    public List<Clothing> getShirts() {
        return this.shirtsList;
    }

    public List<Clothing> getPants() {
        return this.pantsList;
    }

    public List<Clothing> getShoes() {
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

    public int getShirtIndex() {
        return shirtIndex;
    }

    public int getPantsIndex() {
        return pantsIndex;
    }

    public Outfit getSelectedOutfit() {
        return selectedOutfit;
    }

    public String setSelectedOutfitCategory() {
        return selectedOutfitCategory;
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
