package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.Bat;
import model.Ghost;
import model.Knight;
import model.Mage;


public class Sprint4CollisionTest {

    private Player player = Player.getPlayer();
    private Bat bat = Bat.getEnemy();

    private Knight knight = Knight.getEnemy();

    private Mage mage = Mage.getEnemy();

    private Ghost ghost = Ghost.getEnemy();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 3, 0);
        player.setPlayerY(-100);
        player.setPlayerX(10);
        bat.setEnemy();
        bat.setEnemyY(-100);
        bat.setEnemyX(10);
        ghost.setEnemy();
        ghost.setEnemyY(-100);
        ghost.setEnemyX(10);
        mage.setEnemy();
        mage.setEnemyY(-100);
        mage.setEnemyX(10);
        knight.setEnemy();
        knight.setEnemyY(-100);
        knight.setEnemyX(10);

    }

    @Test
    public void testCollisionBat() {
        assertEquals(true, bat.isCollide(player));
    }

    @Test
    public void testCollisionGhost() {
        assertEquals(true, ghost.isCollide(player));
    }

    @Test
    public void testCollisionKnight() {
        assertEquals(true, knight.isCollide(player));
    }

    @Test
    public void testCollisionMage() {
        assertEquals(true, mage.isCollide(player));
    }

}
