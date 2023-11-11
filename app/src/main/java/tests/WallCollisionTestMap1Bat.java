package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class WallCollisionTestMap1Bat {

    private Player player = Player.getPlayer();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 3, 0);
        player.setPlayerY(180);
        player.setPlayerX(320);
    }

    @Test
    public void testUpperWall() {
        player.moveUp(40);
        assertEquals(180, player.getPlayerY());
    }
    @Test
    public void testLeftWall() {
        player.moveLeft(40);
        assertEquals(320, player.getPlayerX());
    }

}
