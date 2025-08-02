package model;

import javax.swing.ImageIcon;

// Represents a shirt to be worn by a Person
public class Shirt {
    private String name;
    private ImageIcon img;

    // EFFECTS: Creates a shirt with a name
    public Shirt(String name, ImageIcon img) {
        this.name = name;
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public ImageIcon getImg() {
        return img;
    }

    public String getName() {
        return name;
    }
}
