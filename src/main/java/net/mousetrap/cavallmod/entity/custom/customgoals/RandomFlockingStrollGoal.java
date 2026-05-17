package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class RandomFlockingStrollGoal extends Goal {

    private final CavallCreature mob;
    private final double speed;
    private final double rejoinFlockRadius;
    private final int pauseMinTicks;
    private final int pauseMaxTicks;
    private int pauseTimer = 0;
    private boolean paused = false;

    public RandomFlockingStrollGoal(CavallCreature mob, double speed, double rejoinFlockRadius,
                                    int pauseMinTicks, int pauseMaxTicks) {
        this.mob = mob;
        this.speed = speed;
        this.rejoinFlockRadius = rejoinFlockRadius;
        this.pauseMinTicks = pauseMinTicks;
        this.pauseMaxTicks = pauseMaxTicks;
        this.setFlags(EnumSet.of(Flag.MOVE));
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
        return !getFlockMembers().isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        return !getFlockMembers().isEmpty();
    }

    @Override
    public void tick() {
        if (paused) {
            pauseTimer--;
            if (pauseTimer <= 0) {
                paused = false;
            }
            return;
        }

        List<? extends CavallCreature> members = getFlockMembers();
        if (members.isEmpty()) return;

        Vec3 flockCenter = getFlockCenter(members);

        CavallCreature nearest = members.stream()
                .min(Comparator.comparingDouble(a -> a.distanceToSqr(mob)))
                .orElse(null);
        if (nearest == null) return;

        double distToNearest = mob.position().distanceTo(nearest.position());

        if (distToNearest > rejoinFlockRadius * 0.5) {
            mob.getNavigation().moveTo(
                    flockCenter.x,
                    mob.getY(),
                    flockCenter.z,
                    speed * 1.5
            );
            return;
        }

        if (mob.getNavigation().isDone()) {
            if (mob.getRandom().nextFloat() < 0.35f) {
                paused = true;
                pauseTimer = pauseMinTicks + mob.getRandom().nextInt(pauseMaxTicks - pauseMinTicks + 1);
                mob.getNavigation().stop();
                return;
            }
            Vec3 randomPos = DefaultRandomPos.getPos(mob, 10, 7);
            if (randomPos != null) {
                mob.getNavigation().moveTo(randomPos.x, randomPos.y, randomPos.z, speed);
            }
        }
    }
}