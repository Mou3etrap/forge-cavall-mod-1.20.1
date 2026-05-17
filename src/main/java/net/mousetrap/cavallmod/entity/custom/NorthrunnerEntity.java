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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.mousetrap.cavallmod.entity.CavallCreature;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.entity.custom.customgoals.*;
import net.mousetrap.cavallmod.tags.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NorthrunnerEntity extends CavallCreature {
    public NorthrunnerEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setTame(false);
        this.onTheMoveTimeout = 600; // override
        this.howLongOnTheMove = 1200; // override
        this.timer1 = (int)(Math.random() * onTheMoveTimeout); // override
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState poseAnimationState = new AnimationState();
    //public final AnimationState sittingAnimationState = new AnimationState();
    //public final AnimationState sittingProcessAnimationState = new AnimationState();
    //public final AnimationState standingUpProcessAnimationState = new AnimationState();

    //private static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.fixed(1.2f, 0.7f);

    private int idleAnimationTimeout = 0;

    private static final int flockRadius = 20;
    public int predatorDetectionRadius = 40;

    @Override
    protected void registerGoals() {
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f);
        // tameable mob goals
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));

        // goals universal to all animals
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.25));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.CARROT), false));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        // goals for terrestrial animals
        this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(6, new SittingGoal(this, 200, 600));
        this.goalSelector.addGoal(5, new RandomFlockingStrollGoal(this, 1.0, flockRadius, 40, 100));
        this.goalSelector.addGoal(4, new FlockingOnTheMoveGoal(this, 0.7, flockRadius, 0.8, 0.3, 100));
        //this.goalSelector.addGoal(3, new FlockingStrollGoal(this, 1.0, 16.0));
        //this.goalSelector.addGoal(2, new FlockingGoal(this, 1.0, flockRadius, 1.2, 0.8, 0.3, 0.5, 4, 2, 2));
        //this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1));
        //this.goalSelector.addGoal(4, new TerrestrialFlockOnTheMoveGoal(this, 0.7, flockRadius,1.5,0.8,0.8,0.1,1,300,600));

        //this.goalSelector.addGoal(2, new FlagBasedMeleeAttackGoal(this, 1.1, true, 10, 10));

        //this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
    }

    public boolean canFly = false;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.poseAnimationState.startIfStopped(this.tickCount);
            setupAnimationStates();
        }

        // Setting the On The Move flag at random if the flock is idling
        if (this.isIdling()) { // if animal is idling
            timer1++;
            System.out.println(this.getUUID() + " | my timer1 is " + this.timer1);
            List<? extends CavallCreature> neighbors = getNeighbors(this, flockRadius, NorthrunnerEntity.class);
            if (timer1 >= onTheMoveTimeout) {
                this.setOnTheMoveTo(true);
                this.setIdlingTo(false);
                System.out.println(this.getUUID() + " | I'm OTM! =" + this.isOnTheMove());
                for (CavallCreature member : neighbors) {
                    member.setOnTheMoveTo(true);
                    member.setIdlingTo(false);
                    System.out.println(this.getUUID() + " | My friend is OTM! =" + member.isOnTheMove());
                    member.timer1 = 0; // prevent neighbors from immediately re-triggering
                }
                timer1 = 0;
            }
        }
        if (this.isOnTheMove()){ // if we've been on the move
            timer2++;
            System.out.println(this.getUUID() + " | my timer2 is " + this.timer2);
            if (timer2 == howLongOnTheMove){ // if it's time to stop being on the move
                this.resetFlagsToIdle(); // resets to idle
                // set their allies to idle as well
                List<? extends CavallCreature> neighbors = getNeighbors(this, flockRadius, NorthrunnerEntity.class);
                for (CavallCreature member : neighbors){
                    member.resetFlagsToIdle(); // resets to idle
                }
                timer2 = 0; // resets timer
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
//        if (this.isSitting()) {
//            this.sittingAnimationState.startIfStopped(this.tickCount);
//            this.idleAnimationState.stop(); // stop walk/idle animation while sitting
//        } else {
//            this.sittingAnimationState.stop();
//            this.idleAnimationState.startIfStopped(this.tickCount);
//        }
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
                .add(Attributes.ARMOR,0.1)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 16D)
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
        return SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM;
    }
    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.AXOLOTL_HURT;
    }
    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }
}
