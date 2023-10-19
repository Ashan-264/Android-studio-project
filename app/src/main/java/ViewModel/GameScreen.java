package ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import Model.GameObject;
import Model.Player;

public class GameScreen extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable countdownRunnable;

    private TextView playerScoreText;

    private PlayerView playerView;

    private float playerY, playerX;  //Ashan

    RelativeLayout gameLayout; //Ashan

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

        if (spriteName.equals("Buzz")) {
            playerImage.setImageResource(R.drawable.buzz2);
        } else if (spriteName.equals("Wizard")) {
            playerImage.setImageResource(R.drawable.purple_wizard);
        } else if (spriteName.equals("Archer")) {
            playerImage.setImageResource(R.drawable.green_archer);
        }

        // Display score
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(player.getScore()));
        // Update score every 2 seconds
        keepScore(player);


        //AShan
        // Create red dot
        gameLayout = findViewById(R.id.gameLayout);
        playerView = new PlayerView(this, playerX, playerY,100);
        gameLayout.addView(playerView);



        Button gameButton = findViewById(R.id.finishBtn);

        gameButton.setOnClickListener(v -> {
            handler.removeCallbacks(countdownRunnable);
            Intent game = new Intent(this, GameScreen2.class);
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
        switch (keyCode) {
            case 20:
                playerY += 40;
                break;
            case 19:
                playerY -= 40;
                break;
            case 21:
                playerX -= 40;
                break;
            case 22:
                playerX += 40;
                break;
            default:
                break;
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
