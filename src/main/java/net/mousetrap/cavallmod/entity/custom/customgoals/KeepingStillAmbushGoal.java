package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.EnumSet;
import java.util.List;


public class KeepingStillAmbushGoal extends Goal {
    private final CavallCreature self;
    private final TagKey<EntityType<?>> preyTag;
    private final int startAmbushingAtThisRadius;
    private final int ambushPossibleRadius;
    // radius around the predator in which they can detect prey
    // and set them into the hidden_ambush flag
    private int timer = 0;
    private int attackTime;
    private LivingEntity target;
    private double leapingSpeed;
    private double stalkingSpeed;
    private boolean ambushStarted;

    public KeepingStillAmbushGoal(
            CavallCreature self,
            TagKey<EntityType<?>> preyTag,
            int ambushPossibleRadius,
            int startAmbushingAtThisRadius,
            double leapingSpeed,
            double stalkingSpeed
    ) {
        this.self = self;
        this.preyTag = preyTag;
        this.ambushPossibleRadius = ambushPossibleRadius;
        this.startAmbushingAtThisRadius = startAmbushingAtThisRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity possibleTarget = this.self.getTarget();
        if (timer <= 0){
            timer = 60; // checks again in 3 seconds if prey is nearby
            // this timer might be allowing them to move in 3 second intervals as well, tbd
        } else {
            --timer;
        }

        if (possibleTarget == null || timer != 0) { // if there is no target or if its not the time to check for one
            return false;
        } else { // if there is a target, determined by the NearestAttackableTargetGoal
            // which of course includes the tags for prey
            this.target = possibleTarget; // pick that one
            return true; // this goal is usable
        }
    }

    @Override
    public boolean canContinueToUse() {
        return self.isAnimalWithCertainTagNearby(ambushPossibleRadius, preyTag);
        // they can continue to use this goal if the prey is still in range
        // no timer bc that would make them stop and start the goal a bunch
    }

    @Override
    public void tick() {
        super.tick();

        if (self.isHiddenAmbushing()) { // reinforcing keeping still so other goals wont take precedence
            self.getNavigation().stop();
            self.setDeltaMovement(Vec3.ZERO);
            self.setSprinting(false);
        }
        List<CavallCreature> herd = self.level().getEntitiesOfClass(
                CavallCreature.class,
                self.getBoundingBox().inflate(ambushPossibleRadius),
                a -> a.getType() == self.getType()
        );

        double bbDSqr = (double)(this.self.getBbWidth() * 2.0F * this.self.getBbWidth() * 2.0F);
        // BbWidth is the bounding box width, so this line is a formula wich calculates a box around the predator
        // and that box is the "possible attack reach distance" if that makes sense
        // bbD is how close the mob needs to be to deal damage

        double distToTargetSqr = this.self.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        // computes square of distance to target. this is easier on the computer for Pythagorean reasons


        double speed = 0; // just a placeholder bc it will be assigned

        // if else statement to set the speed of each member in the herd
        if (distToTargetSqr > bbDSqr && distToTargetSqr <= (startAmbushingAtThisRadius*startAmbushingAtThisRadius)) {
            // if the distance to target is greater than the bounding box attack area
            // and if the dist to target is less than the proper start ambushing radius
            // then start the ambush
            ambushStarted = true;
            for (CavallCreature member : herd) {
                speed = leapingSpeed; // leaping speed
                member.setPursuingTo(true);
                member.setTarget(this.target);
            }
        } else if ((distToTargetSqr > startAmbushingAtThisRadius) && !ambushStarted) {
            for (CavallCreature member : herd) {
                speed = stalkingSpeed; // stalking speed
                member.setPursuingTo(false);
            }
        }
        for (CavallCreature member : herd) {
            // each member will move towards the target depending on their proximity to prey
            member.getNavigation().moveTo(this.target, speed);
            // orders all predators to move to the target at speed
            // avoids obstacles with Mc's pathfinding
        }
        attackTime = Math.max(this.attackTime - 1, 0);
        if (distToTargetSqr < bbDSqr) { // if target is within melee range
            if (attackTime <= 0) { // if the cooldown has reached zero
                attackTime = 20; // reset it
                this.self.setAttackingTo(true);
                this.self.doHurtTarget(this.target); // deal damage
            }
        }


        // set the flag to Hidden_Ambushing
        // then make everyone in the herd also ambush with a for loop

        // if the prey gets close enough to the predators,
        // get the nearest prey as a target, then
        // set everyones tag to Fighting
        // and make Hidden_Ambushing false as well so that the prey can detect them

        // if the group population gets too low, set everyone to flee
    }

    @Override
    public void start() {
        ambushStarted = false;
        this.self.getNavigation().stop();
        this.self.setDeltaMovement(Vec3.ZERO);
        this.self.setSprinting(false);
        super.start();
    }

    @Override
    public void stop() {
        ambushStarted = false;
        this.self.setHiddenAmbushingTo(false);
        super.stop();
    }
}
