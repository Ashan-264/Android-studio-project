package model;

public class MoveDownStrategy implements PlayerMovement {
    @Override
    public void move(Player player, int moveSpeed) {
        player.moveDown(moveSpeed);
    }
}
