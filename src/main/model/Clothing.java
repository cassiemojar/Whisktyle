package model;

import javax.swing.ImageIcon;

public abstract class Clothing {
    private String name;
    private ImageIcon img;

    public Clothing(String name, ImageIcon img) {
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
