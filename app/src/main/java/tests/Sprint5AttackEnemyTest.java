package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.Bat;
import model.Ghost;
import model.Knight;
import model.Mage;

public class Sprint5AttackEnemyTest {

    private Player player = Player.getPlayer();
    private Bat bat = Bat.getEnemy();

    private Knight knight = Knight.getEnemy();

    private Mage mage = Mage.getEnemy();

    private Ghost ghost = Ghost.getEnemy();
    @Before
    public void initialization() {
        player.setPlayer("George Burdell", "Burdell", 30, 0);
        player.setPlayerY(-60);
        player.setPlayerX(50);
        bat.setEnemy();
        bat.setEnemyY(-60);
        bat.setEnemyX(50);
        ghost.setEnemy();
        ghost.setEnemyY(-60);
        ghost.setEnemyX(500);
        mage.setEnemy();
        mage.setEnemyY(-60);
        mage.setEnemyX(500);
        knight.setEnemy();
        knight.setEnemyY(-60);
        knight.setEnemyX(50);
    }

    @Test
    public void testAttackBat() {
        assertEquals(0, player.getEnemiesKilled());
        player.performAttack(bat.getEnemyX(), bat.getEnemyY());
        assertEquals(1, player.getEnemiesKilled());
    }

    @Test
    public void testAttackGhost() {
        assertEquals(0, player.getEnemiesKilled());
        player.performAttack(ghost.getEnemyX(), ghost.getEnemyY());
        assertEquals(0, player.getEnemiesKilled());
    }

    @Test
    public void testAttackMage() {
        assertEquals(0, player.getEnemiesKilled());
        player.performAttack(mage.getEnemyX(), mage.getEnemyY());
        assertEquals(0, player.getEnemiesKilled());
    }

    @Test
    public void testAttackKnight() {
        assertEquals(0, player.getEnemiesKilled());
        player.performAttack(knight.getEnemyX(), knight.getEnemyY());
        assertEquals(1, player.getEnemiesKilled());
    }

}
