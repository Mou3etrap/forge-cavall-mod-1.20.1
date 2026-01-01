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
import net.mousetrap.cavallmod.entity.custom.FlayFolkEntity;
import org.jetbrains.annotations.Nullable;

public class CavallCreature extends Animal {
    protected CavallCreature(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    public static final EntityDataAccessor<Boolean> WAITING_TO_AMBUSH = SynchedEntityData.defineId(FlayFolkEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> FIGHTING = SynchedEntityData.defineId(FlayFolkEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> FLEEING = SynchedEntityData.defineId(FlayFolkEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> ALERT = SynchedEntityData.defineId(FlayFolkEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(FlayFolkEntity.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WAITING_TO_AMBUSH, false);
        this.entityData.define(FIGHTING, false);
        this.entityData.define(FLEEING, false);
        this.entityData.define(ALERT, false);
        this.entityData.define(ATTACKING, false);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return (AgeableMob) this.getType().create(pLevel);
    }

    @Override
    public boolean canFallInLove() { // makes breeding only occur when not fleeing, not alert, and not fighting
        return super.canFallInLove()
                && !this.isFighting()
                && !this.isFleeing()
                && !this.isAlert()
                && !this.isWaitingToAmbush();
    }

    public boolean isPredatorNearby(int radius, TagKey<EntityType<?>> inputTag) {
        if (inputTag == null) {
            System.out.println("Predator tag is null!");
            return false;
        }
        for (Entity entity : this.level().getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(radius))) {
            System.out.println("Nearby entity: " + EntityType.getKey(entity.getType()));
        }

        for (Entity entity : this.level().getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(radius))) {
            // Check if the entity is a predator (tagged with the predator tag)
            System.out.println("Checking entity: " + EntityType.getKey(entity.getType()));
            System.out.println("Against tag: " + inputTag.location());
            if (entity.getType().is(inputTag)) {
                System.out.println("Detected predator nearby!");
                return true;
            }
        }
        return false;  // No predator found
    }
    public boolean isPreyNearby(int radius, TagKey<EntityType<?>> inputTag) {
        for (Entity entity : this.level().getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(radius))) {
            // Check if the entity is prey (tagged with the prey tag)
            if (entity.getType().is(inputTag)) {
                return true;
            }
        }
        return false;  // No prey found
    }

    public boolean isWaitingToAmbush() {
        return this.entityData.get(WAITING_TO_AMBUSH);
    }
    public void setWaitingToAmbushTo(boolean waiting) {
        this.entityData.set(WAITING_TO_AMBUSH, waiting);
    }
    public boolean isFleeing() {
        return this.entityData.get(FLEEING);
    }
    public void setFleeingTo(boolean fleeing) {
        this.entityData.set(FLEEING, fleeing);
    }
    public boolean isFighting() {
        return this.entityData.get(FIGHTING);
    }
    public void setFightingTo(boolean fighting) {
        this.entityData.set(FIGHTING, fighting);
    }
    public boolean isAlert() {
        return this.entityData.get(ALERT);
    }
    public void setAlertTo(boolean alert) {
        this.entityData.set(ALERT, alert);
    }
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }
    public void setAttackingTo(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }
}
