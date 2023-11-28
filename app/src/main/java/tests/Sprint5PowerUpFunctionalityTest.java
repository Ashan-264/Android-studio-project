package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class Sprint5PowerUpFunctionalityTest {

    private Player player = Player.getPlayer();

    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 30, 0);
        player.setPlayerY(-60);
        player.setPlayerX(10);
    }

    @Test
    public void testHeartPowerUp() {
        assertEquals(30, player.getHealth());
        player.activateHeartPowerup();
        assertEquals(40, player.getHealth());
    }

    @Test
    public void testScorePowerUp() {
        //assertEquals(30, player.getHealth());
        assertEquals(999, player.getScore());
        player.activateScorePowerup();
        assertEquals(1019, player.getScore());
    }


}
