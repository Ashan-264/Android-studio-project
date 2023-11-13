package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.Bat;
import model.Ghost;
import model.Knight;
import model.Mage;


public class Sprint4CollisionMovementTest {

    private Player player = Player.getPlayer();
    private Bat bat = Bat.getEnemy();

    private Knight knight = Knight.getEnemy();

    private Mage mage = Mage.getEnemy();

    private Ghost ghost = Ghost.getEnemy();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 3, 0);
        player.setPlayerY(-60);
        player.setPlayerX(10);
        bat.setEnemy();
        bat.setEnemyY(-60);
        bat.setEnemyX(50);
        ghost.setEnemy();
        ghost.setEnemyY(-60);
        ghost.setEnemyX(50);
        mage.setEnemy();
        mage.setEnemyY(-60);
        mage.setEnemyX(50);
        knight.setEnemy();
        knight.setEnemyY(-60);
        knight.setEnemyX(50);

    }

    @Test
    public void testMovementCollisionBat() {
        assertEquals(false, bat.isCollide(player));
        player.moveRight(40);
        assertEquals(true, bat.isCollide(player));
    }

    @Test
    public void testMovementCollisionGhost() {
        assertEquals(false, ghost.isCollide(player));
        player.moveRight(40);
        assertEquals(true, ghost.isCollide(player));
    }

    @Test
    public void testMovementCollisionKnight() {
        assertEquals(false, knight.isCollide(player));
        player.moveRight(40);
        assertEquals(true, knight.isCollide(player));
    }

    @Test
    public void testMovementCollisionMage() {
        assertEquals(false, mage.isCollide(player));
        player.moveRight(40);
        assertEquals(true, mage.isCollide(player));
    }

}
