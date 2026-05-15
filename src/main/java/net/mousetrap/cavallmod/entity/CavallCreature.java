package net.mousetrap.cavallmod.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CavallCreature extends Animal {
    protected CavallCreature(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    public static final EntityDataAccessor<Boolean> IDLING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> HIDDEN_AMBUSHING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> PURSUING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> FLEEING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> ON_THE_MOVE = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> SPRINT_JUMPING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(CavallCreature.class, EntityDataSerializers.BOOLEAN);


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IDLING, true); // the animal is automatically idling

        this.entityData.define(SLEEPING, false);
        this.entityData.define(SITTING, false);
        this.entityData.define(HIDDEN_AMBUSHING, false);
        this.entityData.define(PURSUING, false);
        this.entityData.define(FLEEING, false);
        this.entityData.define(ATTACKING, false);
        this.entityData.define(ON_THE_MOVE, false);

        this.entityData.define(SPRINT_JUMPING, false);
        this.entityData.define(FLYING, false);
    }

    @Override
    public void tick() {
        super.tick();
        // Automatic Breeding
        // every tick the entity looks for another of its own species
        if (!this.level().isClientSide && !this.isBaby()) {
            // find nearby adult of same species
            List<? extends CavallCreature> nearby = this.level().getEntitiesOfClass(this.getClass(),
                    this.getBoundingBox().inflate(2.0D),
                    f -> f != this && !f.isBaby());
            // looping through every of its own species nearby
            for (CavallCreature mate : nearby) {
                if (this.canMate(mate)) { // the canMate method contains the chance, which is overridden in each entity
                    this.setInLove(null);
                    mate.setInLove(null);
                    AgeableMob baby = this.getBreedOffspring((ServerLevel) this.level(), mate);
                    if (baby != null) {
                        baby.moveTo(this.getX(), this.getY(), this.getZ(), 0, 0); // position baby at parent
                        this.level().addFreshEntity(baby);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return (AgeableMob) this.getType().create(pLevel);
    }

    public boolean isAnimalWithCertainTagNearby(int radius, TagKey<EntityType<?>> inputTag) {
        for (Entity entity : this.level().getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(radius))) {
            // Check if the entity has that tag
            if (entity instanceof CavallCreature creature){
                if (entity.getType().is(inputTag) && !creature.isHiddenAmbushing()) {
                    return true;
                }
            } else { // if the entity is not a CavallCreature, i skip thinking about the isHiddenAmbushing
                if (entity.getType().is(inputTag)) {
                    return true;
                }
            }
        }
        return false;  // No animal with that tag found in the given radius
    }
    public Entity returnEntityWithCertainTagNearby(int radius, TagKey<EntityType<?>> inputTag){
        for (Entity entity : this.level().getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(radius))) {
            // Check if the entity has that tag
            if (entity.getType().is(inputTag)) {
                return entity;
            }
        }
        return null;  // No animal with that tag found in the given radius
    }
    public void resetFlagsToIdle() {
        this.entityData.set(IDLING, true);

        this.entityData.set(HIDDEN_AMBUSHING, false);
        this.entityData.set(FLEEING, false);
        this.entityData.set(SLEEPING, false);
        this.entityData.set(SITTING, false);
        this.entityData.set(PURSUING, false);
        this.entityData.set(ON_THE_MOVE, false);
        this.entityData.set(ATTACKING, false);
        this.entityData.set(FLYING, false);
    }
    public void setAllFlagsToFalse() {
        this.entityData.set(IDLING, false);
        this.entityData.set(HIDDEN_AMBUSHING, false);
        this.entityData.set(FLEEING, false);
        this.entityData.set(SLEEPING, false);
        this.entityData.set(SITTING, false);
        this.entityData.set(PURSUING, false);
        this.entityData.set(ON_THE_MOVE, false);
        this.entityData.set(ATTACKING, false);
        this.entityData.set(FLYING, false);
    }

    // getters and setters
    public boolean isHiddenAmbushing() {
        return this.entityData.get(HIDDEN_AMBUSHING);
    }
    public void setHiddenAmbushingTo(boolean waiting) {
        this.entityData.set(HIDDEN_AMBUSHING, waiting);
    }
    public boolean isFleeing() {
        return this.entityData.get(FLEEING);
    }
    public void setFleeingTo(boolean fleeing) {
        this.entityData.set(FLEEING, fleeing);
    }
    public boolean isPursuing() {
        return this.entityData.get(PURSUING);
    }
    public void setPursuingTo(boolean fighting) {
        this.entityData.set(PURSUING, fighting);
    }
    public boolean isIdling() {
        return this.entityData.get(IDLING);
    }
    public void setIdlingTo(boolean idle) {
        this.entityData.set(IDLING, idle);
    }
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }
    public void setAttackingTo(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }
    public boolean isSprintJumping() {
        return this.entityData.get(SPRINT_JUMPING);
    }
    public void setSprintJumpingTo(boolean sprintJumping) {
        this.entityData.set(SPRINT_JUMPING, sprintJumping);
    }
    public boolean isSleeping() {
        return this.entityData.get(SLEEPING);
    }
    public void setSleepingTo(boolean sleeping) {
        this.entityData.set(SLEEPING, sleeping);
    }
    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }
    public void setSittingTo(boolean sitting) {
        this.entityData.set(SITTING, sitting);
    }
    public boolean isOnTheMove() {
        return this.entityData.get(ON_THE_MOVE);
    }
    public void setOnTheMoveTo(boolean moving) { this.entityData.set(ON_THE_MOVE, moving); }
    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }
    public void setFlyingTo(boolean flying) { this.entityData.set(FLYING, flying); }

    // for on the move
    private Vec3 onTheMoveDirection = Vec3.ZERO;
    public Vec3 getOnTheMoveDirection() {
        return onTheMoveDirection;
    }
    public void setOnTheMoveDirection(Vec3 dir) {
        this.onTheMoveDirection = dir;
    }

    // generic get neighbors method
    // includes babies
    // is called like
    // List<FogFoxEntity> neighbors = getNeighbors(this, flockRadius, FogFoxEntity.class);
    public List<? extends CavallCreature> getNeighbors(CavallCreature mob, double radius, Class<? extends CavallCreature> type) {
        return mob.level().getEntitiesOfClass(
                type,
                mob.getBoundingBox().inflate(radius),
                a -> a != mob
        );
    }

}
