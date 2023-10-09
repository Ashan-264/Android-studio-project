package ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        Button exitBtn =  findViewById(R.id.exitButton2);
        exitBtn.setOnClickListener(v -> {
            finishAffinity();  // Close all activities and exit the app
            System.exit(0);
        });
    }


}