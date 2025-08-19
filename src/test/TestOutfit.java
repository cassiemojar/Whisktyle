import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import model.Outfit;
import model.Pants;
import model.Shirt;
import model.Shoes;

public class TestOutfit {
    private Outfit testOutfit;
    private Shirt testShirt;
    private Pants testPants;
    private Shoes testShoes;

    @BeforeEach
    void runBefore() {
        testShirt = new Shirt("Plaid top", "ui/img/test-shirt.png", new ImageIcon("ui/img/test-shirt.png"));
        testPants = new Pants("Plaid skirt", "ui/img/test-pants.png", new ImageIcon("ui/img/test-pants.png"));
        testShoes = new Shoes("Yellow flats");

        testOutfit = new Outfit("School Outfit", testShirt, testPants, testShoes);
    }

    @Test
    void testConstructor() {
        assertEquals(testOutfit.getName(), "School Outfit");
        assertTrue(testOutfit.getShirt().equals(testShirt));
        assertTrue(testOutfit.getPants().equals(testPants));
        assertTrue(testOutfit.getShoes().equals(testShoes));
    }

    @Test
    void testChangeName() {
        testOutfit.setName("Date Outfit");
        assertEquals("Date Outfit", testOutfit.getName());
    }

    @Test
    void testChangeShirt() {
        Shirt newShirt = new Shirt("Sheer white jacket","ui/img/test-shirt.png",  new ImageIcon("ui/img/test-shirt.png"));

        testOutfit.setShirt(newShirt);
        assertTrue(testOutfit.getShirt().equals(newShirt));
    }

    @Test
    void testChangePants() {
        Pants newPants = new Pants("Sheer white dress", "ui/img/test-pants.png", new ImageIcon("ui/img/test-pants.png"));

        testOutfit.setPants(newPants);
        assertTrue(testOutfit.getPants().equals(newPants));
    }

    @Test
    void testChangeShoes() {
        Shoes newShoes = new Shoes("Silver heels");

        testOutfit.setShoes(newShoes);
        assertTrue(testOutfit.getShoes().equals(newShoes));
    }

    @Test
    void testHashCodeAndEquals() {
        Outfit anotherOutfit = new Outfit("School Outfit", testShirt, testPants, testShoes);
        Shirt newShirt = new Shirt("Sheer white jacket", "ui/img/test-shirt.png", new ImageIcon("ui/img/test-shirt.png"));
        Pants newPants = new Pants("Sheer white dress","ui/img/test-pants.png",  new ImageIcon("ui/img/test-pants.png"));
        Shoes newShoes = new Shoes("Silver heels");

        assertTrue(testOutfit.equals(anotherOutfit));

        assertFalse(testOutfit.getShirt().equals(newShirt));
        assertFalse(testOutfit.getPants().equals(newPants));
        assertFalse(testOutfit.getShoes().equals(newShoes));

    }
}
