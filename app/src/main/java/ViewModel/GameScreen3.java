package ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Model.GameObject;
import Model.Player;

public class GameScreen3 extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable countdownRunnable;

    private TextView playerScoreText;

    private PlayerView playerView;


    private int playerY = 200 , playerX = 0;  //Ashan

    RelativeLayout gameLayout; //Ashan

    Point screenSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen3);

        GameObject gameObject = GameObject.getGameObject();
        Player player = gameObject.getPlayer();

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

        playerX = 10;
        playerY = screenSize.y - screenSize.y/4;

        //AShan
        // Create character
        gameLayout = findViewById(R.id.gameLayout3);
        playerView = new PlayerView(this, playerX, playerY, spriteName);
        gameLayout.addView(playerView);




        // Display score
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(player.getScore()));
        // Update score every 2 seconds
        keepScore(player);




        Button gameButton =  findViewById(R.id.finishBtn);

        gameButton.setOnClickListener(v -> {
            handler.removeCallbacks(countdownRunnable);
            Intent game = new Intent(this, EndScreen.class);
            startActivity(game);
        });
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
                    Intent game = new Intent(GameScreen3.this, EndScreen.class);
                    startActivity(game);
                }
            }
        }, 1000); // Start the countdown after 1 second
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO logic to move the player (remember to check collisions)
        Player player = Player.getPlayer();
        player.playerMovement(playerX,playerY,screenSize);
        player.onKeyDown(keyCode,40, event);
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
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
