package ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.PriorityQueue;

import Model.GameObject;
import Model.Player;


public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        GameObject gameObject = GameObject.getGameObject();
        Player player = gameObject.getPlayer();

        gameObject.setLeaderboard();

        // Make Leaderboard
        PriorityQueue<Player> sortedLeaderboard =
                new PriorityQueue<Player>(gameObject.getLeaderboard());

        Player lbPlayer = sortedLeaderboard.poll();
        TextView leaderText;
        if (lbPlayer != null) {
            leaderText = (TextView) findViewById(R.id.leaderBoard1);
            leaderText.setText("1: " +  lbPlayer.getName() + " - "
                    + Integer.toString(lbPlayer.getScore()) + " points");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText= (TextView) findViewById(R.id.leaderBoard2);
            leaderText.setText("2: " +  lbPlayer.getName() + " - "
                    + Integer.toString(lbPlayer.getScore()) + " points");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText= (TextView) findViewById(R.id.leaderBoard3);
            leaderText.setText("3: " +  lbPlayer.getName() + " - "
                    + Integer.toString(lbPlayer.getScore()) + " points");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText= (TextView) findViewById(R.id.leaderBoard4);
            leaderText.setText("4: " +  lbPlayer.getName() + " - "
                    + Integer.toString(lbPlayer.getScore()) + " points");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText= (TextView) findViewById(R.id.leaderBoard5);
            leaderText.setText("5: " +  lbPlayer.getName() + " - "
                    + Integer.toString(lbPlayer.getScore()) + " points");
        }



        // Display score
        TextView scoreText= (TextView) findViewById(R.id.scoreText);
        scoreText.setText("Score: " + Integer.toString(player.getScore()));

        Button restartBtn =  findViewById(R.id.restartButton);
        restartBtn.setOnClickListener(v -> {
            Intent game = new Intent(this, MainActivity.class);
            startActivity(game);
        });

        Button exitBtn =  findViewById(R.id.exitButton);
        exitBtn.setOnClickListener(v -> {
            finishAffinity();  // Close all activities and exit the app
            System.exit(0);
        });
    }


}