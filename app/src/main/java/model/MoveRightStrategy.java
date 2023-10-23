package model;

public class MoveRightStrategy implements PlayerMovement {
    @Override
    public void move(Player player, int moveSpeed) {
        player.moveRight(moveSpeed);
    }
}
