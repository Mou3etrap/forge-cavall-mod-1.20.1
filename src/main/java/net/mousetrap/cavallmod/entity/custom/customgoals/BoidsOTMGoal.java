package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.List;

public class BoidsOTMGoal extends Goal {
    private final CavallCreature self;

    private final double speed;
    // speed while flocking
    // normal: 1
    // skittish: greater than one
    // sluggish: less than one

    private final int flockRadius;
    // how far the mob considers neighbors
    // how far away other mobs must be to consider them in the flock
    // small: 6-10
    // medium: 10-16

    private final int nearbyRadius;
    private final int separationRadius;

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
    private final int updateInterval; // ticks between updates

    public BoidsOTMGoal(
            CavallCreature self,
            double speed,
            int flockRadius,
            int nearbyRadius,
            int separationRadius,
            double separationWeight,
            double cohesionWeight,
            double alignmentWeight,
            double randomnessWeight,
            double maxFlockDistance,
            int updateInterval
    ) {
        this.self = self;
        this.speed = speed;
        this.flockRadius = flockRadius;
        this.nearbyRadius = nearbyRadius;
        this.separationRadius = separationRadius;
        this.separationWeight = separationWeight;
        this.cohesionWeight = cohesionWeight;
        this.alignmentWeight = alignmentWeight;
        this.randomnessWeight = randomnessWeight;
        this.maxFlockDistance = maxFlockDistance;
        this.updateInterval = updateInterval;
    }

    @Override
    public void tick() {
        Level level = self.level();

        // acquiring neighbors in the entire flock
        List<? extends CavallCreature> neighbors = self.getNeighbors(self, flockRadius, self.getClass());
        // getting closest neighbors as determined by nearbyRadius
        List<? extends CavallCreature> nearestNeighbors = self.getNeighbors(self, nearbyRadius, self.getClass());

        // finding average location of the entire flock

        // finding average location of the nearest neighbors

        if (neighbors.isEmpty() && nearestNeighbors.isEmpty()) return; // if there's no flock, nothing can be done

        // Cohesion: steer to move towards the average position of local flockmates
        Vec3 cohesion = Vec3.ZERO;
        Vec3 localAvPosition = Vec3.ZERO;
        // for every local flockmate
        for (CavallCreature localFlockmate : nearestNeighbors){
            Vec3 flockmatePosition = localFlockmate.position();
            // get their position
            localAvPosition = localAvPosition.add(flockmatePosition);
            // add up each position to get the average one

            // make a vector pointing from oneself to the average position
            // and call that vector "cohesion"
            cohesion = localAvPosition.subtract(self.position());
        }

        // Alignment: steer towards the average heading of local flockmates
        Vec3 alignment = Vec3.ZERO;
        // for every local flockmate
        for (CavallCreature localFlockmate : nearestNeighbors){
            Vec3 flockmateAlignment = localFlockmate.getLookAngle();
            // get their looking angle
            // which is a unit vector by default
            alignment = alignment.add(flockmateAlignment);
            // add each flockmate alignment up
            // "alignment" is the vector of direction
        }

        // Separation: steer to avoid crowding local flockmates
        Vec3 separation = Vec3.ZERO;
        Vec3 sumOfVectorsAway = Vec3.ZERO;
        // for every local flockmate
        for (CavallCreature localFlockmate : nearestNeighbors){
            Vec3 toFlockmate = localFlockmate.position().subtract(self.position());
            // vector from self to flockmate
            double distance = toFlockmate.length();
            // get the distance from the flockmate to oneself

            // invert direction of the vector from self to flockmate
            // such that it comes from self and moves away from the flockmate
            // via multiplying by negative one
            Vec3 fromFlockmate = toFlockmate.scale(-1);
            // sum all vectors
            sumOfVectorsAway = sumOfVectorsAway.add(fromFlockmate);
        }

        // Noise aka Randomness
        Vec3 noise = Vec3.ZERO;



        // Readying the new vector
        Vec3 moveVec = separation.scale(separationWeight)
                .add(alignment.scale(alignmentWeight))
                .add(cohesion.scale(cohesionWeight))
                .add(noise);

        // Moving the mob
        self.getNavigation().moveTo(
                self.getX() + moveVec.x,
                self.getY(),
                self.getZ() + moveVec.z,
                speed
        );


    }

    @Override
    public boolean canUse() {
        return self.isOnTheMove();
    }
    @Override
    public boolean canContinueToUse() {
        return self.isOnTheMove();
    }
    @Override
    public void stop() {
        super.stop();
    }
    @Override
    public void start() {
        super.start();
    }
}
