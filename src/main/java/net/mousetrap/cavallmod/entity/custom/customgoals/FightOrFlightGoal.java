package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.List;

public class FightOrFlightGoal extends Goal {

    private final CavallCreature self;

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
    private boolean selfDefending;
    private int selfDefenseRadius;
    private int initialHerdSize;
    private boolean decisionMade = false;
    private int selfDefenseCountdown = 40;
    // the animal will defend itself for 2 seconds if neared too close by a predator
    private int selfDefenseCountdownTimer; // variable that is counted down


    public FightOrFlightGoal(
            CavallCreature self,
            int predatorRadius,
            double allyRadius,
            double aggression,
            double panicThreshold,
            TagKey<EntityType<?>> predatorTag,
            boolean selfDefending,
            int selfDefenseRadius
    ) {
        this.self = self;
        this.predatorRadius = predatorRadius;
        this.allyRadius = allyRadius;
        this.aggression = aggression;
        this.panicThreshold = panicThreshold;
        this.predatorTag = predatorTag;
        this.selfDefending = selfDefending;
        this.selfDefenseRadius = selfDefenseRadius;
    }

    @Override
    public boolean canUse() {
        return self.isAnimalWithCertainTagNearby(predatorRadius, predatorTag);
    }

    @Override
    public boolean canContinueToUse() {
        return self.isAnimalWithCertainTagNearby(predatorRadius, predatorTag);
    }

    @Override
    public void start() {
        decisionMade = false; // reset for this predator encounter
        initialHerdSize = 0;
    }

    @Override
    public void stop() {
        decisionMade = false; // allow new decision next time
        initialHerdSize = 0;
        self.setPursuingTo(false);
        self.setFleeingTo(false);
    }

    @Override
    public void tick() {
        List<CavallCreature> herd = self.level().getEntitiesOfClass(
                CavallCreature.class,
                self.getBoundingBox().inflate(allyRadius),
                a -> a.getType() == self.getType()
        );
        if (herd.isEmpty()) return;

        if (initialHerdSize == 0) {
            initialHerdSize = herd.size();
        }

        int currentHerdSize = herd.size();
        int minHerdSize = (int) (initialHerdSize * (1.0 - panicThreshold));

        if (selfDefending && self.isAnimalWithCertainTagNearby(selfDefenseRadius, predatorTag)){
            // if the prey is self-defending and
            // if a predator nears the prey by one block it will automatically switch to self-defense
            self.setPursuingTo(true);
            self.setFleeingTo(false);
        }

        // force panic
        if (currentHerdSize < minHerdSize) {
            for (CavallCreature member : herd) {
                member.setFleeingTo(true);
                member.setPursuingTo(false);
            }
            return;
        }

        if (!decisionMade) {
            float fightChance = Math.min(1.0F, currentHerdSize * (float) aggression);

            for (CavallCreature member : herd) {
                boolean willFight =
                        member.getRandom().nextFloat() < fightChance
                                && member.getRandom().nextFloat() < aggression;

                if (willFight) {
                    member.setPursuingTo(true);
                    member.setFleeingTo(false);
                    System.out.println("Pursuing! My FIGHTING flag is "+member.isPursuing()+" and my FLEEING tag is "+member.isFleeing());
                } else {
                    member.setFleeingTo(true);
                    member.setPursuingTo(false);
                    System.out.println("Fleeing! My FLEEING flag is "+member.isFleeing()+" and my FIGHTING tag is "+member.isPursuing());
                }
            }
            decisionMade = true;
        }
    }
}

