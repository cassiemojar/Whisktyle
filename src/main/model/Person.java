package model;

import javax.swing.ImageIcon;

import org.json.JSONObject;

import persistence.Writable;

// Represents a Person that is initially set to default shirt, pants, and shoes
public class Person implements Writable {
    private String name;
    private Outfit outfit;
    private ImageIcon img;

    // EFFECTS: creates a person with a default shirt, pants, shoes
    public Person(String name, ImageIcon img, Clothing shirt, Clothing pants, Shoes shoes) {
        this.name = name;
        this.img = img;
        outfit = new Outfit("Default Outfit", shirt, pants, shoes);
    }

    // Setters and getters below
    public void setName(String name) {
        this.name = name;
    }

    public void setShirt(Shirt shirt) {
        outfit.setShirt(shirt);
    }

    public void setPants(Pants pants) {
        outfit.setPants(pants);
    }

    public void setShoes(Shoes shoes) {
        outfit.setShoes(shoes);
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public String getName() {
        return this.name;
    }

    public Clothing getShirt() {
        return outfit.getShirt();
    }

    public Clothing getPants() {
        return outfit.getPants();
    }

    public Shoes getShoes() {
        return outfit.getShoes();
    }

    public Outfit getOutfit() {
        return outfit;
    }

    public ImageIcon getImg() {
        return img;
    }

    // EFFECTS: puts variables as Json Objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Image Path", img.getDescription());

        json.put("Shirt", outfit.getShirt().toJson());
        json.put("Pants", outfit.getPants().toJson());
        json.put("Shoes", outfit.getShoes().toJson());

        return json;
    }

}
