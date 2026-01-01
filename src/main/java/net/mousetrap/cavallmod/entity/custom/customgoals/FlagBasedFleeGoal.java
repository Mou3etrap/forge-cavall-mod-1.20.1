package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.mousetrap.cavallmod.entity.CavallCreature;

public class FlagBasedFleeGoal extends PanicGoal {
    private final CavallCreature mob;

    public FlagBasedFleeGoal(
            CavallCreature mob,
            double speed
    ) {
        super(mob, speed);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return mob.isFleeing() && super.canUse();
    }
    @Override
    public boolean canContinueToUse() {
        return mob.isFleeing() && super.canContinueToUse();
    }
}


