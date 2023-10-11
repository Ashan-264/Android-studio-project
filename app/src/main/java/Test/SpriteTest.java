package Test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import Model.Sprite;

public class SpriteTest {

    @Test
    public void testGetImageName() {
        // Create a new Sprite object with the image name "example_image"
        Sprite sprite = new Sprite("Testimage");

        // Assert that getImageName returns the correct image name
        assertEquals("testimage", sprite.getImageName());
    }
}
