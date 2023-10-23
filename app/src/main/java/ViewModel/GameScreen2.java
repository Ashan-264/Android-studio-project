package ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
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

public class GameScreen2 extends AppCompatActivity implements ScoreObserver{

    private Handler handler = new Handler();
    private Runnable countdownRunnable;

    private TextView playerScoreText;

    private PlayerView playerView;


    private int playerY = 1320, playerX = 280;  //Ashan

    private final int moveSpeed = 40;

    RelativeLayout gameLayout; //Ashan

    Point screenSize;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen2);

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

        playerX = 250;
        playerY = 500;

        //AShan
        // Create character
        gameLayout = findViewById(R.id.gameLayout2);
        playerView = new PlayerView(this, playerX, playerY, spriteName);
        gameLayout.addView(playerView);



        // Display score
        playerScoreText = (TextView) findViewById(R.id.playerScore);
        playerScoreText.setText("Score: " + Integer.toString(player.getScore()));
        // Update score every 2 seconds


//        Button gameButton =  findViewById(R.id.finishBtn);
//
//        gameButton.setOnClickListener(v -> {
//            handler.removeCallbacks(countdownRunnable);
//            Intent game = new Intent(this, GameScreen3.class);
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
//                    Intent game = new Intent(GameScreen2.this, EndScreen.class);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO logic to move the player (remember to check collisions)
        Player player = Player.getPlayer();

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
        // top square, vertical passage, horizontal passage, vertical passage, horizontal passage
        if (newX >= 130 && newX <= 610 && newY >= 220 && newY <= 740) {
            legalMove = true;
        } else if (newX >= 410 && newX <= 530 && newY >= 700 && newY <= 1580) {
            legalMove = true;
        } else if (newX >= 410 && newX <= 930 && newY >= 1500 && newY <= 1580) {
            legalMove = true;
        } else if (newX >= 850 && newX <= 930 && newY >= 260 && newY <= 1580) {
            legalMove = true;
        } else if (newX >= 850 && newY >= 260 && newY <= 340) {
            legalMove = true;
        }

        if (legalMove) {
            player.playerMovement(playerX,playerY,screenSize); // frontend position is updated here
            player.onKeyDown(keyCode,moveSpeed, event); // backend position is updated here
        }

//        player.playerMovement(playerX,playerY,screenSize);
//        player.onKeyDown(keyCode,40, event);
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
        if (playerX + moveSpeed >= screenSize.x - screenSize.x / 8) {
            if (playerY <= 340) {
                Intent game = new Intent(this, GameScreen3.class);
                startActivity(game);
            }
        }
        playerView.updatePosition(playerX, playerY);
//        Log.d("position", "x:" + playerX + "y:" + playerY);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // Logic to stop player movement when a key is released
        // You may need to add logic here to stop the player's movement.
        return true;
    }

}
