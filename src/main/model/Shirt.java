package model;

// Represents a shirt to be worn by a Person
public class Shirt {
    private String name;

    // EFFECTS: Creates a shirt with a name
    public Shirt(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
