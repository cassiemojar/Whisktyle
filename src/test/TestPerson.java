import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Closet;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

public class TestPerson {
    private Person cher;
    private Closet testCloset;

    @BeforeEach
    void runBefore() {
        Shirt shirt = new Shirt("Plaid top");
        Pants pants = new Pants("Plaid skirt");
        Shoes shoes = new Shoes("Yellow flats");
        cher = new Person("Cher", shirt, pants, shoes);
        testCloset = new Closet();
        
    }

    @Test
    void testConstructor() {
        assertEquals("Cher", cher.getName());
    }
}
