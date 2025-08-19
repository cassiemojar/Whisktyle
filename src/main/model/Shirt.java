package model;

import javax.swing.ImageIcon;

// Represents a shirt to be worn by a Person
public class Shirt extends Clothing{

    // EFFECTS: Creates a shirt with a name
    public Shirt(String name, String imgPath, ImageIcon img) {
        super(name, imgPath, img);
    }
}
