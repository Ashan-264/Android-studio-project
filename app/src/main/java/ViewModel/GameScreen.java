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

public class GameScreen extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable countdownRunnable;

    private TextView playerScoreText;

    private PlayerView playerView;


    private int playerY = 1300, playerX = 520;  //Ashan
    private final int moveSpeed = 40;

    RelativeLayout gameLayout; //Ashan

    Point  screenSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        GameObject gameObject = GameObject.getGameObject();
        Player player = Player.getPlayer();




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
        keepScore(player);

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

    private void keepScore(Player player) {
        handler.postDelayed(countdownRunnable = new Runnable() {
            @Override
            public void run() {
                // Decrement the count by 1
                player.subScore(1);

                // Update the TextView
                playerScoreText.setText("Score: " + Integer.toString(player.getScore()));

                if (player.getScore() > 0) {
                    // Schedule the next decrement after 1 second
                    handler.postDelayed(this, 1000);
                } else {
                    // Count reached 0, you can take further action here
                    Intent game = new Intent(GameScreen.this, EndScreen.class);
                    startActivity(game);
                }
            }
        }, 1000); // Start the countdown after 1 second
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

        player.playerMovement(playerX,playerY,screenSize);
         player.onKeyDown(keyCode,moveSpeed, event);
         playerX = player.getPlayerX();
         playerY = player.getPlayerY();
        if (playerX + moveSpeed >= screenSize.x - screenSize.x / 6) {
            if (playerY > 1460) {
                Intent game = new Intent(this, GameScreen2.class);
                startActivity(game);
            }

        }
        playerView.updatePosition(playerX, playerY);




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
