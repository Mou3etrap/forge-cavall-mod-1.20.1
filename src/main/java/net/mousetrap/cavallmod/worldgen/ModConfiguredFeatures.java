package net.mousetrap.cavallmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.block.entity.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CAVALL_SALT_STONE_KEY = registerKey("cavall_stone_salt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CAVALL_SALT_SAND_KEY = registerKey("cavall_sand_salt");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest sandReplaceable = new BlockMatchTest(Blocks.SAND);

        List<OreConfiguration.TargetBlockState> cavallSaltOres = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.CAVALL_STONE_SALT.get().defaultBlockState()),
                OreConfiguration.target(sandReplaceable, ModBlocks.CAVALL_SAND_SALT.get().defaultBlockState()));

        // probs flawed
        register(context, CAVALL_SALT_STONE_KEY, Feature.ORE, new OreConfiguration(cavallSaltOres, 6));
        register(context, CAVALL_SALT_SAND_KEY, Feature.ORE, new OreConfiguration(sandReplaceable, ModBlocks.CAVALL_SAND.get().defaultBlockState(), 6));

    }



    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(CavallMod.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
