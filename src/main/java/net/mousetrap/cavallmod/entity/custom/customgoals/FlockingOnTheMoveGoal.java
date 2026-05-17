package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.*;

public class FlockingOnTheMoveGoal extends Goal {

    private final CavallCreature mob;
    private final double speed;
    private final double rejoinFlockRadius;
    private final double alignmentWeight;
    private final double randomnessWeight;
    private final int changeDirectionTicks;

    private int directionRefreshTimer = 0;
    private Vec3 cachedRandomOffset = Vec3.ZERO;
    private int randomOffsetRefreshTimer = 0;
    private static final int randomOffsetRefreshInterval = 60; // refresh every 3 seconds

    private static final Map<Class<?>, Vec3> flockDirections = new HashMap<>();

    public FlockingOnTheMoveGoal(CavallCreature mob, double speed, double rejoinFlockRadius,
                                 double alignmentWeight, double randomnessWeight, int changeDirectionTicks) {
        this.mob = mob;
        this.speed = speed;
        this.rejoinFlockRadius = rejoinFlockRadius;
        this.alignmentWeight = alignmentWeight;
        this.randomnessWeight = randomnessWeight;
        this.changeDirectionTicks = changeDirectionTicks;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    private List<? extends CavallCreature> getFlockMembers() {
        return mob.getNeighbors(mob, rejoinFlockRadius, mob.getClass());
    }

    private Vec3 getFlockCenter(List<? extends CavallCreature> members) {
        Vec3 center = Vec3.ZERO;
        for (CavallCreature member : members) {
            center = center.add(member.position());
        }
        return center.scale(1.0 / members.size());
    }

    @Override
    public boolean canUse() {
        if (mob.isBaby()) return false;
        //System.out.println(mob.getUUID() + " | CanUse: Am I OTM? " + mob.isOnTheMove());
        if (!mob.isOnTheMove()) return false;
        List<? extends CavallCreature> members = mob.getNeighbors(mob, rejoinFlockRadius, mob.getClass());
        //System.out.println(mob.getUUID() + " | FlockingOnTheMoveGoal canUse: members=" + members.size());
        return !members.isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        return mob.isOnTheMove() && !getFlockMembers().isEmpty();
    }

    @Override
    public void start() {
        directionRefreshTimer = 0;
        if (!flockDirections.containsKey(mob.getClass())) {
            Vec3 dir = new Vec3(
                    mob.getRandom().nextDouble() - 0.5,
                    0,
                    mob.getRandom().nextDouble() - 0.5
            ).normalize();
            flockDirections.put(mob.getClass(), dir);
        }
    }

    @Override
    public void stop() {
        if (!mob.isOnTheMove()) {
            flockDirections.remove(mob.getClass());
        }
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        List<? extends CavallCreature> members = getFlockMembers();
        if (members.isEmpty()) return;

        // direction refresh — only leader updates shared direction
        directionRefreshTimer++;
        if (directionRefreshTimer >= changeDirectionTicks) {
            directionRefreshTimer = 0;
            boolean isLeader = members.stream()
                    .allMatch(a -> this.mob.getUUID().compareTo(a.getUUID()) < 0);
            if (isLeader) {
                Vec3 currentDir = flockDirections.getOrDefault(mob.getClass(), Vec3.ZERO);
                if (currentDir.equals(Vec3.ZERO)) {
                    currentDir = new Vec3(
                            mob.getRandom().nextDouble() - 0.5,
                            0,
                            mob.getRandom().nextDouble() - 0.5
                    ).normalize();
                }
                // rotate current direction by a small random angle instead of picking a new one
                double angleChange = (mob.getRandom().nextDouble() - 0.5) * Math.PI * 0.5;
                double cos = Math.cos(angleChange);
                double sin = Math.sin(angleChange);
                Vec3 newDir = new Vec3(
                        currentDir.x * cos - currentDir.z * sin,
                        0,
                        currentDir.x * sin + currentDir.z * cos
                ).normalize();
                flockDirections.put(mob.getClass(), newDir);
            }
        }

        // random offset refresh — only update every randomOffsetRefreshInterval ticks to prevent jitter
        randomOffsetRefreshTimer++;
        if (randomOffsetRefreshTimer >= randomOffsetRefreshInterval) {
            randomOffsetRefreshTimer = 0;
            cachedRandomOffset = new Vec3(
                    mob.getRandom().nextDouble() - 0.5,
                    0,
                    mob.getRandom().nextDouble() - 0.5
            ).scale(randomnessWeight);
        }

        Vec3 flockCenter = getFlockCenter(members);

        CavallCreature nearest = members.stream()
                .min(Comparator.comparingDouble(a -> a.distanceToSqr(mob)))
                .orElse(null);
        if (nearest == null) return;

        double distToNearest = mob.position().distanceTo(nearest.position());

        // if too far from flock, rejoin first
        if (distToNearest > rejoinFlockRadius) {
            mob.getNavigation().moveTo(
                    flockCenter.x,
                    mob.getY(),
                    flockCenter.z,
                    speed * 1.5
            );
            return;
        }

        Vec3 baseDirection = flockDirections.getOrDefault(mob.getClass(), Vec3.ZERO);
        if (baseDirection.equals(Vec3.ZERO)) return;

        Vec3 moveDir = baseDirection.scale(alignmentWeight)
                .add(cachedRandomOffset)
                .normalize()
                .scale(speed);

        mob.getNavigation().moveTo(
                mob.getX() + moveDir.x * 8,
                mob.getY(),
                mob.getZ() + moveDir.z * 8,
                speed
        );
    }
}