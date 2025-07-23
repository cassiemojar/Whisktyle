package model;

// Represents a pair of Pants
public class Pants {
    private String name;


    // EFFECTS: creates a pair of pants with a name
    public Pants(String name) {
        this.name = name;
    }




    // Getters and setters below
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
