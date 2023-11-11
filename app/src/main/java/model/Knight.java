
package model;

        import android.graphics.Point;
        import android.os.Handler;



public class Knight implements Enemy {

    private static Knight enemy;
    private Sprite sprite;
    private int enemyX;
    private int enemyY;
    private Point screenSize;
    private static int maxScore = 999;
    private PlayerMovement enemyMovement;
    private Handler handler = new Handler();
    private Knight() {
        this.sprite = null;
    }
    public static Knight getEnemy() {
        if (enemy == null) {
            enemy = new Knight();
        }
        return enemy;
    }

    public static void resetEnemy() {
        enemy = null;
    }

    public void setEnemy() {
        this.sprite = new Sprite("Knight");
    }
    public Knight copy() {
        Knight returnEnemy = new Knight();
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





