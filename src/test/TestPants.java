import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Closet;
import model.Outfit;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

public class TestPants {
    private Pants testPants;

    @BeforeEach
    void runBefore() {
        testPants = new Pants("Plaid yellow skirt");
    }


    @Test
    void testConstructor() {
        assertEquals(testPants.getName(), "Plaid yellow skirt");
    }

    @Test
    void testSetName() {
        testPants.setName("Sheer white dress");
        assertEquals(testPants.getName(), "Sheer white dress");
    }
}
