package viewmodel;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class EnemyView extends RelativeLayout {

    private ImageView playerImage;
    private float x;
    private float y;

    private int screenWidth;
    private int screenHeight;

    private Handler handler = new Handler();

    private boolean isMoving = false;

    private int offset = 0;
    private int increment = 0;
    private int direction;
    private int speed = 5;

    public EnemyView(Context context, float x, float y, String spriteName) {
        super(context);
        this.x = x;
        this.y = y;

        // Get screen dimensions
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        int imageWidth = 0;
        int imageHeight = 0;
        playerImage = new ImageView(context);
        if (spriteName.equals("Bat")) {
            playerImage.setImageResource(R.drawable.bat);
            imageWidth = screenWidth / 15;
            imageHeight = screenHeight / 15;
            this.direction = 1;
            this.speed = 10;
        } else if (spriteName.equals("Ghost")) {
            playerImage.setImageResource(R.drawable.ghost);
            imageWidth = screenWidth / 10;
            imageHeight = screenHeight / 10;
            this.direction = 0;
            this.speed = 4;
        } else if (spriteName.equals("Mage")) {
            playerImage.setImageResource(R.drawable.mage);
            imageWidth = screenWidth / 15;
            imageHeight = screenHeight / 15;
            this.direction = 1;
        } else if (spriteName.equals("Knight")) {
            playerImage.setImageResource(R.drawable.knight);
            imageWidth = screenWidth / 10;
            imageHeight = screenHeight / 10;
            this.direction = 2;
            this.speed = 3;
        }

        // Set the initial size of the ImageView directly (in pixels)
        // int imageWidth = screenWidth / 15; // Set your desired width in pixels
        // int imageHeight = screenHeight / 15; // Set your desired height in pixels
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(imageWidth, imageHeight);


        layoutParams.leftMargin = (int) x;
        layoutParams.topMargin = (int) y;
        playerImage.setLayoutParams(layoutParams);

        // Add the ImageView to the view
        addView(playerImage);
    }

    public void updatePosition(float newX, float newY) {
        x = newX;
        y = newY;

        // Update the position of the ImageView
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams) playerImage.getLayoutParams();
        layoutParams.leftMargin = (int) x;
        layoutParams.topMargin = (int) y;
        playerImage.setLayoutParams(layoutParams);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void startMoving() {
        if (!isMoving) {
            isMoving = true;
            handler.postDelayed(enemyRunnable, 50); // 1000 milliseconds = 1 second
        }
    }

    private Runnable enemyRunnable = new Runnable() {
        @Override
        public void run() {
            moveEnemy();
            handler.postDelayed(this, 50); // Post this Runnable again after 1 second
        }
    };

    private void moveEnemy() {
        if (offset <= 0) {
            increment = speed;
        } else if (offset >= 175) {
            increment = -1 * speed;
        }

        offset += increment;

        if (direction == 0) {
            x += increment;
        } else if (direction == 1) {
            y += increment;
        } else if (direction == 2) {
            x += increment;
            y += increment;
        }

        updatePosition(x, y);
    }

    public void stopMovingAndRemove() {
        if (isMoving) {
            handler.removeCallbacks(enemyRunnable);
            isMoving = false;
            // Get the parent view and remove this EnemyView
            ViewGroup parentView = (ViewGroup) getParent();
            if (parentView != null) {
                parentView.removeView(this);
            }
        }
    }
}

