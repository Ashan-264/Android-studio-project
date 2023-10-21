package model;
import java.util.ArrayList;

public class LeaderBoard {
    private ArrayList<Player> playerList;
    private static LeaderBoard leaderBoard;
    private LeaderBoard() {
        playerList = new ArrayList<Player>();
    }
    public static LeaderBoard getLeaderboard() {
        if (leaderBoard == null) {
            leaderBoard = new LeaderBoard();
        }
        return leaderBoard;
    }
    public void addPlayer(Player player) {
        playerList.add(player);
    }
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}
