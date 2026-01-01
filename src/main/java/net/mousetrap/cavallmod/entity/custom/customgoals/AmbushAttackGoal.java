package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class AmbushAttackGoal extends Goal {
    private final Mob mob;
    private LivingEntity target;
    private int attackTime;
    private int detectionDistance;

    public AmbushAttackGoal(Mob pMob, int detectionDistance) {
        this.mob = pMob;
        this.detectionDistance = detectionDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity == null) { // if there is no target
            return false;
        } else { // if there is a target, determined by the NearestAttackableTargetGoal
            // which of course includes the tags for prey
            this.target = livingentity; // pick that one
            return true; // this goal is usable
        }
    }
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        if (!this.target.isAlive()) {
            return false; // if the target died, you cant keep using this method
        } else if (this.mob.distanceToSqr(this.target) > (detectionDistance^2)) { // squared due to distanceToSQR
            return false; // if the target is too far away, you cant attack it
        } else {
            return !this.mob.getNavigation().isDone() || this.canUse();
            // true if the mob is still moving along its navigation path aka stalking mode
            // or if the normal conditons for starti
        }
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.target = null;
        this.mob.getNavigation().stop();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        // makes its head look at target
        double d0 = (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F);
        // BbWidth is the bounding box width, so this line is a formula wich calculates a box around the predator
        // and that box is the "possible attack reach distance" if that makes sense
        // d0 is how close the mob needs to be to deal damage
        double d1 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        // computes square of distance to target. this is easier on the computer for Pythagorean reasons
        double d2 = 0.8D;
        if (d1 > d0 && d1 < 16.0D) {

            d2 = 1.33D;
        } else if (d1 < 225.0D) {
            d2 = 0.6D;
        }

        this.mob.getNavigation().moveTo(this.target, d2);
        // orders the mob to move to the target at speed d2
        // avoids obstacles with Mc's pathfinding
        this.attackTime = Math.max(this.attackTime - 1, 0);
        if (!(d1 > d0)) { // if target is within melee range
            if (this.attackTime <= 0) { // if the cooldown has reached zero
                this.attackTime = 20; // reset it
                this.mob.doHurtTarget(this.target); // deal damage
            }
        }
    }
}
