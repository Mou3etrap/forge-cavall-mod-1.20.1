package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
public class FlockingGoal extends Goal {

    private final Animal mob;
    private final double speed;
    // speed while flocking
    // normal: 1
    // skittish: greater than one
    // sluggish: less than one

    private final double flockRadius;
    // how far the mob considers neighbors
    // how far away other mobs must be to consider them in the flock
    // small: 6-10
    // medium: 10-16

    private final double separationWeight;
    // how strongly mobs keep from crowding each other
    // tight herd: 1-1.3
    // spacious: 1.5-2
    private final double cohesionWeight;
    // how much mobs are pulled towards the group
    // loose: 0.4-0.6
    // tight: 0.6-1
    private final double alignmentWeight;
    // how strongly the mobs face the same direction
    // schooling like fish: 1
    // individualistic: lower than one
    private final double randomnessWeight;
    // how much unpredictable wandering is added
    // subtle: 0.1-0.3
    // wilder: 0.3+
    private final double maxFlockDistance;
    // how far the mob can roam before being forced back to the center
    // always SMALLER than flockRadius
    private final double returnForceMultiplier;
    // how strongly the mobs are forced to return
    // to the center of the flock if they are roaming too far
    // gentle: 0.5 - 0.7
    // firm: 0.8 - 1.2
    private final int updateInterval; // ticks between updates

    private Vec3 lastMoveVec = Vec3.ZERO;
    private Vec3 cachedRandomVec = Vec3.ZERO;

    public FlockingGoal(
            Animal mob,
            double speed,
            double flockRadius,
            double separationWeight,
            double cohesionWeight,
            double alignmentWeight,
            double randomnessWeight,
            double maxFlockDistance,
            double returnForceMultiplier,
            int updateInterval
    ) {
        this.mob = mob;
        this.speed = speed;
        this.flockRadius = flockRadius;
        this.separationWeight = separationWeight;
        this.cohesionWeight = cohesionWeight;
        this.alignmentWeight = alignmentWeight;
        this.randomnessWeight = randomnessWeight;
        this.maxFlockDistance = maxFlockDistance;
        this.returnForceMultiplier = returnForceMultiplier;
        this.updateInterval = updateInterval;
    }

    @Override
    public boolean canUse() {
        if (mob.isBaby()) return false;
        if (mob.tickCount % updateInterval != 0) return false;

        List<Animal> neighbors = mob.level().getEntitiesOfClass(
                Animal.class,
                mob.getBoundingBox().inflate(flockRadius),
                a -> a != mob && a.getClass() == mob.getClass()
        );
        return !neighbors.isEmpty(); // returns false if there are no neighbors nearby
    }

    @Override
    public void tick() {
        Level level = mob.level();

        List<Animal> neighbors = level.getEntitiesOfClass(
                Animal.class,
                mob.getBoundingBox().inflate(flockRadius),
                a -> a != mob && a.getClass() == mob.getClass()
        );

        if (neighbors.isEmpty()) return;

        Vec3 separation = Vec3.ZERO;
        Vec3 alignment = Vec3.ZERO;
        Vec3 cohesion = Vec3.ZERO;
        Vec3 flockCenter = Vec3.ZERO;


        for (Animal neighbor : neighbors) {
            Vec3 toNeighbor = neighbor.position().subtract(mob.position());
            double distance = toNeighbor.length();

            if (distance < 2.0 && distance > 0.001) {
                separation = separation.subtract(
                        toNeighbor.normalize().scale(1.0 / distance)
                );
            }

            alignment = alignment.add(neighbor.getDeltaMovement());
            cohesion = cohesion.add(toNeighbor);
            flockCenter = flockCenter.add(neighbor.position());
        }

        int count = neighbors.size();
        alignment = alignment.scale(1.0 / count);
        cohesion = cohesion.scale(1.0 / count);
        flockCenter = flockCenter.scale(1.0 / count);

        Vec3 toCenter = flockCenter.subtract(mob.position());
        double distanceFromCenter = toCenter.length();

        Vec3 returnForce = Vec3.ZERO;
        boolean returningToFlock = distanceFromCenter > maxFlockDistance;

        if (returningToFlock) {
            returnForce = toCenter.normalize().scale(
                    (distanceFromCenter - maxFlockDistance) * returnForceMultiplier);
            //System.out.println("Returning!");
        }

        if (mob.tickCount % updateInterval == 0 && !returningToFlock) {
            cachedRandomVec = new Vec3(
                    mob.getRandom().nextDouble() - 0.5,
                    0,
                    mob.getRandom().nextDouble() - 0.5
            ).scale(randomnessWeight);
        }

        Vec3 randomVec = returningToFlock ? Vec3.ZERO : cachedRandomVec;

        Vec3 moveVec = separation.scale(separationWeight)
                .add(alignment.scale(alignmentWeight))
                .add(cohesion.scale(cohesionWeight))
                .add(returnForce)
                .add(randomVec);

        double smoothing = 0.2;
        moveVec = lastMoveVec.scale(1 - smoothing).add(moveVec.scale(smoothing));
        lastMoveVec = moveVec;


        if (moveVec.lengthSqr() > 0.0001) {
            moveVec = moveVec.normalize().scale(speed);

            mob.getNavigation().moveTo(
                    mob.getX() + moveVec.x,
                    mob.getY(),
                    mob.getZ() + moveVec.z,
                    speed
            );
        }
    }
}
