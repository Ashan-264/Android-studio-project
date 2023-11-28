package model;

import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import viewmodel.EnemyView;
import viewmodel.GameScreen;
import viewmodel.ScoreObserver;
import viewmodel.HealthObserver;

public class Player implements Comparable<Player>, Subject, Subject2 {

    private static Player player;

    private boolean wallWalk = false;

    private boolean invincibleScore = false;

    private boolean healthBoost =  false;
    private String name;
    private Sprite sprite;
    private int health;

    private int damage;

    private int score;

    private Date date;

    private int playerX; //- Ashan
    private int playerY; //- Ashan
    private Point screenSize;
    private static int maxScore = 999;
    private PlayerMovement playerMovement;

    private Handler handler;
    private boolean isScoring = false;
    private boolean healthUpdating = false;

    private boolean isOnLeaderboard = false;

    private List<ScoreObserver> scoreObservers = new ArrayList<>();
    private List<HealthObserver> healthObservers = new ArrayList<>();

    private Player() {
        this.name = null;
        this.sprite = null;
        this.health = 0;
        this.damage = 0;
        this.score = maxScore;
        this.date = new Date();
        this.isOnLeaderboard = false;
    }

    public static Player getPlayer() {
        if (player == null) {
            player = new Player();
        }

        return player;
    }

    public static void resetPlayer() {
        player = null;
    }

    public void setPlayer(String name, String spriteName, int health, int damage) {
        this.name = name;
        this.sprite = new Sprite(spriteName);
        this.health = health;
        this.damage = damage;
        this.score = maxScore;
        this.date = new Date();
        this.isOnLeaderboard = false;
    }

    public Player copy() {
        Player returnPlayer = new Player();

        returnPlayer.name = this.name;
        returnPlayer.health = this.health;
        returnPlayer.sprite = this.sprite;
        returnPlayer.score = this.score;
        returnPlayer.date = this.date;

        return returnPlayer;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public void subScore(int amount) {
        score -= amount;

        if (score < 0) {
            score = 0;
        }
        this.notifyScoreObservers(score);
    }

    public void takeDamage() {
        health -= damage;
        this.notifyHealthObservers(health);
    }

    public void activateScorePowerup() {
        subScore(-20);
    }

    public void activateHeartPowerup() {
        health += 10;
        this.notifyHealthObservers(health);
    }

    public void setPlayerX(int x) {
        playerX = x;
    }

    public void setPlayerY(int y) {
        playerY = y;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void playerMovement(int initialX, int initialY, Point screenSize) {
        playerX = initialX;
        playerY = initialY;
        this.screenSize = screenSize;
    }

    public void moveDown(int moveSpeed) {
        if (playerY + moveSpeed <= 1780) {
            playerY += moveSpeed;
        }
    }

    public void moveUp(int moveSpeed) {
        if (playerY - moveSpeed > -135) {
            playerY -= moveSpeed;
        }
    }

    public void moveLeft(int moveSpeed) {
        if (playerX - moveSpeed > 0) {
            playerX -= moveSpeed;
        }
    }

    public void moveRight(int moveSpeed) {
        if (playerX + moveSpeed <= 970) {
            playerX += moveSpeed;
        }
    }
    public void onKeyDown(int keyCode, int moveSpeed, KeyEvent event) {
        // Handle key down events to move the player
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_DOWN:
            PlayerMovement downMovement = new MoveDownStrategy();
            setMovementStrategy(downMovement);
            downMovement.move(player, moveSpeed);
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            PlayerMovement upMovement = new MoveUpStrategy();
            setMovementStrategy(upMovement);
            upMovement.move(player, moveSpeed);
            break;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            PlayerMovement leftMovement = new MoveLeftStrategy();
            setMovementStrategy(leftMovement);
            leftMovement.move(player, moveSpeed);
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            PlayerMovement rightMovement = new MoveRightStrategy();
            setMovementStrategy(rightMovement);
            rightMovement.move(player, moveSpeed);
            break;
        default:
            break;
        }
        Log.d("position", "x:" + playerX + "y:" + playerY);
    }

    public void onKeyUp(int keyCode) {
        // Handle key up events (if needed)
        // You can add logic here to stop the player's movement
    }

    @Override
    public int compareTo(Player compPlayer) {
        return compPlayer.score - this.score;
    }

    public void startScoring() {
        if (!isScoring) {
            isScoring = true;
            handler.postDelayed(scoreRunnable, 1000); // 1000 milliseconds = 1 second
        }
    }

    private Runnable scoreRunnable = new Runnable() {
        @Override
        public void run() {
            subScore(1);
            handler.postDelayed(this, 1000); // Post this Runnable again after 1 second
        }
    };

    public void stopScoring() {
        if (isScoring) {
            handler.removeCallbacks(scoreRunnable);
            isScoring = false;
        }
    }

    @Override
    public void addScoreObserver(ScoreObserver observer) {
        scoreObservers.add(observer);
    }

    // Add a method to remove observers
    @Override
    public void removeScoreObserver(ScoreObserver observer) {
        scoreObservers.remove(observer);
    }

    @Override
    public void notifyScoreObservers(int newScore) {
        for (ScoreObserver observer : scoreObservers) {
            observer.onScoreChanged(newScore);
        }
    }

    public boolean onLeaderboard() {
        return isOnLeaderboard;
    }

    public void setAddedToLeaderboard() {
        isOnLeaderboard = true;
    }

    public void setMovementStrategy(PlayerMovement strategy) {
        this.playerMovement = strategy;
    }

    public void move(int moveSpeed) {
        if (playerMovement != null) {
            playerMovement.move(this, moveSpeed);
        }
    }

    // health updates
    public void startHealthUpdates() {
        if (!healthUpdating) {
            healthUpdating = true;
            handler.postDelayed(healthRunnable, 1000); // 1000 milliseconds = 1 second
        }
    }
    private Runnable healthRunnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000); // Post this Runnable again after 1 second
        }
    };
    public void stopHealthUpdates() {
        if (healthUpdating) {
            handler.removeCallbacks(healthRunnable);
            healthUpdating = false;
        }
    }

    @Override
    public void notifyHealthObservers(int newHealth) {
        for (HealthObserver observer : healthObservers) {
            observer.onHealthChanged(newHealth);
        }
    }

    public void checkHeartPowerUp(int heartPowerupX, int heartPowerupY) {
        if (Math.abs(playerX - heartPowerupX) < 100 && Math.abs(playerY - heartPowerupY) < 60) {
            player.activateHeartPowerup();
        }
    }

    public void checkScorePowerUp(int heartPowerupX, int heartPowerupY) {
        if (Math.abs(playerX - heartPowerupX) < 100 && Math.abs(playerY - heartPowerupY) < 60) {
            player.activateScorePowerup();
        }
    }

    @Override
    public void addHealthObserver(HealthObserver observer) {
        healthObservers.add(observer);
    }

    // Add a method to remove observers
    @Override
    public void removeHealthObserver(HealthObserver observer) {
        healthObservers.remove(observer);
    }


}


