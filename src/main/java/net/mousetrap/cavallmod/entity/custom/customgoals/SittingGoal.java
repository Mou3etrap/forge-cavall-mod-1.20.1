package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.EnumSet;

public class SittingGoal extends Goal {

    private final CavallCreature mob;
    private final int sitMinTicks;
    private final int sitMaxTicks;
    private int sitTimer = 0;
    private int sitTimerEnd;

    public SittingGoal(CavallCreature mob, int sitMinTicks, int sitMaxTicks) {
        this.mob = mob;
        this.sitMinTicks = sitMinTicks;
        this.sitMaxTicks = sitMaxTicks;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        //this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
//        this.sitTimerEnd = sitMinTicks + mob.getRandom().nextInt(sitMaxTicks - sitMinTicks + 1);
    }

//    @Override
//    public boolean canUse() {
//        if (mob.isBaby()) return false;
//        if (mob.isOrderedToSit()) return true; // always sit when ordered
//        if (!mob.isIdling()) return false;
//        return mob.getRandom().nextFloat() < 0.2f;
//    }
    @Override
    public boolean canUse() {
        if (mob.isBaby()) {
            //System.out.println(mob.getUUID() + " | canUse: false (baby)");
            return false;
        }
        if (mob.isOrderedToSit()) {
            System.out.println(mob.getUUID() + " | canUse: true (ordered)");
            return true;
        }
        if (!mob.isIdling()) {
            System.out.println(mob.getUUID() + " | canUse: false (not idling)");
            return false;
        }
        boolean roll = mob.getRandom().nextFloat() < 0.002f;
        System.out.println(mob.getUUID() + " | canUse: roll=" + roll + " isIdling=" + mob.isIdling());
        return roll;
    }

//    @Override
//    public boolean canContinueToUse() {
//        if (mob.isOrderedToSit()) return true; // owner commanded, stay sitting
//        //if (!mob.isIdling()) return false;
//        if (mob.isCavallCreatureSitting()) return true;
//        sitTimer--;
//        return mob.isCavallCreatureVisuallySitting() && sitTimer > 0;
//    }
    @Override
    public boolean canContinueToUse() {
        System.out.println(mob.getUUID() + " Entered canContinueToUse");
        if (mob.isOrderedToSit()) return true;
        if (mob.isOnTheMove()) return false;
        //int sitTimerEnd = sitMinTicks + mob.getRandom().nextInt(sitMaxTicks - sitMinTicks + 1);
        sitTimer++;
        System.out.println(mob.getUUID() + " | canContinueToUse: isSitting=" + mob.isCavallCreatureSitting() + " sitTimer=" + sitTimer + " isIdling=" + mob.isIdling() + " isOTM=" + mob.isOnTheMove());
        boolean check = sitTimer <= sitTimerEnd;
        System.out.println(mob.getUUID() + " | can I continue? " + check + "sitTimer= " + sitTimer+" sitTimerEnd= "+sitTimerEnd);
        return sitTimer <= sitTimerEnd;
    }

    @Override
    public void start() {
        // only call sitDown if not already sitting
        this.sitTimerEnd = sitMinTicks + mob.getRandom().nextInt(sitMaxTicks - sitMinTicks + 1);
        System.out.println(mob.getUUID() + " | SittingGoal start fired with sitTimerEnd= " + sitTimerEnd);
        if (!mob.isCavallCreatureSitting()) {
            mob.sitDown();
            mob.refreshDimensions();
        }
        mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        System.out.println(mob.getUUID() + " Entered stop");
        // only stand up if not ordered to sit
        if (!mob.isOrderedToSit()) {
            mob.standUp();
            mob.refreshDimensions();
        }
    }
}

