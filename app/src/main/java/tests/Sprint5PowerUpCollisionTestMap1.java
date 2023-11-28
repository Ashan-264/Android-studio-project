package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class Sprint5PowerUpCollisionTestMap1 {
    //for map 1 score power up

    private Player player = Player.getPlayer();

    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 30, 0);
        player.setPlayerY(310);
        player.setPlayerX(240);
    }

    @Test
    public void testHeartCollisionPowerUpMap1MoveLeft() {
        int scorePowerupX = 120;
        int scorePowerupY = 400;
        assertEquals(999, player.getScore());
        player.setPlayerY(350);
        player.moveLeft(40);
        player.checkScorePowerUp(scorePowerupX, scorePowerupY);
        assertEquals(1019, player.getScore());


    }

    @Test
    public void testHeartCollisionPowerUpMap1MoveDown() {
        int scorePowerupX = 120;
        int scorePowerupY = 400;
        assertEquals(999, player.getScore());
        player.setPlayerX(200);
        player.moveDown(40);
        player.checkScorePowerUp(scorePowerupX, scorePowerupY);
        assertEquals(1019, player.getScore());
    }


}
