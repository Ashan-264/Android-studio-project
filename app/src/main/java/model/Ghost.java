
package model;

        import android.graphics.Point;
        import android.os.Handler;



public class Ghost implements Enemy {

    private static Ghost enemy;
    private Sprite sprite;
    private int enemyX;
    private int enemyY;
    private Point screenSize;
    private static int maxScore = 999;
    private PlayerMovement enemyMovement;
    private Handler handler = new Handler();
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
    public void moveLeft(int moveSpeed) {
        enemyX -= moveSpeed;
    }
    public void moveRight(int moveSpeed) {
        enemyX += moveSpeed;
    }

}





