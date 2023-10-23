package ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import model.GameObject;
import model.Player;

public class GameScreen extends AppCompatActivity implements ScoreObserver{

    private Handler handler = new Handler();
    private Runnable countdownRunnable;

    private TextView playerScoreText;

    private PlayerView playerView;


    private int playerX, playerY;
//    private int playerY = 1300, playerX = 520; //Ashan
    private final int moveSpeed = 40;

    RelativeLayout gameLayout; //Ashan

    Point  screenSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        GameObject gameObject = GameObject.getGameObject();
        Player player = Player.getPlayer();
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



        // Display score
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(player.getScore()));
        // Update score every 2 seconds
        player.startScoring();

        // Get the screen size
        Display display = getWindowManager().getDefaultDisplay();
        screenSize = new Point();
        display.getSize(screenSize);

        playerX = 520;
        playerY = 500;
        //AShan
        // Create character
        gameLayout = findViewById(R.id.gameLayout);
        playerView = new PlayerView(this, playerX, playerY, spriteName);
        gameLayout.addView(playerView);




//        Button gameButton = findViewById(R.id.finishBtn);
//
//        gameButton.setOnClickListener(v -> {
//            handler.removeCallbacks(countdownRunnable);
//            Intent game = new Intent(this, GameScreen2.class);
//            startActivity(game);
//        });
    }

//    private void keepScore(Player player) {
//        handler.postDelayed(countdownRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // Decrement the count by 1
//                player.subScore(1);
//
//                // Update the TextView
//                playerScoreText.setText("Score: " + Integer.toString(player.getScore()));
//
//                if (player.getScore() > 0) {
//                    // Schedule the next decrement after 1 second
//                    handler.postDelayed(this, 1000);
//                } else {
//                    // Count reached 0, you can take further action here
//                    Intent game = new Intent(GameScreen.this, EndScreen.class);
//                    startActivity(game);
//                }
//            }
//        }, 1000); // Start the countdown after 1 second
//    }

    @Override
    public void onScoreChanged(int newScore) {
            playerScoreText = (TextView) findViewById(R.id.playerScore);
            playerScoreText.setText("Score: " + Integer.toString(newScore));
    }


    // Handle key events to move the player
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO logic to move the player (remember to check collisions)
        Player player = Player.getPlayer();
//        RelativeLayout gameLayout = findViewById(R.id.gameLayout);
//        int pixel1;
//        Drawable backgroundDrawable = gameLayout.getBackground();
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) backgroundDrawable;
//        Bitmap backgroundBitmap = bitmapDrawable.getBitmap();
//        int x = playerX;
//        int y = playerY;
//
//        pixel1 = backgroundBitmap.getPixel(x, y);
//        int red = Color.red(pixel1);
//        int blue = Color.blue(pixel1);
//        int green = Color.green(pixel1);
//        Log.d("pixel", "color codes:" + Integer.toHexString(pixel1));
        int newX = player.getPlayerX(), newY = player.getPlayerY();
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
            player.playerMovement(playerX,playerY,screenSize); // frontend position is updated here
            player.onKeyDown(keyCode,moveSpeed, event); // backend position is updated here
        }
//        player.playerMovement(playerX,playerY,screenSize); // frontend position is updated here
//        player.onKeyDown(keyCode,moveSpeed, event); // backend position is updated here
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
        if (playerX + moveSpeed >= screenSize.x - screenSize.x / 6) {
            if (playerY >= 1420) {
                Intent game = new Intent(this, GameScreen2.class);
                startActivity(game);
            }

        }
        playerView.updatePosition(playerX, playerY);
//        Log.d("currLocation", "x:" + playerX + "y: " + playerY);
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

}
    // Handle key events to move the player
