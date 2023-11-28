package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;

public class Sprint5PowerUpCollisionTestMap3 {
    //for map 3 attack power up

    private Player player = Player.getPlayer();

    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 30, 0);
        player.setPlayerY(450);
        player.setPlayerX(960);
        player.setPlayerYAttackRange(400);
        player.setPlayerXAttackRange(400);
    }

    @Test
    public void testHeartCollisionPowerUpMap3MoveLeft() {
        int damagePowerupX = 840;
        int damagePowerupY = 540;
        player.setPlayerY(500);
        assertEquals(400, player.getPlayerXAttackRange());
        player.moveLeft(40);
        player.checkAttackPowerUp(damagePowerupX, damagePowerupY);
        assertEquals(600, player.getPlayerXAttackRange());
    }

    @Test
    public void testHeartCollisionPowerUpMap3MoveDown() {
        int damagePowerupX = 840;
        int damagePowerupY = 540;
        player.setPlayerX(920);
        assertEquals(400, player.getPlayerXAttackRange());
        player.moveDown(40);
        player.checkAttackPowerUp(damagePowerupX, damagePowerupY);
        assertEquals(600, player.getPlayerXAttackRange());
    }


}