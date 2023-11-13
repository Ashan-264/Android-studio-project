package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class ScreenLimitTestUL {

    private Player player = Player.getPlayer();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Wizard", 3, 0);
        player.setPlayerY(-100);
        player.setPlayerX(10);

    }

    @Test
    public void testUpperBound() {
        player.moveUp(40);
        assertEquals(-100, player.getPlayerY());
    }
    @Test
    public void testLeftBound() {
        player.moveLeft(40);
        assertEquals(10, player.getPlayerX());
    }

}
