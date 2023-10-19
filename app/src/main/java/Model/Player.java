package Model;

import android.graphics.Point;
import android.view.KeyEvent;

import java.util.Date;

public class Player implements Comparable<Player> {

    private static Player player;
    private String name;
    private Sprite sprite;
    private int health;

    private int score;

    private Date date;

    private int playerX; //- Ashan
    private int playerY; //- Ashan
    private Point screenSize;
    static int MAX_SCORE = 999;

    private Player() {
        this.name = null;
        this.sprite = null;
        this.health = 0;
        this.score = MAX_SCORE;
        this.date = new Date();
    }

    public static Player getPlayer() {
        if (player == null) {
            player = new Player();
        }

        return player;
    }

    public void setPlayer(String name, String spriteName, int health) {
        this.name = name;
        this.sprite = new Sprite(spriteName);
        this.health = health;
        this.score = MAX_SCORE;
        this.date = new Date();
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

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getScore() { return score;}

    public Date getDate() {return date;}

    public void subScore(int amount) {
        score -= amount;

        if (score < 0) {
            score = 0;
        }
    }

public void setPlayerX (int x) {
        playerX = x;
}

    public void setPlayerY (int y) {
        playerY = y;
    }

    public int getPlayerX () {
        return playerX;
    }

    public int getPlayerY () {
        return playerY;
    }

    public void playerMovement(int initialX, int initialY, Point screenSize) {
        playerX = initialX;
        playerY = initialY;
        this.screenSize = screenSize;
    }

    public void onKeyDown(int keyCode, int moveSpeed, KeyEvent event) {
        // Handle key down events to move the player
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (playerY + moveSpeed < screenSize.y - screenSize.y / 6) {
                    playerY += moveSpeed;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                if (playerY - moveSpeed > -135 ) {
                    playerY -= moveSpeed;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (playerX - moveSpeed > 0) {
                    playerX -= moveSpeed;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (playerX + moveSpeed < screenSize.x - screenSize.x / 6) {
                    playerX += moveSpeed;
                }
                break;
        }
    }

    public void onKeyUp(int keyCode) {
        // Handle key up events (if needed)
        // You can add logic here to stop the player's movement
    }





    @Override
    public int compareTo(Player compPlayer){
        return compPlayer.score - this.score;
    }
}


