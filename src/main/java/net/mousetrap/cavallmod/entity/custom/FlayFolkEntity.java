package net.mousetrap.cavallmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.entity.custom.customgoals.*;
import net.mousetrap.cavallmod.tags.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlayFolkEntity extends CavallCreature {
    public FlayFolkEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState poseAnimationState = new AnimationState();
    public final AnimationState waitingToAmbushAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();

    public int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;
    //public boolean wasHiddenAmbushing = false;
    //private double originalY = 0.0;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FlagBasedFleeGoal(this, 1.25, ModTags.FLAYFOLK_PREDATORS, 20, 30, 2));
        this.goalSelector.addGoal(1, new FlagBasedMeleeAttackGoal(this, 1.25,true, 8, 12));

        this.goalSelector.addGoal(1, new FightOrFlightGoal(this, 500, 20, 0.8, 0.1, ModTags.FLAYFOLK_PREDATORS, true, 2));

        this.goalSelector.addGoal(2, new FlockingGoal(this, 1.0, 20.0, 1.5, 0.2, 0.2, 0.1, 15, 1, 5));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25));

        //this.goalSelector.addGoal(4, new KeepingStillAmbushGoal(this, ModTags.FLAYFOLK_PREY, 60,15, 1.5, 0));

        this.goalSelector.addGoal(1, new MultipurposeAmbushGoal(this, 45, 15, 1.5, 0.0));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new BreedGoal(this, 1.25));
        this.goalSelector.addGoal(6, new TemptGoal(this, 1.25, Ingredient.of(Items.CARROT), false));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 200, false, false, ModEntities.buildPredicateFromTag(ModTags.FLAYFOLK_PREY)));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

        //this.goalSelector.addGoal(1, new AmbushAttackGoal(this, 25, 16));

        //this.goalSelector.addGoal(4, new GetReadyToAmbushGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            this.poseAnimationState.startIfStopped(this.tickCount);
            setupAnimationStates();
        }

        // Sprinting
        if (this.isPursuing() || this.isFleeing()) {
            // if the pursuing flag or fleeing flag is true, start sprinting
            this.setSprinting(true);
            this.setSprintJumpingTo(true); // this animal can sprint jump
        } else {
            this.setSprinting(false);
            this.setSprintJumpingTo(false);
        }
        // Sprint-jump impulse
        if (this.isSprintJumping() && this.onGround()) {
            // if the animal's sprintjumping flag is true
            // and the animal is on the ground
            if (this.getRandom().nextInt(10) == 0) {
                Vec3 look = this.getLookAngle(); // initialize current direction
                this.setDeltaMovement( // jump in that direction
                        look.x * 0.6,
                        0.45, // y direction "power"
                        look.z * 0.6
                );
            }
        }
    }

    // this method is really just a countdown sort of method which counts down the timeout variable each tick
    // and if the timeout gets to zero, it resets it
    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0){
            attackAnimationTimeout = 20; // length of attack animation in ticks
            attackAnimationState.start(this.tickCount);
        } else{
            --this.attackAnimationTimeout;
        }
        if (!this.isAttacking()){
            attackAnimationState.stop();
        }

        if (this.isHiddenAmbushing() ){ //&& !wasHiddenAmbushing){
            waitingToAmbushAnimationState.start(this.tickCount);
            //originalY = this.getY();
            //this.setPos(this.getX(), originalY - 0.5, this.getZ()); // Lower into ground
            //wasHiddenAmbushing = true;
        }
        if (!this.isHiddenAmbushing() ){ // && wasHiddenAmbushing){
            waitingToAmbushAnimationState.stop();
            //this.setPos(this.getX(), originalY, this.getZ()); // Restore position
            //wasHiddenAmbushing = false; // sets it back to false again
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6f, 1f);
        } else{
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.ATTACK_KNOCKBACK,0.5)
                .add(Attributes.ATTACK_SPEED, 1.1)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.CARROT);
    }

    // creates an offspring when bred
    // not sure how to for example have two babies spawn when bred
    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        // return ModEntities.FOGFOX.get().create(pLevel);
        FlayFolkEntity baby = ModEntities.FLAYFOLK.get().create(pLevel);
        if (baby != null) baby.setBaby(true); // VERY IMPORTANT
        return baby;
    }
    // overriding the canMate method for automatic breeding
    @Override
    public boolean canMate(Animal otherAnimal) {
        // Only allow mating rarely
        if (otherAnimal.getClass() != this.getClass()) return false;

        if (!this.isIdling()) return false; // if the animal isnt idling, it cant breed

        return this.random.nextInt(60000) == 0 && !this.isBaby() && !otherAnimal.isBaby();
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.WARDEN_AMBIENT;
    }
    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.WARDEN_HURT;
    }
    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;
    }

    // getter and setter for WAITING_TO_AMBUSH flag

}
