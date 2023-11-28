package model;

public class ScorePowerup extends AbilityDecorator {
    public ScorePowerup(PlayerAbility decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void useAbility() {
        super.useAbility();
        boolean WallWalk = true;
    }
}

