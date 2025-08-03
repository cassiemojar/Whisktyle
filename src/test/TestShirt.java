import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import model.Shirt;

public class TestShirt {
    private Shirt testShirt;

    @BeforeEach
    void runBefore() {
        testShirt = new Shirt("Plaid yellow top", new ImageIcon("ui/img/test-shirt.png"));
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

    @Test
    void testSetImgIcon() {
        ImageIcon testImg = new ImageIcon("ui/img/test-shirt-two.png");
        testShirt.setImg(testImg);
        assertTrue(testShirt.getImg().equals(testImg));
    }
}
