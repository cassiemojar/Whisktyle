import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import model.Outfit;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

// Represents a test class for Person class
public class TestPerson {
    private Person cher;
    private Shirt testShirt;
    private Pants testPants;
    private Shoes testShoes;
    private Outfit testOutfit;

    @BeforeEach
    void runBefore() {
        testShirt = new Shirt("Plaid top", new ImageIcon("ui/img/test-shirt.png"));
        testPants = new Pants("Plaid skirt", new ImageIcon("ui/img/test-pants.png"));
        testShoes = new Shoes("Yellow flats");

        testOutfit = new Outfit("Default Outfit", testShirt, testPants, testShoes);

        cher = new Person("Cher", testShirt, testPants, testShoes);

    }

    @Test
    void testConstructor() {
        assertEquals("Cher", cher.getName());
        assertTrue(cher.getShirt().equals(testShirt));
        assertTrue(cher.getPants().equals(testPants));
        assertTrue(cher.getShoes().equals(testShoes));
        assertTrue(cher.getOutfit().equals(testOutfit));
    }

    @Test
    void testChangeName() {
        cher.setName("Dionne");
        assertEquals("Dionne", cher.getName());
    }

    @Test
    void testChangeShirt() {
        Shirt newShirt = new Shirt("Sheer white jacket", new ImageIcon("ui/img/test-shirt.png"));
        Outfit newOutfit = new Outfit("Date Outfit", newShirt, testPants, testShoes);

        cher.setShirt(newShirt);
        cher.getOutfit().setName("Date Outfit");

        assertTrue(cher.getShirt().equals(newShirt));
        assertFalse(cher.getOutfit().equals(testOutfit));
        assertTrue(cher.getOutfit().equals(newOutfit));
    }

    @Test
    void testChangePants() {
        Pants newPants = new Pants("Sheer white dress", new ImageIcon("ui/img/test-pants.png"));
        Outfit newOutfit = new Outfit("Date Outfit", testShirt, newPants, testShoes);

        cher.setPants(newPants);
        cher.getOutfit().setName("Date Outfit");

        assertTrue(cher.getPants().equals(newPants));
        assertFalse(cher.getOutfit().equals(testOutfit));
        assertTrue(cher.getOutfit().equals(newOutfit));
    }

    @Test
    void testChangeShoes() {
        Shoes newShoes = new Shoes("Silver heels");
        Outfit newOutfit = new Outfit("Date Outfit", testShirt, testPants, newShoes);

        cher.setShoes(newShoes);
        cher.getOutfit().setName("Date Outfit");

        assertTrue(cher.getShoes().equals(newShoes));
        assertFalse(cher.getOutfit().equals(testOutfit));
        assertTrue(cher.getOutfit().equals(newOutfit));
    }
}
