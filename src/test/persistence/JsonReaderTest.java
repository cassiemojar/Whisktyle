package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import model.Closet;
import model.NoPersonException;

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
}
