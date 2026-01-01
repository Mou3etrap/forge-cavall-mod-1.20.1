package net.mousetrap.cavallmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.ModEntities;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CavallMod.MOD_ID);

    // adding an item into the register
    public static final RegistryObject<Item> HAPLO_EYE = ITEMS.register("haplo_eye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HAPLO_CLAW = ITEMS.register("haplo_claw",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FOGFOX_SPAWN_EGG = ITEMS.register("fogfox_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FOGFOX, 0x931932, 0x671728,
                    new Item.Properties()));
    public static final RegistryObject<Item> FLAYFOLK_SPAWN_EGG = ITEMS.register("flayfolk_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FOGFOX, 0x671728, 0x931932,
                    new Item.Properties()));


    // registering the deferred register
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
