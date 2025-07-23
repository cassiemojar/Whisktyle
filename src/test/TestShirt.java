import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Closet;
import model.Outfit;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

public class TestShirt {
    private Shirt testShirt;

    @BeforeEach
    void runBefore() {
        testShirt = new Shirt("Plaid yellow top");
    }

    @Test
    void testConstructor() {
        assertEquals(testShirt.getName(), "Plaid yellow top");
    }

    @Test
    void testSetName() {
        testShirt.setName("Sheer white jacket");
        assertEquals(testShirt.getName(), "Sheer white jacket");
    }
}
