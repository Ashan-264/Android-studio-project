package model;

import android.graphics.Point;

public interface Enemy {
    public void setEnemy();


    public Sprite getSprite();


    public void setEnemyX(int x);

    public void setEnemyY(int y);

    public void enemyMovement(int initialX, int initialY, Point screenSize);

}
