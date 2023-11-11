package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class ScreenLimitTestDR {

    private Player player = Player.getPlayer();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 3, 0);
        player.setPlayerY(1780);
        player.setPlayerX(970);
    }

    @Test
    public void testLowerBound() {
        player.moveUp(40);
        assertEquals(1780, player.getPlayerY());
    }
    @Test
    public void testRightBound() {
        player.moveLeft(40);
        assertEquals(970, player.getPlayerX());
    }

}
