package net.mousetrap.cavallmod.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.mousetrap.cavallmod.CavallMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> CAVALL_SALT_STONE_PLACED_KEY = registerKey("cavall_stone_salt_placed");
    public static final ResourceKey<PlacedFeature> CAVALL_SALT_SAND_PLACED_KEY = registerKey("cavall_sand_salt_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, CAVALL_SALT_SAND_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CAVALL_SALT_SAND_KEY),
                ModOrePlacement.commonOrePlacement(12, // number of veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(20))));
        // will spawn from y-level -20 to +20
        register(context, CAVALL_SALT_STONE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CAVALL_SALT_STONE_KEY),
                ModOrePlacement.commonOrePlacement(12, // number of veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(20))));
        // will spawn from y-level -40 to +20
    }

    public static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(CavallMod.MOD_ID, name));
    }
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
