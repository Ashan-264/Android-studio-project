package ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import Model.GameObject;
import Model.Player;


public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        GameObject gameObject = GameObject.getGameObject();
        Player player = gameObject.getPlayer();

        TextView scoreText= (TextView) findViewById(R.id.scoreText);
        scoreText.setText("Score: " + Integer.toString(player.getScore()));

        Button exitBtn =  findViewById(R.id.exitButton2);
        exitBtn.setOnClickListener(v -> {
            finishAffinity();  // Close all activities and exit the app
            System.exit(0);
        });
    }


}