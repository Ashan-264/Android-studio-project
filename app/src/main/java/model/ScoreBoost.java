package model;

public class ScoreBoost extends AbilityDecorator {
    public ScoreBoost(PlayerAbility decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void useAbility() {
        super.useAbility();
        boolean invincibleScore = true;
    }
}

