import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import model.Shirt;

public class TestShirt {
    private Shirt testShirt;
    private String testImgPath;
    private ImageIcon testImg;

    @BeforeEach
    void runBefore() {
        testImgPath = "ui/img/test-shirt.png";
        testImg = new ImageIcon(testImgPath);
        testShirt = new Shirt("Plaid yellow top", testImgPath, testImg);
    }

    @Test
    void testConstructor() {
        assertEquals(testShirt.getName(), "Plaid yellow top");
        assertEquals(testShirt.getImg(), testImg);
        assertEquals(testShirt.getImgPath(), testImgPath);
    }

    @Test
    void testSetName() {
        testShirt.setName("Sheer white jacket");
        assertEquals(testShirt.getName(), "Sheer white jacket");
    }

    @Test
    void testSetImgIcon() {
        String newImgPath = "ui/img/test-shirt-two.png";
        ImageIcon newImg = new ImageIcon(newImgPath);
        testShirt.setImg(newImg);
        testShirt.setImgPath(newImgPath);
        assertTrue(testShirt.getImg().equals(newImg));
        assertTrue(testShirt.getImgPath().equals(newImgPath));
    }
}
