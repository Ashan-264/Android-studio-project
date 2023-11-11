package model;

import android.graphics.Point;

import java.util.Date;

public interface Enemy {
    public void setEnemy();


    public Sprite getSprite();


    public void setEnemyX(int x);

    public void setEnemyY(int y);

    public int getEnemyX();

    public int getEnemyY();

    public void enemyMovement(int initialX, int initialY, Point screenSize) ;

}
