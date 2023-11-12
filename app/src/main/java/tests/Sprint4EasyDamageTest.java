package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.Bat;
import model.Ghost;
import model.Knight;
import model.Mage;

public class Sprint4EasyDamageTest {

    private Player player = Player.getPlayer();
    private Bat bat = Bat.getEnemy();

    private Knight knight = Knight.getEnemy();

    private Mage mage = Mage.getEnemy();

    private Ghost ghost = Ghost.getEnemy();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 30, 0);
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
    public void testEasyDamageBat() {
        assertEquals(30, player.getHealth());
        player.moveRight(40);
        bat.isCollide(player);
        assertEquals(29, player.getHealth());
    }

    @Test
    public void testEasyDamageGhost() {
        assertEquals(30, player.getHealth());
        player.moveRight(40);
        ghost.isCollide(player);
        assertEquals(29, player.getHealth());
    }

    @Test
    public void testEasyDamageKnight() {
        assertEquals(30, player.getHealth());
        player.moveRight(40);
        knight.isCollide(player);
        assertEquals(29, player.getHealth());
    }

    @Test
    public void testEasyDamageMage() {
        assertEquals(30, player.getHealth());
        player.moveRight(40);
        mage.isCollide(player);
        assertEquals(29, player.getHealth());
    }

}
