package model;

import android.graphics.Point;

import java.util.Date;

public interface Enemy {
    public void setEnemy(String name, String spriteName, int health);


    public String getName();

    public int getHealth();

    public Sprite getSprite();

    public int getScore();

    public Date getDate();



    public void setEnemyX(int x);

    public void setEnemyY(int y);

    public int getEnemyX();

    public int getEnemyY();

    public void enemyMovement(int initialX, int initialY, Point screenSize) ;

    public void moveDown(int moveSpeed) ;

    public void moveUp(int moveSpeed) ;

    public void moveLeft(int moveSpeed) ;

    public void moveRight(int moveSpeed);
}
