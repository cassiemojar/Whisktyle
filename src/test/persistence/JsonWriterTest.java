package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Closet;
import model.Clothing;
import model.NoPersonException;
import model.Outfit;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Closet closet = new Closet();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCloset() {
        try {
            Closet closet = new Closet();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCloset.json");
            writer.open();
            writer.write(closet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCloset.json");
            closet = reader.read();

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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterClosetWithData() {
        try {
            Closet closet = new Closet();

            Person person = new Person("Cher", new ImageIcon("person.png"),
                    new Shirt("Sheer white jacket", new ImageIcon("jacket.png")),
                    new Pants("Sheer white dress", new ImageIcon("dress.png")),
                    new Shoes("Silver heels"));
                
            person.setShirt(new Shirt("Sheer white jacket", new ImageIcon("jacket.png")));
            person.setPants(new Pants("Sheer white dress", new ImageIcon("dress.png")));
            person.setShoes(new Shoes("Silver heels"));
            closet.setPerson(person);

            closet.addShirt(new Shirt("Plaid yellow jacket", new ImageIcon("yellow jacket.png")));
            closet.addPants(new Pants("Plaid yellow skirt", new ImageIcon("skirt.png")));
            //closet.addShoes(new Shoes("Yellow flats"));

            Outfit outfit = new Outfit("As If!",
                    new Shirt("Plaid yellow jacket", new ImageIcon("yellow jacket.png")),
                    new Pants("Plaid yellow skirt", new ImageIcon("skirt.png")),
                    new Shoes("Yellow flats"));
            closet.addOutfit("School", outfit);

            JsonWriter writer = new JsonWriter("./data/testWriterCloset.json");
            writer.open();
            writer.write(closet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCloset.json");
            Closet readCloset = reader.read();

            Person readPerson = readCloset.getPerson();
            assertEquals("Cher", readPerson.getName());
            assertEquals("person.png", readPerson.getImg().toString());
            assertEquals("Sheer white jacket", readPerson.getShirt().getName());
            assertEquals("Sheer white dress", readPerson.getPants().getName());
           // assertEquals("Silver heels", readPerson.getShoes().getName());


            List<Clothing> shirts = readCloset.getShirts();
            assertEquals(1, shirts.size());
            assertEquals("Plaid yellow jacket", shirts.get(0).getName());

            List<Clothing> pants = readCloset.getPants();
            assertEquals(1, pants.size());
            assertEquals("Plaid yellow skirt", pants.get(0).getName());

            List<Clothing> shoes = readCloset.getShoes();
            assertEquals(0, shoes.size());

            Outfit readOutfit = readCloset.getAnOutfit("School", "As If!");
            assertEquals("As If!", readOutfit.getName());
            assertEquals("Plaid yellow jacket", readOutfit.getShirt().getName());
            assertEquals("Plaid yellow skirt", readOutfit.getPants().getName());
            // assertEquals("Yellow flats", readOutfit.getShoes().getName());

        } catch (IOException | NoPersonException | model.OutfitException e) {
            fail("Exception should not have been thrown");
        }
    }
}
