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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.entity.custom.customgoals.FlockingGoal;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FogFoxEntity extends Animal {
    public FogFoxEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState poseAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.25));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25, Ingredient.of(Items.CARROT), false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(2, new FlockingGoal(this, 1.0, 15.0, 1.5, 0.4, 0.5, 0.2, 12, 1, 5));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            this.poseAnimationState.startIfStopped(this.tickCount);
            setupAnimationStates();
        }
        // every tick the entity looks for another fogfox
        // and has a chance to set them both into love mode
        if (!this.level().isClientSide && !this.isBaby()) {
            // find nearby adult of same species
            List<FogFoxEntity> nearby = this.level().getEntitiesOfClass(FogFoxEntity.class,
                    this.getBoundingBox().inflate(2.0D),
                    f -> f != this && !f.isBaby());

            // looping through every fogfox nearby
            for (FogFoxEntity mate : nearby) {
                if (this.canMate(mate)) {
                    this.setInLove(null); // optional, just sets “ready to breed”
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
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
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
        FogFoxEntity baby = ModEntities.FOGFOX.get().create(pLevel);
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
