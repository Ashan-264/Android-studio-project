package ViewModel;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PlayerView extends RelativeLayout {

    private ImageView playerImage;
    private float x, y;

    public PlayerView(Context context, float x, float y, String spriteName) {
        super(context);
        this.x = x;
        this.y = y;

        playerImage = new ImageView(context);
        if (spriteName.equals("Buzz")) {
            playerImage.setImageResource(R.drawable.buzz2);
        } else if (spriteName.equals("Wizard")) {
            playerImage.setImageResource(R.drawable.purple_wizard);
        } else if (spriteName.equals("Archer")) {
            playerImage.setImageResource(R.drawable.green_archer);
        }

        // Set the initial size of the ImageView directly (in pixels)
        int imageWidth = 300; // Set your desired width in pixels
        int imageHeight = 300; // Set your desired height in pixels
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageHeight);


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
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) playerImage.getLayoutParams();
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
