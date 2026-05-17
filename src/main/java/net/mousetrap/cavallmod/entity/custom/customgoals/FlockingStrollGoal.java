package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FlockingStrollGoal extends Goal {

    private final Animal mob;
    private final double speed;
    private final double rejoinFlockRadius;

    public FlockingStrollGoal(Animal mob, double speed, double rejoinFlockRadius) {
        this.mob = mob;
        this.speed = speed;
        this.rejoinFlockRadius = rejoinFlockRadius;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    private List<Animal> getFlockMembers() {
        return mob.level().getEntitiesOfClass(
                Animal.class,
                mob.getBoundingBox().inflate(rejoinFlockRadius),
                a -> a != mob && a.getClass() == mob.getClass()
        );
    }

    private Vec3 getFlockCenter(List<Animal> members) {
        Vec3 center = Vec3.ZERO;
        for (Animal member : members) {
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
        List<Animal> members = getFlockMembers();
        if (members.isEmpty()) return;

        Vec3 flockCenter = getFlockCenter(members);
        double distToCenter = mob.position().distanceTo(flockCenter);

        // find the nearest flock member
        Animal nearest = members.stream()
                .min(Comparator.comparingDouble(a -> a.distanceToSqr(mob)))
                .orElse(null);

        if (nearest == null) return;

        double distToNearest = mob.position().distanceTo(nearest.position());

        if (distToNearest > rejoinFlockRadius * 0.5) {
            // getting too far from nearest neighbor — head straight to flock center
            mob.getNavigation().moveTo(
                    flockCenter.x,
                    mob.getY(),
                    flockCenter.z,
                    speed * 1.2
            );
        } //else {
            // similar to WaterAvoidingRandomStrollGoal
           // if (mob.getNavigation().isDone()) {
           //     Vec3 randomPos = DefaultRandomPos.getPos(mob, 10, 7);
           //     if (randomPos != null) {
           //         mob.getNavigation().moveTo(randomPos.x, randomPos.y, randomPos.z, speed);
        //    }
        //}
        //}
    }
}