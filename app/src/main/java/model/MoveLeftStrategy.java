package model;

public class MoveLeftStrategy implements PlayerMovement {
    @Override
    public void move(Player player, int moveSpeed) {
        player.moveLeft(moveSpeed);
    }
}
