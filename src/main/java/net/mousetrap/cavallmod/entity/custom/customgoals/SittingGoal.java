package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.EnumSet;

public class SittingGoal extends Goal {

    private final CavallCreature mob;
    private final int sitMinTicks;
    private final int sitMaxTicks;
    private int sitTimer = 0;

    public SittingGoal(CavallCreature mob, int sitMinTicks, int sitMaxTicks) {
        this.mob = mob;
        this.sitMinTicks = sitMinTicks;
        this.sitMaxTicks = sitMaxTicks;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (mob.isBaby()) return false;
        if (!mob.isIdling()) return false;
        //if (mob.isSitting()) return false;
        // random chance to start sitting each time canUse is checked
        return mob.getRandom().nextFloat() < 0.002f;
    }

    @Override
    public boolean canContinueToUse() {
        if (!mob.isIdling()) return false; // stand up if state changes
        sitTimer--;
        return sitTimer > 0;
    }

    @Override
    public void start() {
        sitTimer = sitMinTicks + mob.getRandom().nextInt(sitMaxTicks - sitMinTicks + 1);
        //mob.setSittingTo(true);
        mob.getNavigation().stop();
        mob.refreshDimensions(); // update hitbox
    }

    @Override
    public void stop() {
        //mob.setSittingTo(false);
        mob.refreshDimensions(); // restore hitbox
    }
}

