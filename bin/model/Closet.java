package model;

import java.util.List;

// Represents a Closet that does not have a person or clothes initially.
public class Closet {
    private Person person;
    private List<Shirt> shirtsList;
    private List<Pants> pantsList;
    private List<Shoes> shoesList;
    private List<Outfit> savedOutfits;


    // EFFECTS: Creates a closet that has no person, clothing, or saved outfits.
    public Closet() {

    }

    // MODIFIES: this
    // EFFECTS: adds a shirt to the list of existing shirts
    public void addShirt(Shirt shirt) {

    }

    // MODIFIES: this
    // EFFECTS: adds a pants to the list of existing pants
    public void addPants(Pants pants) {

    }

    // MODIFIES: this
    // EFFECTS: adds a pair of shoes to the list of existing shoes
    public void addShoes(Shoes shoes) {

    }

    // MODIFIES: this
    // EFFECTS: adds an outfit to the list of existing saved outfits
    public void addOutfit(Shirt shirt, Pants pants, Shoes shoes) {

    }


    // Getters and Setters below
    public void setPerson(Person person) {

    }

    public Person getPerson() {
        return null;
    }



}
