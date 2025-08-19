import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import model.Pants;

public class TestPants {
    private Pants testPants;
    private String testImgPath;
    private ImageIcon testImg;

    @BeforeEach
    void runBefore() {
        testImgPath = "ui/img/test-pants.png";
        testImg = new ImageIcon("ui/img/test-pants.png");
        testPants = new Pants("Plaid yellow skirt", testImgPath, testImg);

    }

    @Test
    void testConstructor() {
        assertEquals(testPants.getName(), "Plaid yellow skirt");
        assertEquals(testPants.getImg(), testImg);
        assertEquals(testPants.getImgPath(), testImgPath);
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

    @Test
    void testSetImage() {
        String newImgPath = "ui/img/test-pants-two.png";
        ImageIcon newImg = new ImageIcon(newImgPath);

        testPants.setImgPath(newImgPath);
        testPants.setImg(newImg);

        assertEquals(testPants.getImg(), newImg);
        assertEquals(testPants.getImgPath(), newImgPath);
    }
}
