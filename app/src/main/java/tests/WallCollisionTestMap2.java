package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class WallCollisionTestMap2 {

    private Player player = Player.getPlayer();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 3, 0);
        player.setPlayerY(740);
        player.setPlayerX(610);
    }

    @Test
    public void testLowerWall() {
        player.moveDown(40);
        assertEquals(740, player.getPlayerY());
    }
    @Test
    public void testRightWall() {
        player.moveRight(40);
        assertEquals(610, player.getPlayerX());
    }

}
