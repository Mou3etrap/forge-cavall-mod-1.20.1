package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.EnumSet;

public class RandomLookAroundWhileSittingGoal extends Goal {

    private final CavallCreature mob;
    private float yRot;
    private int lookTime;

    public RandomLookAroundWhileSittingGoal(CavallCreature mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return mob.isCavallCreatureSitting() && mob.getRandom().nextFloat() < 0.02f;
    }

    @Override
    public boolean canContinueToUse() {
        return mob.isCavallCreatureSitting() && lookTime > 0;
    }

    @Override
    public void start() {
        yRot = mob.getRandom().nextFloat() * 360f;
        lookTime = 20 + mob.getRandom().nextInt(20);
    }

    @Override
    public void tick() {
        lookTime--;
        mob.getLookControl().setLookAt(
                mob.getX() + Math.sin(Math.toRadians(yRot)) * 5,
                mob.getEyeY(),
                mob.getZ() + Math.cos(Math.toRadians(yRot)) * 5
        );
    }
}