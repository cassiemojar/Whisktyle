package model;

import javax.swing.ImageIcon;

// Represents a pair of Pants
public class Pants {
    private String name;
    private ImageIcon img;


    // EFFECTS: creates a pair of pants with a name
    public Pants(String name, ImageIcon img) {
        this.name = name;
        this.img = img;
    }

    // Getters and setters below
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public ImageIcon getImg() {
        return img;
    }


}
