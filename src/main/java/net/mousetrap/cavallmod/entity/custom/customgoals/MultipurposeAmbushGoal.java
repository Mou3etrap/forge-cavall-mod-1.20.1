package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.EnumSet;
import java.util.List;

public class MultipurposeAmbushGoal extends Goal {
    private final CavallCreature mob;
    private LivingEntity target;
    private int attackTime;
    private final int detectionDistance;
    private final double leapDist;
    private final double leapingSpeed;
    private final double stalkingSpeed;

    public MultipurposeAmbushGoal(CavallCreature pMob, int detectionDistance, double leapDist, double leapingSpeed, double stalkingSpeed) {
        this.mob = pMob;
        this.detectionDistance = detectionDistance;
        this.leapDist = leapDist;
        this.leapingSpeed = leapingSpeed;
        this.stalkingSpeed = stalkingSpeed;

        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
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
        this.mob.setAttackingTo(false);
        this.mob.setHiddenAmbushingTo(false);
    }

    @Override
    public void start() {
        // set yourself to hidden ambushing
        this.mob.setHiddenAmbushingTo(true);
        // set everybody else to HiddenAmbushing
        List<CavallCreature> herd = mob.level().getEntitiesOfClass(CavallCreature.class, mob.getBoundingBox().inflate(detectionDistance), a -> a.getType() == mob.getType());
        for (CavallCreature member : herd) {
            if (!member.isHiddenAmbushing()){
                member.setHiddenAmbushingTo(true);
            }
        }
        //super.start();
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

        List<CavallCreature> herd = mob.level().getEntitiesOfClass(
                CavallCreature.class,
                mob.getBoundingBox().inflate(detectionDistance),
                a -> a.getType() == mob.getType()
        );

        double bbD = (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F);
        // BbWidth is the bounding box width, so this line is a formula wich calculates a box around the predator
        // and that box is the "possible attack reach distance" if that makes sense
        // bbD is how close the mob needs to be to deal damage
        double d0 = detectionDistance;

        double distSqr = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        // computes square of distance to target. this is easier on the computer for Pythagorean reasons

        double speed = 0.0;
        if ( (distSqr > bbD && distSqr <= (leapDist*leapDist)) ) {
            for (CavallCreature member : herd) { // everyone also attack
                member.getNavigation().moveTo(this.target, speed);
                member.setHiddenAmbushingTo(false);
                speed = leapingSpeed; // leaping speed
                this.mob.setSprintJumpingTo(true);
            }
        } else if (distSqr < detectionDistance) {
            speed = stalkingSpeed; // stalking speed
            this.mob.setSprintJumpingTo(false);
        }

        this.mob.getNavigation().moveTo(this.target, speed);
        // orders the mob to move to the target at speed
        // avoids obstacles with Mc's pathfinding
        this.attackTime = Math.max(this.attackTime - 1, 0);
        if (distSqr < (bbD*bbD)) { // if target is within melee range
            if (this.attackTime <= 0) { // if the cooldown has reached zero
                this.attackTime = 20; // reset it
                this.mob.setAttackingTo(true);
                this.mob.doHurtTarget(this.target); // deal damage
            }
        }
    }
}
