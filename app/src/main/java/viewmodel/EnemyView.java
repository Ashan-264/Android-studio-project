package viewmodel;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class EnemyView extends RelativeLayout {

    private ImageView playerImage;
    private float x;
    private float y;

    private int screenWidth;
    private int screenHeight;

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

        playerImage = new ImageView(context);
        if (spriteName.equals("Bat")) {
            playerImage.setImageResource(R.drawable.bat);
        } else if (spriteName.equals("Ghost")) {
            playerImage.setImageResource(R.drawable.ghost);
        } else if (spriteName.equals("Mage")) {
            playerImage.setImageResource(R.drawable.mage); }
            else if (spriteName.equals("Knight")) {
                playerImage.setImageResource(R.drawable.knight);
        }

        // Set the initial size of the ImageView directly (in pixels)
        int imageWidth = screenWidth / 10; // Set your desired width in pixels
        int imageHeight = screenHeight / 10; // Set your desired height in pixels
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
}

