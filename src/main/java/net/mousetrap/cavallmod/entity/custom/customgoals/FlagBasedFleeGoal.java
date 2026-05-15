package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.List;

public class FlagBasedFleeGoal extends PanicGoal {
    private final CavallCreature mob;
    private final TagKey<EntityType<?>> predatorTag;
    private final int predatorRadius;
    private double horzFleeingDist;
    private double vertFleeingDist;

    public FlagBasedFleeGoal(CavallCreature mob, double speed, TagKey<EntityType<?>> predatorTag, int predatorRadius, double horzFleeingDist, double vertFleeingDist){
        super(mob, speed);
        this.mob = mob;
        this.predatorTag = predatorTag;
        this.predatorRadius = predatorRadius;
        this.horzFleeingDist = horzFleeingDist;
        this.vertFleeingDist = vertFleeingDist;
    }

    public Vec3 getFleeTarget(CavallCreature mob, double horzFleeingDist, double vertFleeingDist) {
        Vec3 mobPos = mob.position();
        Entity predator = this.mob.returnEntityWithCertainTagNearby(predatorRadius, predatorTag);
        Vec3 predatorPos = predator.position();

        // Compute direction vector from predator to mob
        Vec3 fleeDir = computeFleeVector(mob, predatorRadius, predatorTag);

        // Randomize a bit to avoid straight lines
        double randX = (mob.getRandom().nextDouble() - 0.5) * 2.0;
        double randZ = (mob.getRandom().nextDouble() - 0.5) * 2.0;

        Vec3 target = mobPos.add(
                fleeDir.x * horzFleeingDist + randX,
                0,
                fleeDir.z * vertFleeingDist + randZ
        );
        // Check vertical limits
        target = new Vec3(target.x, mobPos.y + mob.getRandom().nextDouble() * vertFleeingDist, target.z);
        return target;
    }
    @Override
    public void tick() {
        // Find a nearby predator
        Entity predator = mob.returnEntityWithCertainTagNearby(predatorRadius, predatorTag);
        if (predator == null) {
            mob.setFleeingTo(false);
            return; // no predator nearby, nothing to do
        }
        // Get a fleeing target vector
        Vec3 fleeTarget = getFleeTarget(mob, horzFleeingDist, vertFleeingDist);

        // If a valid target was computed, tell the mob to move there
        if (fleeTarget != null) {
            mob.getNavigation().moveTo(fleeTarget.x, fleeTarget.y, fleeTarget.z, this.speedModifier);
        }
    }

    @Override
    public boolean canUse() {
        return mob.isFleeing();
    }
    @Override
    public boolean canContinueToUse() {
        return mob.isFleeing() && mob.isAnimalWithCertainTagNearby(predatorRadius, predatorTag);
    }

    public static Vec3 computeFleeVector(CavallCreature mob, double radius, TagKey<EntityType<?>> predatorTag) {
        List<LivingEntity> predators = mob.level().getEntitiesOfClass(
                LivingEntity.class,
                mob.getBoundingBox().inflate(radius),
                e -> e.getType().is(predatorTag)
        );

        if (predators.isEmpty()) return Vec3.ZERO;

        Vec3 flee = Vec3.ZERO;

        for (LivingEntity predator : predators) {
            Vec3 away = mob.position().subtract(predator.position());
            double dist = away.length();

            if (dist > 0.01) {
                flee = flee.add(away.normalize().scale(1.0 / dist)); // weighted by proximity
            }
        }

        return flee.normalize();
    }

}


