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
import net.mousetrap.cavallmod.entity.CavallCreature;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.entity.custom.customgoals.*;
import net.mousetrap.cavallmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlayFolkEntity extends CavallCreature {
    public FlayFolkEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState poseAnimationState = new AnimationState();
    public final AnimationState waitingToAmbushAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();

    public int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FlagBasedFleeGoal(this, 1.25));
        this.goalSelector.addGoal(1, new FlagBasedMeleeAttackGoal(this, 1.25,true, 8, 12));
        this.goalSelector.addGoal(1, new AmbushAttackGoal(this, 10));
        this.goalSelector.addGoal(1, new FightOrFlightGoal(this, 500, 20, 20, 0.8, 0.1, ModTags.FLAYFOLK_PREDATORS));

        this.goalSelector.addGoal(2, new BreedGoal(this, 1.25));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, Ingredient.of(Items.CARROT), false));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new FlockingGoal(this, 1.0, 20.0, 1.5, 0.2, 0.2, 0.1, 15, 1, 5));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 200, false, false, ModEntities.buildPredicateFromTag(ModTags.FLAYFOLK_PREY)));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));


        //this.goalSelector.addGoal(4, new GetReadyToAmbushGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            this.poseAnimationState.startIfStopped(this.tickCount);
            setupAnimationStates();
        }

        // automatic breeding
        // every tick the entity looks for another fogfox
        // and has a chance to set them both into love mode
        if (!this.level().isClientSide && !this.isBaby()) {
            // find nearby adult of same species
            List<FlayFolkEntity> nearby = this.level().getEntitiesOfClass(FlayFolkEntity.class,
                    this.getBoundingBox().inflate(2.0D),
                    f -> f != this && !f.isBaby());

            // looping through every fogfox nearby
            for (FlayFolkEntity mate : nearby) {
                if (this.canMate(mate)) { // the canMate method contains the chance
                    this.setInLove(null);
                    mate.setInLove(null);
                    AgeableMob baby = this.getBreedOffspring((ServerLevel) this.level(), mate);
                    baby.moveTo(this.getX(), this.getY(), this.getZ(), 0, 0); // position baby at parent
                    this.level().addFreshEntity(baby);
                    break;
                }
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
        if (this.isAttacking() && attackAnimationTimeout <= 0){
            attackAnimationTimeout = 20; // length of attack animation in ticks
            attackAnimationState.start(this.tickCount);
        } else{
            --this.attackAnimationTimeout;
        }
        if (!this.isAttacking()){
            attackAnimationState.stop();
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
        // chance of breeding
        return this.random.nextInt(10000) == 0 && !this.isBaby() && !otherAnimal.isBaby();
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
