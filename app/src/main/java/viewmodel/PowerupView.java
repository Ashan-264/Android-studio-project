package viewmodel;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PowerupView extends RelativeLayout {

        private ImageView playerImage;
        private float x;
        private float y;

        private int screenWidth;
        private int screenHeight;

        public PowerupView(Context context, float x, float y, String spriteName) {
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
            int imageWidth = screenWidth / 10;
            int imageHeight = screenHeight / 10;
            playerImage = new ImageView(context);
            if (spriteName.equals("DamagePowerup")) {
                playerImage.setImageResource(R.drawable.damage_powerup);
            } else if (spriteName.equals("HeartPowerup")) {
                playerImage.setImageResource(R.drawable.heart_powerup);
            } else if (spriteName.equals("ScorePowerup")) {
                playerImage.setImageResource(R.drawable.score_powerup);
            }

            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(imageWidth, imageHeight);

            layoutParams.leftMargin = (int) x;
            layoutParams.topMargin = (int) y;
            playerImage.setLayoutParams(layoutParams);

            // Add the ImageView to the view
            addView(playerImage);
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void remove() {
            // Get the parent view and remove this EnemyView
            ViewGroup parentView = (ViewGroup) getParent();
            if (parentView != null) {
                parentView.removeView(this);
            }
        }
}
