package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;

import java.util.EnumSet;

public class FlagBasedMeleeAttackGoal extends MeleeAttackGoal {
    private final CavallCreature mob;
    private final int ticksBeforeDamage; // ticks before damage dealt in the animation
    private final int ticksAfterDamage; // ticks after damage dealt waiting for attack animation to end
    // ticksBeforeDamage + ticksAfterDamage should be the animation duration in ticks
    private int attackCooldown; // counts down each tick
    private double speed;
    // movement speed during target approach

    public FlagBasedMeleeAttackGoal(CavallCreature mob, double speed, boolean followTarget, int ticksBeforeDamage, int ticksAfterDamage) {
        super(mob, speed, followTarget);
        this.mob = mob;
        this.ticksBeforeDamage = ticksBeforeDamage;
        this.ticksAfterDamage = ticksAfterDamage;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target, double distanceSq) {
        if (distanceSq > this.getAttackReachSqr(target)) {
            attackCooldown = 0;
            mob.setAttackingTo(false);
            return;
        }

        if (attackCooldown == ticksBeforeDamage) {
            mob.setAttackingTo(true); // start animation
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (attackCooldown > 0) attackCooldown--;

        LivingEntity target = mob.getTarget();
        if (target == null) return;

        mob.getLookControl().setLookAt(target, 30f, 30f);

        mob.getNavigation().moveTo(target, speed);

        double distSq = mob.distanceToSqr(target);

        if (distSq < this.getAttackReachSqr(target) && attackCooldown <= 0) {
            mob.doHurtTarget(target);
        }
    }

    protected void resetAttackCooldown() {
        attackCooldown = ticksBeforeDamage + ticksAfterDamage;
    }

    @Override
    public boolean canUse() {
        return mob.isPursuing();
    }

    @Override
    public boolean canContinueToUse() {
        return mob.isPursuing();
    }
}



