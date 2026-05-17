package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.*;
import java.util.stream.Collectors;

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
    private Vec3 wanderDirection = Vec3.ZERO;
    private int wanderChangeTick = 0;
    private final Map<UUID, Vec3> lastNeighborPositions = new HashMap<>();

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
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (mob.isBaby()) return false;

        List<Animal> neighbors = mob.level().getEntitiesOfClass(
                Animal.class,
                mob.getBoundingBox().inflate(flockRadius),
                a -> a != mob && a.getClass() == mob.getClass()
        );
        return !neighbors.isEmpty(); // returns false if there are no neighbors nearby
    }
    @Override
    public boolean canContinueToUse() {
        return !mob.level().getEntitiesOfClass(
                Animal.class,
                mob.getBoundingBox().inflate(flockRadius * 2), // match the escape hatch radius
                a -> a != mob && a.getClass() == mob.getClass()
        ).isEmpty();
    }

    @Override
    public void tick() {
        Level level = mob.level();
        if (mob.tickCount % updateInterval != 0) return;

        List<Animal> neighbors = level.getEntitiesOfClass(
                Animal.class,
                mob.getBoundingBox().inflate(flockRadius * 2),
                a -> a != mob && a.getClass() == mob.getClass()
        );

        if (neighbors.isEmpty()) return;

        // --- compute flock center ---
        Vec3 flockCenter = Vec3.ZERO;
        for (Animal member : neighbors) {
            flockCenter = flockCenter.add(member.position());
        }
        flockCenter = flockCenter.scale(1.0 / neighbors.size());
        double distToCenter = mob.position().distanceTo(flockCenter);

        // --- CASE 1: fully separated, hard return ---
        if (distToCenter > flockRadius) {
            mob.getNavigation().moveTo(flockCenter.x, mob.getY(), flockCenter.z, speed * 1.5);
            lastMoveVec = flockCenter.subtract(mob.position()).normalize().scale(speed);
            return;
        }

        // --- refresh wander direction periodically ---
        if (mob.tickCount >= wanderChangeTick || wanderDirection.equals(Vec3.ZERO)) {
            wanderDirection = new Vec3(
                    mob.getRandom().nextDouble() - 0.5,
                    0,
                    mob.getRandom().nextDouble() - 0.5
            ).normalize();
            // pick a new wander direction every 3-5 seconds
            wanderChangeTick = mob.tickCount + 60 + mob.getRandom().nextInt(40);
        }

// --- CASE 2: no close neighbors, just wander ---
        List<Animal> closeNeighbors = neighbors.stream()
                .filter(a -> a.distanceToSqr(mob) < flockRadius * flockRadius)
                .toList();

        if (closeNeighbors.isEmpty()) {
            mob.getNavigation().moveTo(
                    mob.getX() + wanderDirection.x * 8,
                    mob.getY(),
                    mob.getZ() + wanderDirection.z * 8,
                    speed
            );
            return;
        }

// --- CASE 3: normal boids ---
        Vec3 separation = Vec3.ZERO;
        Vec3 alignment = Vec3.ZERO;
        Vec3 cohesion = Vec3.ZERO;

        for (Animal neighbor : closeNeighbors) {
            Vec3 toNeighbor = neighbor.position().subtract(mob.position());
            double distance = toNeighbor.length();

            if (distance < 4.0 && distance > 0.001) {
                separation = separation.subtract(toNeighbor.normalize().scale(1.0 / distance));
            }

            Vec3 lastPos = lastNeighborPositions.getOrDefault(neighbor.getUUID(), neighbor.position());
            Vec3 estimatedVelocity = neighbor.position().subtract(lastPos);
            alignment = alignment.add(estimatedVelocity);

            cohesion = cohesion.add(toNeighbor);
            lastNeighborPositions.put(neighbor.getUUID(), neighbor.position());
        }

        lastNeighborPositions.keySet().retainAll(
                closeNeighbors.stream().map(Animal::getUUID).collect(Collectors.toSet())
        );

        int count = closeNeighbors.size();
        alignment = alignment.scale(1.0 / count);
        cohesion = cohesion.scale(1.0 / count);

        Vec3 moveVec = separation.scale(separationWeight)
                .add(alignment.scale(alignmentWeight))
                .add(cohesion.scale(cohesionWeight))
                .add(wanderDirection.scale(randomnessWeight)); // wander always contributes

        double smoothing = 0.2;
        moveVec = lastMoveVec.scale(1 - smoothing).add(moveVec.scale(smoothing));
        lastMoveVec = moveVec;

// floor: if boids vector is too weak, just use wander direction
        if (moveVec.lengthSqr() < 0.0001) {
            moveVec = wanderDirection.scale(speed);
        }

        moveVec = moveVec.normalize().scale(speed);
        mob.getNavigation().moveTo(
                mob.getX() + moveVec.x * 6,
                mob.getY(),
                mob.getZ() + moveVec.z * 6,
                speed
        );
    }
}
