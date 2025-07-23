import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Closet;
import model.Outfit;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;


public class TestShoes {
    private Shoes testShoes;

    @BeforeEach
    void runBefore() {
        testShoes = new Shoes("Yellow flats");
    }

    @Test
    void testConstructor() {
        assertEquals(testShoes.getName(), "Yellow flats");
    }

    @Test
    void testSetName() {
        testShoes.setName("Silver heels");
        assertEquals(testShoes.getName(), "Silver heels");
    }
}
