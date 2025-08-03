import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import model.Pants;

public class TestPants {
    private Pants testPants;

    @BeforeEach
    void runBefore() {
        testPants = new Pants("Plaid yellow skirt", new ImageIcon("ui/img/test-pants.png"));
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

    @Test
    void testSetPants() {
        ImageIcon testImg = new ImageIcon("ui/img/test-pants-two.png");
        testPants.setImg(testImg);
        assertTrue(testPants.getImg().equals(testImg));
    }
}
