package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.Closet;
import model.Clothing;
import model.NoPersonException;
import model.Outfit;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Closet closet = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCloset() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCloset.json");
        try {
            Closet closet = reader.read();

            try {
                closet.getPerson();
                fail();
            } catch (NoPersonException e) {
                assertEquals(0, closet.getShirts().size());
                assertEquals(0, closet.getPants().size());
                assertEquals(0, closet.getShoes().size());

                assertTrue(closet.getSavedOutfits().isEmpty());
            }

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderCloset() {
        JsonReader reader = new JsonReader("./data/testReaderCloset.json");
        try {
            Closet closet = reader.read();

            try {
                assertEquals("Cher", closet.getPerson().getName());
                assertEquals("person.png", closet.getPerson().getImg().toString());

                assertEquals("Sheer white jacket", closet.getPerson().getShirt().getName());
                assertEquals("jacket.png", closet.getPerson().getShirt().getImg().toString());

                assertEquals("Sheer white dress", closet.getPerson().getPants().getName());
                assertEquals("dress.png", closet.getPerson().getPants().getImg().toString());

                assertEquals(null, closet.getPerson().getShoes()); // haven't implemented shoes yet. Maybe in the
                                                                   // future!

                Clothing shirtOne = closet.getShirts().get(0);
                Clothing shirtTwo = closet.getShirts().get(1);
                assertEquals("Sheer white jacket", shirtOne.getName());
                assertEquals("jacket.png", shirtOne.getImg().toString());
                assertEquals("Plaid yellow jacket", shirtTwo.getName());
                assertEquals("yellow-jacket.png", shirtTwo.getImg().toString());

                Clothing pantsOne = closet.getPants().get(0);
                Clothing pantsTwo = closet.getPants().get(1);
                assertEquals("Sheer white dress", pantsOne.getName());
                assertEquals("dress.png", pantsOne.getImg().toString());
                assertEquals("Plaid yellow skirt", pantsTwo.getName());
                assertEquals("skirt.png", pantsTwo.getImg().toString());

                assertTrue(closet.getShoes().isEmpty());

                Map<String, List<Outfit>> savedOutfits = closet.getSavedOutfits();
                assertTrue(savedOutfits.keySet().contains("School"));
                assertEquals(savedOutfits.get("School").get(0).getName(), "As If!");
                assertEquals(savedOutfits.get("School").get(0).getShirt().getName(), "Plaid yellow jacket");
                assertEquals(savedOutfits.get("School").get(0).getShirt().getImg().toString(), "yellow-jacket.png");
                assertEquals(savedOutfits.get("School").get(0).getPants().getName(), "Plaid yellow skirt");
                assertEquals(savedOutfits.get("School").get(0).getPants().getImg().toString(), "skirt.png");
                assertEquals(savedOutfits.get("School").get(0).getShoes().getName(), "");

                // assertEquals(closet.getSavedOutfits().get("School").get(0).getShirt(), closet.getShirts().get(0));


                assertTrue(closet.getSavedOutfits().keySet().contains("First Date"));
                assertEquals(savedOutfits.get("First Date").get(0).getName(), "Calvin Klein");
                assertEquals(savedOutfits.get("First Date").get(0).getShirt().getName(), "Sheer white jacket");
                assertEquals(savedOutfits.get("First Date").get(0).getShirt().getImg().toString(), "jacket.png");
                assertEquals(savedOutfits.get("First Date").get(0).getPants().getName(), "Sheer white dress");
                assertEquals(savedOutfits.get("First Date").get(0).getPants().getImg().toString(), "dress.png");
                assertEquals(savedOutfits.get("First Date").get(0).getShoes().getName(), "");
        
            } catch (NoPersonException e) {
                fail();
            }

            // List<Thingy> thingies = wr.getThingies();
            // assertEquals(2, thingies.size());
            // checkThingy("needle", Category.STITCHING, thingies.get(0));
            // checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
