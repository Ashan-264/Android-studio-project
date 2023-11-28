package model;

public class HealthBoost extends AbilityDecorator {
    public HealthBoost(PlayerAbility decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void useAbility() {
        super.useAbility();
        boolean HealthBoost = true;
    }
}
