package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class MovementTest {

    private Player player = Player.getPlayer();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 3, 0);
        player.setPlayerY(500);
        player.setPlayerX(500);
    }

    @Test
    public void testUp() {
        player.moveUp(40);
        assertEquals(460, player.getPlayerY());
    }
    @Test
    public void testDown() {
        player.moveDown(40);
        assertEquals(540, player.getPlayerY());
    }
    @Test
    public void testRight() {
        player.moveRight(40);
        assertEquals(540, player.getPlayerX());
    }
    @Test
    public void testLeft() {
        player.moveLeft(40);
        assertEquals(460, player.getPlayerX());
    }

}
