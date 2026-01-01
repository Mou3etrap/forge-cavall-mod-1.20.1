package net.mousetrap.cavallmod.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.mousetrap.cavallmod.CavallMod;

public class ModTags {
    public static final TagKey<Item> ANIMAL_DROP = registerItemTag("animal_drop");

    public static final TagKey<EntityType<?>> FOGFOX_PREDATORS = registerEntityTag("fogfox_predators");
    public static final TagKey<EntityType<?>> FOGFOX_PREY = registerEntityTag("fogfox_prey");
    public static final TagKey<EntityType<?>> FLAYFOLK_PREDATORS = registerEntityTag("flayfolk_predators");
    public static final TagKey<EntityType<?>> FLAYFOLK_PREY = registerEntityTag("flayfolk_prey");


    private static TagKey<EntityType<?>> registerEntityTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(CavallMod.MOD_ID, name));
    }
    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(CavallMod.MOD_ID, name));
    }
    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(CavallMod.MOD_ID, name));
    }
    private static TagKey<Biome> registerBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(CavallMod.MOD_ID, name));
    }
    private static TagKey<Structure> registerStructureTag(String name) {
        return TagKey.create(Registries.STRUCTURE, new ResourceLocation(CavallMod.MOD_ID, name));
    }
}