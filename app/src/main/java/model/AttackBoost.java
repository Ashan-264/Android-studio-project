package model;


public class AttackBoost extends AbilityDecorator {
    public AttackBoost(PlayerAbility decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void useAbility() {
        super.useAbility();
        boolean AttackBoost = true;
    }
}

