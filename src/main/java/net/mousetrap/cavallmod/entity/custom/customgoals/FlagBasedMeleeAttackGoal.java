package net.mousetrap.cavallmod.entity.custom.customgoals;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.mousetrap.cavallmod.entity.CavallCreature;

public class FlagBasedMeleeAttackGoal extends MeleeAttackGoal {
    private final CavallCreature mob;
    private final int ticksBeforeDamage; // ticks before damage dealt in the animation
    private final int ticksAfterDamage; // ticks after damage dealt waiting for attack animation to end
    // ticksBeforeDamage + ticksAfterDamage should be the animation duration in ticks
    private int attackCooldown; // counts down each tick

    public FlagBasedMeleeAttackGoal(CavallCreature mob, double speed, boolean followTarget, int ticksBeforeDamage, int ticksAfterDamage) {
        super(mob, speed, followTarget);
        this.mob = mob;
        this.ticksBeforeDamage = ticksBeforeDamage;
        this.ticksAfterDamage = ticksAfterDamage;
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

        if (attackCooldown <= 0) {
            mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
            mob.swing(InteractionHand.MAIN_HAND);
            mob.doHurtTarget(target);
            resetAttackCooldown();
            mob.setAttackingTo(false);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (attackCooldown > 0) attackCooldown--;
    }

    protected void resetAttackCooldown() {
        attackCooldown = ticksBeforeDamage + ticksAfterDamage;
    }

    @Override
    public boolean canUse() {
        return mob.isFighting() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return mob.isFighting() && super.canContinueToUse();
    }
}



