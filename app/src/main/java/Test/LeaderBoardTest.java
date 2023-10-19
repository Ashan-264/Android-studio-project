package Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import android.widget.TextView;

import org.junit.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Model.GameObject;
import Model.Player;
import ViewModel.R;

public class LeaderBoardTest {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;

    private GameObject testGame = GameObject.getGameObject();


    @Before
    public void initialization() {
        player1= Player.getPlayer();
        player1.setPlayer("George Burdell", "Burdell", 3);
        player1.subScore(5);
        player2 = Player.getPlayer();
        player2.setPlayer("Ash", "Ash", 90);
        player2.subScore(6);
        player3 = Player.getPlayer();
        player3.setPlayer("Archer", "Archer", 30);
        player3.subScore(4);
        player4 =Player.getPlayer();
        player4.setPlayer("Grover", "Grover", 2);
        player4.subScore(7);
        player5 = Player.getPlayer();
        player5.setPlayer("Rager", "Rager", 26);
        player5.subScore(3);

        testGame.configGame(player1,"easy");
        testGame.setLeaderboard();
        testGame.configGame(player2,"easy");
        testGame.setLeaderboard();
        testGame.configGame(player3,"easy");
        testGame.setLeaderboard();
        testGame.configGame(player4,"easy");
        testGame.setLeaderboard();
        testGame.configGame(player5,"easy");
        testGame.setLeaderboard();


    }


    @Test
    public void StorePlayerTest() {
        GameObject gameObject = GameObject.getGameObject();
        Player player = gameObject.getPlayer();
        gameObject.setLeaderboard();

        // Make Leaderboard
        PriorityQueue<Player> sortedLeaderboard = new PriorityQueue<Player>(gameObject.getLeaderboard());

        Player lbPlayer = sortedLeaderboard.poll();
        sortedLeaderboard.poll();
        if (lbPlayer != null) {
            assertEquals("1: Rager - 7 points",("1: " +  lbPlayer.getName() + " - "
                    + Integer.toString(lbPlayer.getScore()) + " points"));
        }

    }
    @Test
    public void CorrectOrder() {
        GameObject gameObject = GameObject.getGameObject();
        Player player = gameObject.getPlayer();
        gameObject.setLeaderboard();

        // Make Leaderboard
        PriorityQueue<Player> sortedLeaderboard = new PriorityQueue<Player>(gameObject.getLeaderboard());

        Player lbPlayer = sortedLeaderboard.poll();
        sortedLeaderboard.poll();
        if (lbPlayer != null) {
            assertEquals("1: Rager - 7 points",("1: " +  lbPlayer.getName() + " - "
                    + Integer.toString(lbPlayer.getScore()) + " points"));
        }
        Player lbPlayer1 = sortedLeaderboard.poll();
        if (lbPlayer1 != null) {
            assertEquals("1: Archer - 6 points",("1: " +  lbPlayer1.getName() + " - "
                    + Integer.toString(lbPlayer1.getScore()) + " points"));
        }
        Player lbPlayer2 = sortedLeaderboard.poll();

        if (lbPlayer2 != null) {
            assertEquals("1: George Burdell - 5 points",("1: " +  lbPlayer2.getName() + " - "
                    + Integer.toString(lbPlayer2.getScore()) + " points"));
        }
        Player lbPlayer3 = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            assertEquals("1: Ash - 4 points",("1: " +  lbPlayer3.getName() + " - "
                    + Integer.toString(lbPlayer3.getScore()) + " points"));
        }
        Player lbPlayer4 = sortedLeaderboard.poll();
        if (lbPlayer != null) {
            assertEquals("1: Grover - 3 points",("1: " +  lbPlayer4.getName() + " - "
                    + Integer.toString(lbPlayer4.getScore()) + " points"));
        }

    }
}
