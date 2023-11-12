package viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.PriorityQueue;

import model.GameObject;
import model.LeaderBoard;
import model.Player;


public class EndScreenLose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen_lose);

        GameObject gameObject = GameObject.getGameObject();
        Player player = Player.getPlayer();

        player.stopScoring();

        if (!player.onLeaderboard()) {
            LeaderBoard.getLeaderboard().addPlayer(player.copy());
            player.setAddedToLeaderboard();
        }

        // Make Leaderboard
        PriorityQueue<Player> sortedLeaderboard =
                new PriorityQueue<Player>(LeaderBoard.getLeaderboard().getPlayerList());

        SimpleDateFormat format = new SimpleDateFormat("hh:mm MM/dd/yy");

        Player lbPlayer = sortedLeaderboard.poll();
        TextView leaderText;
        if (lbPlayer != null) {
            leaderText = (TextView) findViewById(R.id.leaderBoard1);
            leaderText.setText(Integer.toString(lbPlayer.getScore()) + " - " +  lbPlayer.getName()
                    + " (" + format.format(lbPlayer.getDate()) + ")");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText = (TextView) findViewById(R.id.leaderBoard2);
            leaderText.setText(Integer.toString(lbPlayer.getScore()) + " - " +  lbPlayer.getName()
                    + " (" + format.format(lbPlayer.getDate()) + ")");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText = (TextView) findViewById(R.id.leaderBoard3);
            leaderText.setText(Integer.toString(lbPlayer.getScore()) + " - " +  lbPlayer.getName()
                    + " (" + format.format(lbPlayer.getDate()) + ")");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText = (TextView) findViewById(R.id.leaderBoard4);
            leaderText.setText(Integer.toString(lbPlayer.getScore()) + " - " +  lbPlayer.getName()
                    + " (" + format.format(lbPlayer.getDate()) + ")");
        }

        lbPlayer = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            leaderText = (TextView) findViewById(R.id.leaderBoard5);
            leaderText.setText(Integer.toString(lbPlayer.getScore()) + " - " +  lbPlayer.getName()
                    + " (" + format.format(lbPlayer.getDate()) + ")");
        }



        // Display score
        TextView scoreText = (TextView) findViewById(R.id.scoreText);
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