package model;

// Represents an outfit with a shirt, pants, shoes together
public class Outfit {
    private Shirt shirt;
    private Pants pants;
    private Shoes shoes;

    // EFFECTS: Creates an outfit with goven shirt, pants, shoes
    public Outfit(Shirt shirt, Pants pants, Shoes shoes) {
        this.shirt = shirt;
        this.pants = pants;
        this.shoes = shoes;
    }

    // Setters and getters below
    public void setShirt(Shirt shirt) {
        this.shirt = shirt;
    }

    public void setPants(Pants pants) {
        this.pants = pants;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public Shirt getShirt() {
        return this.shirt;
    }

    public Pants getPants() {
        return this.pants;
    }

    public Shoes getShoes() {
        return this.shoes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shirt == null) ? 0 : shirt.hashCode());
        result = prime * result + ((pants == null) ? 0 : pants.hashCode());
        result = prime * result + ((shoes == null) ? 0 : shoes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Outfit other = (Outfit) obj;
        if (shirt == null) {
            if (other.shirt != null)
                return false;
        } else if (!shirt.equals(other.shirt))
            return false;
        if (pants == null) {
            if (other.pants != null)
                return false;
        } else if (!pants.equals(other.pants))
            return false;
        if (shoes == null) {
            if (other.shoes != null)
                return false;
        } else if (!shoes.equals(other.shoes))
            return false;
        return true;
    }
}
