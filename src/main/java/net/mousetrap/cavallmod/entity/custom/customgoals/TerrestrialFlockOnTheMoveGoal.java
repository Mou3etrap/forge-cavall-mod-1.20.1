package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class TerrestrialFlockOnTheMoveGoal extends Goal {

    private final CavallCreature mob;
    private final double speed;

    private final double flockRadius;
    private final double separationWeight;
    private final double cohesionWeight;
    private final double alignmentWeight;
    private final double randomnessWeight;
    private final double globalDirectionWeight;
    // how strongly each individual mob is pulled towards the herd's global direction
    // school of fish: 0.6 - 1.0
    // deer: 0.3 - 0.6

    private int directionChangeInterval;
    private final int minDirChangeTicks;
    private final int maxDirChangeTicks;

    private Vec3 lastMoveVec = Vec3.ZERO;

    public TerrestrialFlockOnTheMoveGoal(
            CavallCreature mob,
            double speed,
            double flockRadius,
            double separationWeight,
            double cohesionWeight,
            double alignmentWeight,
            double randomnessWeight,
            double globalDirectionWeight,
            int minDirChangeTicks,
            int maxDirChangeTicks
    ) {
        this.mob = mob;
        this.speed = speed;
        this.flockRadius = flockRadius;
        this.separationWeight = separationWeight;
        this.cohesionWeight = cohesionWeight;
        this.alignmentWeight = alignmentWeight;
        this.randomnessWeight = randomnessWeight;
        this.globalDirectionWeight = globalDirectionWeight;
        this.minDirChangeTicks = minDirChangeTicks;
        this.maxDirChangeTicks = maxDirChangeTicks;
        this.directionChangeInterval = minDirChangeTicks + (int)(Math.random() * (maxDirChangeTicks - minDirChangeTicks + 1));

        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return mob.isOnTheMove();
        // this mob can use this goal if the "on the move" flag is true
    }

    @Override
    public boolean canContinueToUse() {
        return mob.isOnTheMove();
        // this mob can keep using this goal
        // as long as the "on the move" flag is true
    }

    @Override
    public void tick() {
        Level level = mob.level();

        List<CavallCreature> neighbors = level.getEntitiesOfClass(
                CavallCreature.class,
                mob.getBoundingBox().inflate(flockRadius),
                a -> a != mob && a.getClass() == mob.getClass()
        );

        CavallCreature leader = getLeader(neighbors);

        if (mob == leader) {
            if (leader.getOnTheMoveDirection() == Vec3.ZERO || mob.tickCount % directionChangeInterval == 0) {
                Vec3 newDir = new Vec3(
                        mob.getRandom().nextDouble() - 0.5,
                        0,
                        mob.getRandom().nextDouble() - 0.5
                ).normalize();

                // only leader gets the global direction set
                mob.setOnTheMoveDirection(newDir);

                // changes the direction change interval each time a new direction gets picked
                directionChangeInterval = mob.getRandom().nextInt(maxDirChangeTicks - minDirChangeTicks + 1) + minDirChangeTicks;

            }
        }

        Vec3 separation = Vec3.ZERO;
        Vec3 alignment = Vec3.ZERO;
        Vec3 cohesion = Vec3.ZERO;
        Vec3 globalDirection = leader.getOnTheMoveDirection();

        for (CavallCreature neighbor : neighbors) {
            Vec3 toNeighbor = neighbor.position().subtract(mob.position());
            double dist = toNeighbor.length();

            if (dist > 0.001 && dist < 2.0) {
                separation = separation.subtract(toNeighbor.normalize().scale(1.0 / dist));
            }

            alignment = alignment.add(neighbor.getDeltaMovement());
            cohesion = cohesion.add(toNeighbor);
        }

        int count = neighbors.size();
        if (count > 0) {
            alignment = alignment.scale(1.0 / count);
            cohesion = cohesion.scale(1.0 / count);
        }

        Vec3 random = new Vec3(
                mob.getRandom().nextDouble() - 0.5,
                0,
                mob.getRandom().nextDouble() - 0.5
        ).scale(randomnessWeight);

        Vec3 moveVec = separation.scale(separationWeight)
                .add(alignment.scale(alignmentWeight))
                .add(cohesion.scale(cohesionWeight))
                .add(globalDirection.scale(globalDirectionWeight))
                .add(random);

        // smooth motion
        double smoothing = 0.25;
        moveVec = lastMoveVec.scale(1 - smoothing)
                .add(moveVec.scale(smoothing));
        lastMoveVec = moveVec;

        if (moveVec.lengthSqr() > 0.0001) {
            moveVec = moveVec.normalize().scale(speed);

            mob.getNavigation().moveTo(
                    mob.getX() + moveVec.x * 6,
                    mob.getY(),
                    mob.getZ() + moveVec.z * 6,
                    speed
            );
        }
    }
    // picks leader based on universally unique identifier aka uuid
    private CavallCreature getLeader(List<CavallCreature> neighbors) {
        return neighbors.stream()
                .min(Comparator.comparing(Entity::getUUID))
                .orElse(mob);
    }

}
