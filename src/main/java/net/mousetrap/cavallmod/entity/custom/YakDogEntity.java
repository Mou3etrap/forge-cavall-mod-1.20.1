package net.mousetrap.cavallmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.mousetrap.cavallmod.entity.CavallCreature;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.entity.custom.customgoals.FlockingOnTheMoveGoal;
import net.mousetrap.cavallmod.entity.custom.customgoals.RandomFlockingStrollGoal;
import net.mousetrap.cavallmod.entity.custom.customgoals.RandomLookAroundWhileSittingGoal;
import net.mousetrap.cavallmod.entity.custom.customgoals.SittingGoal;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class YakDogEntity extends CavallCreature {
    public YakDogEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setTame(false);
        this.onTheMoveTimeout = 1200; // override
        this.howLongOnTheMove = 600; // override
        this.timer1 = (int)(Math.random() * onTheMoveTimeout); // override
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sittingProcessAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState standingUpProcessAnimationState = new AnimationState();
    // public final AnimationState poseAnimationState = new AnimationState();
    //public final AnimationState sittingAnimationState = new AnimationState();
    //public final AnimationState sittingProcessAnimationState = new AnimationState();
    //public final AnimationState standingUpProcessAnimationState = new AnimationState();

    private static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.scalable(1.5f, 1f);

    private int idleAnimationTimeout = 0;

    private static final int flockRadius = 30;
    public int predatorDetectionRadius = 40;

    @Override
    protected void registerGoals() {
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0f);
        // tameable mob goals
        //this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));

        // goals for hunting/attacking/defending
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());

        // goals universal to all animals
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new BreedGoal(this, 1.25));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        // goals for terrestrial animals
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new SittingGoal(this, 200, 600));
        this.goalSelector.addGoal(6, new RandomFlockingStrollGoal(this, 1.0, flockRadius, 40, 100));
        this.goalSelector.addGoal(5, new FlockingOnTheMoveGoal(this, 1.0, flockRadius, 0.6, 0.3, 60));
        this.goalSelector.addGoal(8, new RandomLookAroundWhileSittingGoal(this));
        //this.goalSelector.addGoal(3, new FlockingStrollGoal(this, 1.0, 16.0));
        //this.goalSelector.addGoal(2, new FlockingGoal(this, 1.0, flockRadius, 1.2, 0.8, 0.3, 0.5, 4, 2, 2));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 1));
        //this.goalSelector.addGoal(4, new TerrestrialFlockOnTheMoveGoal(this, 0.7, flockRadius,1.5,0.8,0.8,0.1,1,300,600));

        //this.goalSelector.addGoal(2, new FlagBasedMeleeAttackGoal(this, 1.1, true, 10, 10));

        //this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
         //this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, NorthrunnerEntity.class, false));

    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.idleAnimationState.startIfStopped(this.tickCount);
            setupAnimationStates();
        }

        // Setting the On The Move flag at random if the flock is idling
        if (this.isIdling()) { // if animal is idling
            timer1++;
            //System.out.println(this.getUUID() + " | my timer1 is " + this.timer1);
            List<? extends CavallCreature> neighbors = getNeighbors(this, flockRadius, YakDogEntity.class);
            if (timer1 >= onTheMoveTimeout) {
                this.setOnTheMoveTo(true);
                this.setIdlingTo(false);
                //System.out.println(this.getUUID() + " | I'm OTM! =" + this.isOnTheMove());
                for (CavallCreature member : neighbors) {
                    member.setOnTheMoveTo(true);
                    member.setIdlingTo(false);
                    //System.out.println(this.getUUID() + " | My friend is OTM! =" + member.isOnTheMove());
                    member.timer1 = 0; // prevent neighbors from immediately re-triggering
                }
                timer1 = 0;
            }
        }
        if (this.isOnTheMove()){ // if we've been on the move
            timer2++;
            //System.out.println(this.getUUID() + " | my timer2 is " + this.timer2);
            if (timer2 == howLongOnTheMove){ // if it's time to stop being on the move
                this.resetFlagsToIdle(); // resets to idle
                // set their allies to idle as well
                List<? extends CavallCreature> neighbors = getNeighbors(this, flockRadius, YakDogEntity.class);
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
        // this section stops the standing animation from firing when killed
        if (this.isDeadOrDying()) {
            this.sittingAnimationState.stop();
            this.sittingProcessAnimationState.stop();
            this.standingUpProcessAnimationState.stop();
            return;
        }

        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        // this section is responsible for triggering sitting & standing animations and poses at the right times
        if (this.isCavallCreatureVisuallySitting()) {
            this.standingUpProcessAnimationState.stop();
            this.idleAnimationState.stop();
            if (this.isVisuallySittingDown()) {
                this.sittingProcessAnimationState.startIfStopped(this.tickCount);
                this.sittingAnimationState.stop();
            } else {
                this.sittingProcessAnimationState.stop();
                this.sittingAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.sittingProcessAnimationState.stop();
            this.sittingAnimationState.stop();
            this.idleAnimationState.stop();
            this.standingUpProcessAnimationState.animateWhen(this.isInPoseTransition() && this.getPoseTime() >= 0L, this.tickCount);
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
                .add(Attributes.ATTACK_DAMAGE,3D)
                .add(Attributes.ATTACK_KNOCKBACK,0.5)
                .add(Attributes.ARMOR,0.3)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 16D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }


    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.WHEAT);
    }

    // creates an offspring when bred
    // not sure how to for example have two babies spawn when bred
    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        // return ModEntities.FOGFOX.get().create(pLevel);
        YakDogEntity baby = ModEntities.YAKDOG.get().create(pLevel);
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
        return SoundEvents.COW_STEP;
    }
    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COW_HURT;
    }
    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }


    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            }
            InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
            if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                this.setOrderedToSit(!this.isOrderedToSit());
                if (this.isOrderedToSit()) {
                    this.sitDown(); // trigger pose transition
                } else {
                    this.standUp(); // trigger pose transition
                }
                this.jumping = false;
                this.navigation.stop();
                this.setTarget((LivingEntity)null);
                return InteractionResult.SUCCESS;
            } else {
                return interactionresult;
            }

        } else if (itemstack.is(Items.BEETROOT)) {
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                this.tame(pPlayer);
                this.navigation.stop();
                this.setTarget((LivingEntity)null);
                this.setOrderedToSit(true);
                this.sitDown(); // trigger pose transition on tame
                this.level().broadcastEntityEvent(this, (byte)7);
            } else {
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }
//    @Override
//    public EntityDimensions getDimensions(Pose pose) {
//        if (pose == Pose.SITTING) {
//            return SITTING_DIMENSIONS;
//        }
//        return super.getDimensions(pose);
//    }
    public EntityDimensions getDimensions(Pose pPose) {
        return pPose == Pose.SITTING ? SITTING_DIMENSIONS.scale(this.getScale()) : super.getDimensions(pPose);
    }
}
