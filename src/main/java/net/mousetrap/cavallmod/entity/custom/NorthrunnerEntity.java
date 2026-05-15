package net.mousetrap.cavallmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.entity.custom.customgoals.FightOrFlightGoal;
import net.mousetrap.cavallmod.entity.custom.customgoals.FlagBasedFleeGoal;
import net.mousetrap.cavallmod.entity.custom.customgoals.FlagBasedMeleeAttackGoal;
import net.mousetrap.cavallmod.entity.custom.customgoals.FlockingGoal;
import net.mousetrap.cavallmod.tags.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NorthrunnerEntity extends CavallCreature {
    public NorthrunnerEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState poseAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new FlagBasedMeleeAttackGoal(this, 1.1, true, 10, 10));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new FlockingGoal(this, 1.0, flockRadius, 1.2, 0.7, 0.75, 0.2, 2, 2, 5));
        //this.goalSelector.addGoal(3, new TerrestrialFlockOnTheMoveGoal(this, 1.0, flockRadius, 1.5, 0.1, 0.75, 0.2, 0.7, 200, 600));


        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.25));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.25, Ingredient.of(Items.CARROT), false));

        //this.goalSelector.addGoal(2, new FlagBasedMeleeAttackGoal(this, 1.15,true, ));

        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
    }

    public int flockRadius = 15;
    public int predatorDetectionRadius = 40;

    public int onTheMoveTimeout = 1200;
    // every minute the mobs will attempt to be placed in the OnTheMove flag

    public int howLongOnTheMove = 2400;
    // if they are on the move they will be as so for 2 minutes
    // (there is a randomness applied)
    public int timer1 = 0; // used for onTheMoveTimeout
    public int timer2 = 0; // used for howLongOnTheMove

    public boolean canFly = false;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.poseAnimationState.startIfStopped(this.tickCount);
            setupAnimationStates();
        }

        // Setting the On The Move flag at random if the flock is idling
        if (this.isIdling()) { // if flock is idling
            timer1++;
            if (timer1 == onTheMoveTimeout) { // if its time to start the timeout
                this.setOnTheMoveTo(true);
                // set their allies to OnTheMove as well
                List<? extends CavallCreature> neighbors = getNeighbors(this, flockRadius, NorthrunnerEntity.class);
                for (CavallCreature member : neighbors){
                    member.setOnTheMoveTo(true);
                }
                timer1 = 0; // resets timer
            }
        }
        if (this.isOnTheMove()){ // if weve been on the move
            timer2++;
            if (timer2 == howLongOnTheMove){ // if its time to stop being on the move
                this.resetFlagsToIdle(); // resets to idle
                // set their allies to OnTheMove as well
                List<? extends CavallCreature> neighbors = getNeighbors(this, flockRadius, NorthrunnerEntity.class);
                for (CavallCreature member : neighbors){
                    member.resetFlagsToIdle(); // resets to idle
                }
                timer2 = 0; // resets timer
            }
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

    // this method is really just a coutdown sort of method which counts down the timeout variable each tick
    // and if the timeout gets to zero, it resets it
    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
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
                .add(Attributes.MAX_HEALTH, 12D)
                .add(Attributes.ATTACK_DAMAGE,3D)
                .add(Attributes.ATTACK_KNOCKBACK,0.2)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
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
        NorthrunnerEntity baby = ModEntities.NORTHRUNNER.get().create(pLevel);
        if (baby != null) baby.setBaby(true); // VERY IMPORTANT
        return baby;
    }
    // overriding the canMate method for automatic breeding
    @Override
    public boolean canMate(Animal otherAnimal) {
        // Only allow mating rarely
        if (otherAnimal.getClass() != this.getClass()) return false;

        if (!this.isIdling()) return false; // if the animal isnt idling, it cant breed

        // chance of breeding
        return this.random.nextInt(50000) == 0 && !this.isBaby() && !otherAnimal.isBaby();
    }


    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.BAT_AMBIENT;
    }
    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BAT_HURT;
    }
    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }
}
