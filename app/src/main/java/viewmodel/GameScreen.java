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

import model.Enemy;
import model.GameObject;
import model.HealthBoost;
import model.Player;
import model.PlayerAbility;
import model.ScorePowerup;

public class GameScreen extends AppCompatActivity implements ScoreObserver, HealthObserver {

    private TextView playerScoreText;
    private TextView playerHealthText;

    private PlayerView playerView;


    private int playerX;
    private int playerY;
    private final int moveSpeed = 40;

    private RelativeLayout gameLayout;

    private Point  screenSize;


    //Enemy variables
    private EnemyView batView;
    private EnemyView mageView;
    private int batX;
    private int batY;
    private int mageX;
    private int mageY;

    // Powerup variables
    private PowerupView scorePowerupView;

    private int scorePowerupX;
    private int scorePowerupY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        GameObject gameObject = GameObject.getGameObject();
        Player player = Player.getPlayer();
        Player.getPlayer().addScoreObserver(this);
        Player.getPlayer().addHealthObserver(this);

         //Ashans change - created new object for decorator pattern

        //create Bat for map 1 factory method
        Map1Bat map1Bat = new Map1Bat();
        Enemy enemy = map1Bat.createEnemy();


        // Display difficulty
        TextView difficultyText = (TextView) findViewById(R.id.difficulty);
        difficultyText.setText(gameObject.getDifficulty());

        // Display player name
        TextView playerNameText = (TextView) findViewById(R.id.gamePlayerName);
        playerNameText.setText(player.getName());

        // Display player Health
        playerHealthText = (TextView) findViewById(R.id.playerHealth);
        playerHealthText.setText("Health: " + Integer.toString(player.getHealth()));
        player.startHealthUpdates();

        // Display player sprite
        String spriteName = player.getSprite().getImageName();


        // Display score
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(player.getScore()));
        // Update score every 2 seconds
        player.startScoring();

        // Get the screen size
        Display display = getWindowManager().getDefaultDisplay();
        screenSize = new Point();
        display.getSize(screenSize);

        playerX = 760;
        playerY = 460;
        //AShan
        // Create character
        gameLayout = findViewById(R.id.gameLayout);
        playerView = new PlayerView(this, playerX, playerY, spriteName);
        gameLayout.addView(playerView);

        // Enemy 1: bat
        batX = 560;
        batY = 1460;

        // Enemy 2: mage
        mageX = 360;
        mageY = 340;

        //AShan
        // Create enemy
        gameLayout = findViewById(R.id.gameLayout);
        batView = new EnemyView(this, batX, batY, "Bat");
        gameLayout.addView(batView);
        batView.startMoving();
        mageView = new EnemyView(this, mageX, mageY, "Mage");
        gameLayout.addView(mageView);
        mageView.startMoving();

        // create score powerup
        scorePowerupX = 120;
        scorePowerupY = 980;

        // create powerup view
        scorePowerupView = new PowerupView(this, scorePowerupX, scorePowerupY, "ScorePowerup");
        gameLayout.addView(scorePowerupView);

    }

    @Override
    public void onScoreChanged(int newScore) {
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(newScore));

        if (newScore <= 0) {
            Intent game = new Intent(GameScreen.this, EndScreen.class);
            startActivity(game);
        }
    }

    public void onHealthChanged(int newHealth) {
        playerHealthText = (TextView) findViewById(R.id.playerHealth);
        playerHealthText.setText("Health: " + Integer.toString(newHealth));

        if (newHealth <= 0) {
            Intent game = new Intent(GameScreen.this, EndScreen.class);
            startActivity(game);
        }
    }


    // Handle key events to move the player
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
           //Ashan's change - key to remove bat
            case KeyEvent.KEYCODE_1:
               // Check for collision with the bat
                if (Math.abs(playerX - batX) < 400 && Math.abs(playerY - batY) < 400) {
                    // Remove the bat from the screen
                    Log.d("Enemy moving", "It works");
                    batView.stopMovingAndRemove();
                    batX = 0;
                    batY=0;
                }

                if (Math.abs(playerX - mageX) < 400 && Math.abs(playerY - mageY) < 400) {
                    // Remove the bat from the screen
                    Log.d("Enemy moving", "It works");
                    mageView.stopMovingAndRemove();
                    mageX = 0;
                    mageY=0;
             }
                break;
            default:
                break;
        }

        boolean legalMove = false;
        // top square, entrance rectangle, vertical passage, horizontal passage
        if (newX >= 320 && newX <= 800 && newY >= 180 && newY <= 700) {
            legalMove = true;
        } else if (newX >= 80 && newX <= 320 && newY >= 420 && newY <= 500) {
            legalMove = true;
        } else if (newX >= 80 && newX <= 160 && newY >= 420 && newY <= 1540) {
            legalMove = true;
        } else if (newX >= 80 && newY >= 1400 && newY <= 1540) {
            legalMove = true;
        }

        if (legalMove) {
            player.playerMovement(playerX, playerY, screenSize); // frontend position updated here
            player.onKeyDown(keyCode, moveSpeed, event); // backend position is updated here
        }

        playerX = player.getPlayerX();
        playerY = player.getPlayerY();

        // bat collision check
        if (Math.abs(playerX - batX) < 100 && Math.abs(playerY - batY) < 60) {
            player.takeDamage();
        }

        // mage collision check
        if (Math.abs(playerX - mageX) < 100 && Math.abs(playerY - mageY) < 60) {
            player.takeDamage();
        }

        // score powerup collision check
        if (Math.abs(playerX - scorePowerupX) < 100 && Math.abs(playerY - scorePowerupY) < 60) {
            player.activateScorePowerup();
            scorePowerupView.remove();
            scorePowerupX = 0;
            scorePowerupY = 0;
        }

        if (playerX + moveSpeed >= screenSize.x - screenSize.x / 6) {
            if (playerY >= 1420) {
                Intent game = new Intent(this, GameScreen2.class);
                startActivity(game);
            }

        }
        playerView.updatePosition(playerX, playerY);
        //Log.d("currLocation", "x:" + playerX + "y: " + playerY);
        // top left of yellow box is 320, 180
        // top of passage entrance 320, 420
        // bottom of passage entrance 320, 500
        // bottom left of yellow box is 320, 700

        // top left of passage 80, 420
        // bottom left of vertical passage 80, 1380
        // bottom left of path 80, 1540
        // right edge of passage 160, 1380
        // bottom edge of passage 160, 1540

        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // Logic to stop player movement when a key is released
        // You may need to add logic here to stop the player's movement.
        return true;
    }

    public void removeEnemy(EnemyView enemyview) {
        // Remove the bat view from the layout
        gameLayout.removeView(enemyview);
    }



    }
// Handle key events to move the player
