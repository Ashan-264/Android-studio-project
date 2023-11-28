package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class Sprint5PowerUpCollisionTestMap2 {
    //for map 2 heart power up

    private Player player = Player.getPlayer();

    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 30, 0);
        player.setPlayerY(1220);
        player.setPlayerX(300);
    }

    @Test
    public void testHeartCollisionPowerUpMap2MoveRight() {
        assertEquals(30, player.getHealth());
        player.setPlayerY(1180);
        int heartPowerupX = 410;
        int heartPowerupY = 1140;
        player.moveRight(40);
        player.checkHeartPowerUp(heartPowerupX, heartPowerupY);
        assertEquals(40, player.getHealth());
    }

    @Test
    public void testHeartCollisionPowerUpMap2MoveUp() {
        assertEquals(30, player.getHealth());
        player.setPlayerX(340);
        int heartPowerupX = 410;
        int heartPowerupY = 1140;
        player.moveUp(40);
        player.checkHeartPowerUp(heartPowerupX, heartPowerupY);
        assertEquals(40, player.getHealth());
    }


}
