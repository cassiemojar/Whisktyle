import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Shirt;

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
