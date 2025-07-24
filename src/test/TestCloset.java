import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Closet;
import model.NoPersonException;
import model.Outfit;
import model.OutfitException;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

public class TestCloset {
    private Closet testCloset;

    private Outfit testOutfit1;
    private Shirt testShirt1;
    private Pants testPants1;
    private Shoes testShoes1;

    private Outfit testOutfit2;
    private Shirt testShirt2;
    private Pants testPants2;
    private Shoes testShoes2;

    private Outfit testOutfit3;
    private Shirt testShirt3;
    private Pants testPants3;
    private Shoes testShoes3;

    @BeforeEach
    void runBefore() {
        testCloset = new Closet();

        testShirt1 = new Shirt("Plaid top");
        testPants1 = new Pants("Plaid skirt");
        testShoes1 = new Shoes("Yellow flats");

        testOutfit1 = new Outfit("School outfit", testShirt1, testPants1, testShoes1);

        testShirt2 = new Shirt("Red top");
        testPants2 = new Pants("Red skirt");
        testShoes2 = new Shoes("Red flats");

        testOutfit2 = new Outfit("Second school outfit", testShirt2, testPants2, testShoes2);

        testShirt3 = new Shirt("Sheer white jacket");
        testPants3 = new Pants("Sheer white dress");
        testShoes3 = new Shoes("Silver heels");

        testOutfit3 = new Outfit("Date outfit", testShirt3, testPants3, testShoes3);
    }

    @Test
    void testConstructor() {
        try {
            testCloset.getPerson();
            fail();
        } catch (NoPersonException a) {
            assertTrue(testCloset.getShirts().isEmpty());
            assertTrue(testCloset.getPants().isEmpty());
            assertTrue(testCloset.getShoes().isEmpty());
            assertTrue(testCloset.getOutfitCategory("Any").isEmpty());

            try {
                testCloset.getAnOutfit("Any", "School Outfit");
                fail();
            } catch (OutfitException b) {
                // Runs
            }

        }
    }

    @Test
    void testSetPerson() {
        Person testPerson2 = new Person("Dionne", testShirt3, testPants3, testShoes3);
        testCloset.setPerson(testPerson2);
        try {
            assertTrue(testCloset.getPerson().equals(testPerson2));
        } catch (NoPersonException a) {
            fail();
        }

    }

    @Test
    void testAddShirt() {
        testCloset.addShirt(testShirt1);
        assertTrue(testCloset.getShirts().contains(testShirt1));
        assertEquals(testCloset.getShirts().size(), 1);
        assertTrue(testCloset.getPants().isEmpty());
        assertTrue(testCloset.getShoes().isEmpty());

        testCloset.addShirt(testShirt2);
        assertTrue(testCloset.getShirts().contains(testShirt2));
        assertTrue(testCloset.getShirts().contains(testShirt1));
        assertEquals(testCloset.getShirts().size(), 2);
        assertTrue(testCloset.getPants().isEmpty());
        assertTrue(testCloset.getShoes().isEmpty());
    }

    @Test
    void testAddPants() {
        testCloset.addPants(testPants1);
        assertTrue(testCloset.getPants().contains(testPants1));
        assertEquals(testCloset.getPants().size(), 1);
        assertTrue(testCloset.getShirts().isEmpty());
        assertTrue(testCloset.getShoes().isEmpty());

        testCloset.addPants(testPants2);
        assertTrue(testCloset.getPants().contains(testPants2));
        assertTrue(testCloset.getPants().contains(testPants1));
        assertEquals(testCloset.getPants().size(), 2);
        assertTrue(testCloset.getShirts().isEmpty());
        assertTrue(testCloset.getShoes().isEmpty());
    }

    @Test
    void testAddShoes() {
        testCloset.addShoes(testShoes1);
        assertTrue(testCloset.getShoes().contains(testShoes1));
        assertEquals(testCloset.getShoes().size(), 1);
        assertTrue(testCloset.getPants().isEmpty());
        assertTrue(testCloset.getShirts().isEmpty());

        testCloset.addShoes(testShoes2);
        assertTrue(testCloset.getShoes().contains(testShoes2));
        assertTrue(testCloset.getShoes().contains(testShoes1));
        assertEquals(testCloset.getShoes().size(), 2);
        assertTrue(testCloset.getPants().isEmpty());
        assertTrue(testCloset.getShirts().isEmpty());
    }

    @Test
    void testAddSavedOutfit() {
        testCloset.addOutfit("School", testOutfit1);

        assertTrue(testCloset.getSavedOutfits().containsKey("School"));
        assertTrue(testCloset.getOutfitCategory("School").contains(testOutfit1));
        assertEquals(testCloset.getOutfitCategory("School").size(), 1);

        try {
            assertTrue(testCloset.getAnOutfit("School", "School outfit").equals(testOutfit1));

            testCloset.addOutfit("School", testOutfit2);
            assertTrue(testCloset.getOutfitCategory("School").contains(testOutfit2));
            assertEquals(testCloset.getOutfitCategory("School").size(), 2);

            try {
                assertTrue(testCloset.getAnOutfit("School", "Second school outfit").equals(testOutfit2));

                testCloset.addOutfit("Party", testOutfit3);
                assertTrue(testCloset.getSavedOutfits().containsKey("Party"));

                assertTrue(testCloset.getOutfitCategory("School").contains(testOutfit1));
                assertTrue(testCloset.getOutfitCategory("School").contains(testOutfit2));
                assertEquals(testCloset.getOutfitCategory("School").size(), 2);

                assertTrue(testCloset.getOutfitCategory("Party").contains(testOutfit3));
                assertEquals(testCloset.getOutfitCategory("Party").size(), 1);

                try {
                    assertTrue(testCloset.getAnOutfit("Party", "Date outfit").equals(testOutfit3));

                } catch (OutfitException c) {
                    fail();
                }

            } catch (OutfitException b) {
                fail();
            }

        } catch (OutfitException a) {
            fail();
        }
    }
}
