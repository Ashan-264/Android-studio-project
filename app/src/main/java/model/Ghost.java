
package model;

import android.graphics.Point;
import android.os.Handler;

public class Ghost implements Enemy {

    private static Ghost enemy;
    private Sprite sprite;

    private int baseDamage = 1;

    private int difficulty = 1; //difficulty multiplier (2 for medium 4 for hard)
    private int enemyX;
    private int enemyY;
    private Point screenSize;
    private Ghost() {
        this.sprite = null;
    }
    public static Ghost getEnemy() {
        if (enemy == null) {
            enemy = new Ghost();
        }
        return enemy;
    }

    public static void resetEnemy() {
        enemy = null;
    }

    public void setEnemy() {
        this.sprite = new Sprite("Ghost");
    }
    public Ghost copy() {
        Ghost returnEnemy = new Ghost();
        returnEnemy.sprite = this.sprite;
        return returnEnemy;
    }
    public Sprite getSprite() {
        return sprite;
    }
    public void setEnemyX(int x) {
        enemyX = x;
    }
    public void setEnemyY(int y) {
        enemyY = y;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isCollide(Player player) {
        if (player.getPlayerX() == enemyX && player.getPlayerY() == enemyY) {
            player.setHealth(player.getHealth() - baseDamage * difficulty);
            return true;
        } else {
            return false;
        }
    }
    public void enemyMovement(int initialX, int initialY, Point screenSize) {
        enemyX = initialX;
        enemyY = initialY;
        this.screenSize = screenSize;
    }
    public void moveLeft(int moveSpeed) {
        enemyX -= moveSpeed;
    }
    public void moveRight(int moveSpeed) {
        enemyX += moveSpeed;
    }
}





