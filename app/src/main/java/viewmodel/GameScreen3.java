package viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import model.GameObject;
import model.Player;

public class GameScreen3 extends AppCompatActivity implements ScoreObserver, HealthObserver {

    private TextView playerScoreText;
    private TextView playerHealthText;
    private PlayerView playerView;

    private int playerY = 1300;
    private int playerX = 200;  //Ashan

    private final int moveSpeed = 40;

    private RelativeLayout gameLayout; //Ashan

    private Point screenSize;

    private EnemyView mageView;
    private EnemyView knightView;
    private int mageX;
    private int mageY;
    private int knightX;
    private int knightY;

    private PowerupView damagePowerupView;

    private int damagePowerupX;
    private int damagePowerupY;

    private int xAttackRange = 400;
    private int yAttackRange = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen3);

        GameObject gameObject = GameObject.getGameObject();
        Player player = gameObject.getPlayer();
        Player.getPlayer().addScoreObserver(this);
        Player.getPlayer().addHealthObserver(this);

        int xAttackRange = player.getPlayerXAttackRange();
        int yAttackRange = player.getPlayerYAttackRange();

        // Display difficulty
        TextView difficultyText = (TextView) findViewById(R.id.difficulty);
        difficultyText.setText(gameObject.getDifficulty());

        // Display player name
        TextView playerNameText = (TextView) findViewById(R.id.gamePlayerName);
        playerNameText.setText(player.getName());

        // Display player Health
        playerHealthText = (TextView) findViewById(R.id.playerHealth);
        playerHealthText.setText("Health: " + Integer.toString(player.getHealth()));

        // Display player sprite
        String spriteName = player.getSprite().getImageName();


        // Get the screen size
        Display display = getWindowManager().getDefaultDisplay();
        screenSize = new Point();
        display.getSize(screenSize);

        playerX = 400;
        playerY = 500;

        //AShan
        // Create character
        gameLayout = findViewById(R.id.gameLayout3);
        playerView = new PlayerView(this, playerX, playerY, spriteName);
        gameLayout.addView(playerView);

        // Enemy 1: mage
        mageX = 600;
        mageY = 460;

        // Enemy 2: knight
        knightX = 780;
        knightY = 1380;

        // Create enemy
        mageView = new EnemyView(this, mageX, mageY, "Mage");
        mageView.startMoving();
        gameLayout.addView(mageView);
        knightView = new EnemyView(this, knightX, knightY, "Knight");
        knightView.startMoving();
        gameLayout.addView(knightView);


        // create score powerup
        damagePowerupX = 840;
        damagePowerupY = 540;

        // create powerup view
        damagePowerupView = new PowerupView(this, damagePowerupX, damagePowerupY, "DamagePowerup");
        gameLayout.addView(damagePowerupView);


        // Display score
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(player.getScore()));

    }



    @Override
    public void onScoreChanged(int newScore) {
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(newScore));

        if (newScore <= 0) {
            Intent game = new Intent(GameScreen3.this, EndScreen.class);
            startActivity(game);
        }
    }

    public void onHealthChanged(int newHealth) {
        playerHealthText = (TextView) findViewById(R.id.playerHealth);
        playerHealthText.setText("Health: " + Integer.toString(newHealth));

        if (newHealth <= 0) {
            Intent game = new Intent(GameScreen3.this, EndScreen.class);
            startActivity(game);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Player player = Player.getPlayer();

        int newX = player.getPlayerX();
        int newY = player.getPlayerY();
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_DOWN:
            newX = playerX;
            newY = playerY + moveSpeed;
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            newX = playerX;
            newY = playerY - moveSpeed;
            break;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            newX = playerX - moveSpeed;
            newY = playerY;
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            newX = playerX + moveSpeed;
            newY = playerY;
            break;
        case KeyEvent.KEYCODE_1:
            if (Math.abs(playerX - knightX) < xAttackRange
                    && Math.abs(playerY - knightY) < yAttackRange) {
                // Remove the bat from the screen
                Log.d("Enemy moving", "It works");
                knightView.stopMovingAndRemove(this);
                knightX = 0;
                knightY = 0;
            }

            //Huy's addition
            if (Math.abs(playerX - mageX) < xAttackRange
                    && Math.abs(playerY - mageY) < yAttackRange) {
                //remove mage from screen
                Log.d("Enemy moving", "It works");
                mageView.stopMovingAndRemove(this);
                mageX = 0;
                mageY = 0;
            }
            break;
        default:
            break;
        }

        boolean legalMove = false;
        // top square, entrance rectangle, vertical passage, horizontal passage
        if (newX >= 200 && newX <= 600 && newY >= 180 && newY <= 820) {
            legalMove = true;
        } else if (newX >= 600 && newX <= 880 && newY >= 460 && newY <= 580) {
            legalMove = true;
        } else if (newX >= 800 && newX <= 880 && newY >= 500 && newY <= 1540) {
            legalMove = true;
        } else if (newX <= 880 && newY >= 1420 && newY <= 1540) {
            legalMove = true;
        }

        if (legalMove) {
            player.playerMovement(playerX, playerY, screenSize); // frontend position updated here
            player.onKeyDown(keyCode, moveSpeed, event); // backend position is updated here
        }

        playerX = player.getPlayerX();
        playerY = player.getPlayerY();

        // mage collision check
        if (Math.abs(playerX - mageX) < 100 && Math.abs(playerY - mageY) < 60) {
            player.takeDamage();
        }

        // knight collision check
        if (Math.abs(playerX - knightX) < 100 && Math.abs(playerY - knightY) < 60) {
            player.takeDamage();
        }

        // damage powerup collision check
        if (Math.abs(playerX - damagePowerupX) < 100 && Math.abs(playerY - damagePowerupY) < 60) {
            player.activateAttackPowerup();
            xAttackRange = player.getPlayerXAttackRange();
            yAttackRange = player.getPlayerYAttackRange();
            damagePowerupView.remove();
            damagePowerupX = 0;
            damagePowerupY = 0;
        }

        if (playerX - moveSpeed <= 0) {
            if (playerY >= 1420) {
                Intent game = new Intent(GameScreen3.this, EndScreen.class);
                startActivity(game);
            }
        }
        playerView.updatePosition(playerX, playerY);
        //        RelativeLayout gameLayout = findViewById(R.id.gameLayout);
        //        int pixel2;
        //        Drawable backgroundDrawable = gameLayout.getBackground();
        //        BitmapDrawable bitmapDrawable = (BitmapDrawable) backgroundDrawable;
        //        Bitmap backgroundBitmap = bitmapDrawable.getBitmap();
        //        int x = playerX;
        //        int y = playerY;
        //        pixel2 = backgroundBitmap.getPixel(x, y);
        //        Log.d("pixel", "color codes" + pixel2);

        // Log.d("currLocation", "x:" + playerX + "y: " + playerY);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // Logic to stop player movement when a key is released
        // You may need to add logic here to stop the player's movement.
        return true;
    }

}
