package model;

// Represents a pair of shoes
public class Shoes {
    private String name;


    // EFFECTS: Creates a pair of shoes with a name
    public Shoes(String name) {
        this.name = name;
    }


    // Setters and getters below
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
