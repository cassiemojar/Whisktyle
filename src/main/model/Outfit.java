package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an outfit with a shirt, pants, shoes together
public class Outfit implements Writable{
    private String name;
    private Clothing shirt;
    private Clothing pants;
    private Shoes shoes;

    // EFFECTS: Creates an outfit with goven shirt, pants, shoes
    public Outfit(String name, Clothing shirt, Clothing pants, Shoes shoes) {
        this.name = name;
        this.shirt = shirt;
        this.pants = pants;
        this.shoes = shoes;
    }

    // EFFECTS: puts variables as Json Objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);

        if (shirt instanceof Writable) {
            json.put("Shirt", ((Writable) shirt).toJson());
        }
        if (pants instanceof Writable) {
            json.put("Pants", ((Writable) pants).toJson());
        }
        if (shoes != null) {
            json.put("Shoes", shoes.toJson());
        }
        return json;
    }

    

    // Setters and getters below

    public void setName(String name) {
        this.name = name;
    }

    public void setShirt(Shirt shirt) {
        this.shirt = shirt;
    }

    public void setPants(Pants pants) {
        this.pants = pants;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public String getName() {
        return this.name;
    }

    public Clothing getShirt() {
        return this.shirt;
    }

    public Clothing getPants() {
        return this.pants;
    }

    public Shoes getShoes() {
        return this.shoes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((shirt == null) ? 0 : shirt.hashCode());
        result = prime * result + ((pants == null) ? 0 : pants.hashCode());
        result = prime * result + ((shoes == null) ? 0 : shoes.hashCode());
        return result;
    }

    @SuppressWarnings("methodlength")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Outfit other = (Outfit) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (shirt == null) {
            if (other.shirt != null) {
                return false;
            }
        } else if (!shirt.equals(other.shirt)) {
            return false;
        }
        if (pants == null) {
            if (other.pants != null) {
                return false;
            }
        } else if (!pants.equals(other.pants)) {
            return false;
        }
        if (shoes == null) {
            if (other.shoes != null) {
                return false;
            }
        } else if (!shoes.equals(other.shoes)) {
            return false;
        }
        return true;
    }
}
