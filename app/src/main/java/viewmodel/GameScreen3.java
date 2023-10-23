package viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import model.GameObject;
import model.Player;

public class GameScreen3 extends AppCompatActivity implements ScoreObserver {

    private Handler handler = new Handler();
    private Runnable countdownRunnable;

    private TextView playerScoreText;

    private PlayerView playerView;


    private int playerY = 1300;
    private int playerX = 200;  //Ashan

    private final int moveSpeed = 40;

    private RelativeLayout gameLayout; //Ashan

    private Point screenSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen3);

        GameObject gameObject = GameObject.getGameObject();
        Player player = gameObject.getPlayer();
        Player.getPlayer().addScoreObserver(this);

        // Display difficulty
        TextView difficultyText = (TextView) findViewById(R.id.difficulty);
        difficultyText.setText(gameObject.getDifficulty());

        // Display player name
        TextView playerNameText = (TextView) findViewById(R.id.gamePlayerName);
        playerNameText.setText(player.getName());

        // Display player Health
        TextView playerHealthText = (TextView) findViewById(R.id.playerHealth);
        playerHealthText.setText("Health: " + Integer.toString(player.getHealth()));

        // Display player sprite
        ImageView playerImage = (ImageView) findViewById(R.id.playerImage);
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




        // Display score
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(player.getScore()));

    }



    @Override
    public void onScoreChanged(int newScore) {
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(newScore));

        if (newScore == 0) {
            Intent game = new Intent(GameScreen3.this, EndScreen.class);
            startActivity(game);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO logic to move the player (remember to check collisions)
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