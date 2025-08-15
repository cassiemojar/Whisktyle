package model;

import javax.swing.ImageIcon;

import org.json.JSONObject;

import persistence.Writable;

public abstract class Clothing implements Writable {
    private String name;
    private ImageIcon img;

    public Clothing(String name, ImageIcon img) {
        this.name = name;
        this.img = img;
    }

    // EFFECTS: converts variables into JSON objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Image Path", img != null ? img.getDescription() : null);
        return json;
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
