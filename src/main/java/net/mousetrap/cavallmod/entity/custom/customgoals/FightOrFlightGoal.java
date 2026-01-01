package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.List;

public class FightOrFlightGoal extends Goal {

    private final CavallCreature self;
    private final int checkForPredatorsInterval;
    // interval which the cooldown is set to
    // how often they look for predators
    private int cooldown;
    // timer variable for checkForPredatorsInterval

    private final int predatorRadius;
    // how far they look for predators
    private final double allyRadius;
    private final double aggression;
    // zero: not aggressive, will always flee
    // one: all members will always attack
    private final double panicThreshold;
    // percentage of herd population which can be lost
    // if panicThreshold is 0.2, then if they lose 20% of their population,
    // they will all flee no matter what
    private final TagKey<EntityType<?>> predatorTag;
    // the tag for the predator, such as FOGFOX_PREDATORS
    // which is needed because the isPredatorNearby() method is in the base class
    // and must be given the tag itself for each creature
    private int initialHerdSize;

    public FightOrFlightGoal(
            CavallCreature self,
            int checkForPredatorsInterval,
            int predatorRadius,
            double allyRadius,
            double aggression,
            double panicThreshold,
            TagKey<EntityType<?>> predatorTag
    ) {
        this.self = self;
        this.checkForPredatorsInterval = checkForPredatorsInterval;
        this.predatorRadius = predatorRadius;
        this.allyRadius = allyRadius;
        this.aggression = aggression;
        this.cooldown = checkForPredatorsInterval;
        this.panicThreshold = panicThreshold;
        this.initialHerdSize = 0;
        this.predatorTag = predatorTag;
    }

    @Override
    public boolean canUse() {
        if (--cooldown > 0) return false; // if the cooldown is still counting down, the mob cant use this method yet. the cooldown should NOT be large -- maybe every second
        // if cooldown has reached zero, reset it
        cooldown = checkForPredatorsInterval;
        return self.isPredatorNearby(predatorRadius, predatorTag);
    }
    @Override
    public boolean canContinueToUse() {
        // keep running while the mob is flagged to fight or flee
        return (self.isFighting() || self.isFleeing()) && self.isPredatorNearby(predatorRadius, predatorTag);
    }

    @Override
    public void tick() {
        List<CavallCreature> herd = self.level().getEntitiesOfClass(
                CavallCreature.class,
                self.getBoundingBox().inflate(allyRadius),
                a -> a.getType() == self.getType()
        );
        if (this.initialHerdSize == 0){
            this.initialHerdSize = herd.size();
        }
        int currentHerdSize = herd.size();
        int minHerdSize = (int) (initialHerdSize * (1.0 - panicThreshold));
        if (currentHerdSize < minHerdSize) {
            for (CavallCreature member : herd){
                member.setFleeingTo(true);
                member.setFightingTo(false); // force panic
            }
        }
    }

    @Override
    public void start() {
        List<CavallCreature> herd = self.level().getEntitiesOfClass(
                CavallCreature.class,
                self.getBoundingBox().inflate(allyRadius),
                a -> a.getType() == self.getType()
        );
        int currentHerdSize = herd.size();
        // fightChance represents how confident the whole herd feels
        // higher the more members there are
        float fightChance = Math.min(1.0F, currentHerdSize * (float) aggression);

        // for every animal in the herd
        for (CavallCreature member : herd) {
            // willFight is true if a random number is less than fightChance
            // (higher fightChance == more confident herd == more likely to be greater than a random number)
            // and the agression is also put against a random number
            // to decide individual confidence
            boolean willFight = member.getRandom().nextFloat() < fightChance &&
                    member.getRandom().nextFloat() < aggression;
            if (willFight) {
                //member.setFleeingTo(false); // stop fleeing!!
                member.setFightingTo(true); // fight!!
            } else {
                //member.setFightingTo(false); // stop fighting!!
                member.setFleeingTo(true); // flee!!!
            }
        }
    }
}

