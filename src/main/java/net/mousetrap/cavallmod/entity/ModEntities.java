package net.mousetrap.cavallmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.custom.FogFoxEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CavallMod.MOD_ID);

    // the .sized is the hitbox size
    // the Mobcategory also looks important --> look into that
    public static final RegistryObject<EntityType<FogFoxEntity>> FOGFOX =
            ENTITY_TYPES.register("fogfox", () -> EntityType.Builder.of(FogFoxEntity::new,
                    MobCategory.CREATURE).sized(0.8F,1.5F).build("fogfox"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
