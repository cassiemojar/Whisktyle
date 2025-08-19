package model;

import javax.swing.ImageIcon;

import org.json.JSONObject;

import persistence.Writable;

public abstract class Clothing implements Writable {
    private String name;
    private ImageIcon img;
    private String imgPath;

    public Clothing(String name, String imgPath, ImageIcon img) {
        this.name = name;
        this.imgPath = imgPath;
        this.img = img;
    }

    // EFFECTS: converts variables into JSON objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        if (img != null) {
            json.put("Image Path", imgPath);
        }
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

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

}
