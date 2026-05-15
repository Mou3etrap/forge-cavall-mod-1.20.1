package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.EnumSet;

public class StalkPreyGoal extends Goal {
    private final CavallCreature self;
    private final double stalkingSpeed;

    public StalkPreyGoal(
            CavallCreature self,
            double stalkingSpeed
    ) {
        this.self = self;
        this.stalkingSpeed = stalkingSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (self.isSleeping() || self.isFleeing()) {
            return false; // if the animal is sleeping or fleeing, it cannot stalk prey
        } else {
            LivingEntity preyTarget = self.getTarget();
            return true; //placeholder
            //return preyTarget != null && preyTarget.isAlive() && Fox.STALKABLE_PREY.test(preyTarget) && Fox.this.distanceToSqr(preyTarget) > 36.0D && !Fox.this.isCrouching() && !Fox.this.isInterested() && !Fox.this.jumping;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        //self.setSitting(false);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        LivingEntity preyTarget = self.getTarget();
        if (preyTarget != null) {
            // if
            //self.setIsInterested(true);
            //self.setIsCrouching(true);
            self.getNavigation().stop();
            self.getLookControl().setLookAt(preyTarget, (float)self.getMaxHeadYRot(), (float)self.getMaxHeadXRot());
        } else {
            //
            //self.setIsInterested(false);
            //self.setIsCrouching(false);
        }

    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        LivingEntity livingentity = self.getTarget();
        if (livingentity != null) {
            self.getLookControl().setLookAt(livingentity, (float)self.getMaxHeadYRot(), (float)self.getMaxHeadXRot());
            if (self.distanceToSqr(livingentity) <= 36.0D) {
                //self.setIsInterested(true);
                //self.setIsCrouching(true);
                self.getNavigation().stop();
            } else {
                self.getNavigation().moveTo(livingentity, stalkingSpeed);
            }

        }
    }
}
