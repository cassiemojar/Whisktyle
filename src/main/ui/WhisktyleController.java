package ui;

import model.Closet;

// Represents a controller class that maintains same instance of closet across classes using singleton pattern
public class WhisktyleController {
    private static WhisktyleController singleton;
    private Closet closet;

    // EFFECTS: initializes a new closet
    private WhisktyleController() {
        closet = new Closet();
    }

    // MODIFIE: this
    // EFFECTS: creates instance of WhisktyleController, returns it
    public static WhisktyleController getInstance() {
        if (singleton == null) {
            singleton = new WhisktyleController();
        }
        return singleton;
    }

    // Getters and setters below
    public Closet getCloset() {
        return closet;
    }

}
