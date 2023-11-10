package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class PlayerTest {
    private Player player;
    @Before
    public void initialization() {
        player = Player.getPlayer();
        player.setPlayer("George Burdell", "Burdell", 3, 0);
    }

    @Test
    public void testName() {
        assertEquals("George Burdell", player.getName());
    }
    @Test
    public void testSprite() {
        assertEquals("Burdell", player.getSprite().getImageName());

    }
    @Test
    public void testHealth() {
        assertEquals(3, player.getHealth());
    }

    @Test
    public void testDefaultScore() {
        assertEquals(10, player.getScore());
    }

    @Test
    public void testSubScore() {
        player.subScore(4);
        assertEquals(6, player.getScore());
    }
    @Test
    public void testNegativeScore() {
        player.subScore(12);
        assertEquals(0, player.getScore());
    }




}