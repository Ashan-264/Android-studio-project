
package model;

import android.graphics.Point;
import android.os.Handler;

public class Mace implements Enemy {

    private static Mace enemy;
    private Sprite sprite;
    private int enemyX;
    private int enemyY;
    private Point screenSize;
    private static int maxScore = 999;
    private PlayerMovement enemyMovement;
    private Handler handler = new Handler();
    private Mace() {
        this.sprite = null;
    }
    public static Mace getEnemy() {
        if (enemy == null) {
            enemy = new Mace();
        }
        return enemy;
    }

    public static void resetEnemy() {
        enemy = null;
    }

    public void setEnemy() {
        this.sprite = new Sprite("Mace");
    }
    public Mace copy() {
        Mace returnEnemy = new Mace();
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
    public int getEnemyX() {
        return enemyX;
    }
    public int getEnemyY() {
        return enemyY;
    }
    public void enemyMovement(int initialX, int initialY, Point screenSize) {
        enemyX = initialX;
        enemyY = initialY;
        this.screenSize = screenSize;
    }
    public void moveUp(int moveSpeed) {
        enemyY -= moveSpeed;
    }
    public void moveDown(int moveSpeed) {
        enemyY += moveSpeed;
    }

}





