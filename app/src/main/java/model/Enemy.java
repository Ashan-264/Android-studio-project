package model;

import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import viewmodel.ScoreObserver;


    public class Enemy  {

        private static model.Enemy enemy;
        private String name;
        private Sprite sprite;
        private int health;

        private int score;

        private Date date;

        private int enemyX;
        private int enemyY;
        private Point screenSize;
        private static int maxScore = 999;
        private PlayerMovement enemyMovement;

        private Handler handler = new Handler();
        private boolean isScoring = false;

        private boolean isOnLeaderboard = false;

        private List<ScoreObserver> scoreObservers = new ArrayList<>();

        private Enemy() {
            this.name = null;
            this.sprite = null;
            this.health = 0;
            this.score = maxScore;
            this.date = new Date();
            this.isOnLeaderboard = false;
        }

        public static Enemy getEnemy() {
            if (enemy == null) {
                enemy = new Enemy();
            }

            return enemy;
        }

        public static void resetEnemy() {
            enemy = null;
        }

        public void setEnemy(String name, String spriteName, int health) {
            this.name = name;
            this.sprite = new Sprite(spriteName);
            this.health = health;
            this.score = maxScore;
            this.date = new Date();
            this.isOnLeaderboard = false;
        }

        public model.Enemy copy() {
            model.Enemy returnEnemy = new model.Enemy();

            returnEnemy.name = this.name;
            returnEnemy.health = this.health;
            returnEnemy.sprite = this.sprite;
            returnEnemy.score = this.score;
            returnEnemy.date = this.date;

            return returnEnemy;
        }

        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public Sprite getSprite() {
            return sprite;
        }

        public int getScore() {
            return score;
        }

        public Date getDate() {
            return date;
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

        public void moveDown(int moveSpeed) {
            if (enemyY + moveSpeed <= 1780) {
                enemyY += moveSpeed;
            }
        }

        public void moveUp(int moveSpeed) {
            if (enemyY - moveSpeed > -135) {
                enemyY -= moveSpeed;
            }
        }

        public void moveLeft(int moveSpeed) {
            if (enemyX - moveSpeed > 0) {
                enemyX -= moveSpeed;
            }
        }

        public void moveRight(int moveSpeed) {
            if (enemyX + moveSpeed <= 970) {
                enemyX += moveSpeed;
            }
        }
//        public void onKeyDown(int keyCode, int moveSpeed, KeyEvent event) {
//            // Handle key down events to move the enemy
//            switch (keyCode) {
//                case KeyEvent.KEYCODE_DPAD_DOWN:
//                    PlayerMovement downMovement = new MoveDownStrategy();
//                    setMovementStrategy(downMovement);
//                    downMovement.move(enemy, moveSpeed);
//                    break;
//                case KeyEvent.KEYCODE_DPAD_UP:
//                    PlayerMovement upMovement = new MoveUpStrategy();
//                    setMovementStrategy(upMovement);
//                    upMovement.move(enemy, moveSpeed);
//                    break;
//                case KeyEvent.KEYCODE_DPAD_LEFT:
//                    PlayerMovement leftMovement = new MoveLeftStrategy();
//                    setMovementStrategy(leftMovement);
//                    leftMovement.move(enemy, moveSpeed);
//                    break;
//                case KeyEvent.KEYCODE_DPAD_RIGHT:
//                    PlayerMovement rightMovement = new MoveRightStrategy();
//                    setMovementStrategy(rightMovement);
//                    rightMovement.move(enemy, moveSpeed);
//                    break;
//                default:
//                    break;
//            }
//            Log.d("position", "x:" + enemyX + "y:" + enemyY);
//        }

        public void onKeyUp(int keyCode) {
            // Handle key up events (if needed)
            // You can add logic here to stop the enemy's movement
        }




//        public void setMovementStrategy(PlayerMovement strategy) {
//            this.enemyMovement = strategy;
//        }

//        public void move(int moveSpeed) {
//            if (enemyMovement != null) {
//                enemyMovement.move(this, moveSpeed);
//            }
//        }
    }


