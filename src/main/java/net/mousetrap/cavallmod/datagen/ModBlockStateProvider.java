package net.mousetrap.cavallmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.block.entity.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CavallMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.CAVALL_DIRT);
        logBlock(((RotatedPillarBlock) ModBlocks.XYLO_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.XYLO_WOOD.get()), blockTexture(ModBlocks.XYLO_LOG.get()), blockTexture(ModBlocks.XYLO_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_XYLO_LOG.get()), blockTexture(ModBlocks.STRIPPED_XYLO_LOG.get()), new ResourceLocation(CavallMod.MOD_ID, "block/stripped_xylo_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_XYLO_WOOD.get()), blockTexture(ModBlocks.STRIPPED_XYLO_LOG.get()), blockTexture(ModBlocks.STRIPPED_XYLO_LOG.get()));
        // other blocks here

        blockItem(ModBlocks.XYLO_LOG);
        blockItem(ModBlocks.XYLO_WOOD);
        blockItem(ModBlocks.STRIPPED_XYLO_LOG);
        blockItem(ModBlocks.STRIPPED_XYLO_WOOD);

        blockWithItem(ModBlocks.XYLO_PLANKS);

        leavesBlock(ModBlocks.XYLO_LEAVES);
    }
    private void blockItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(CavallMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()) {
        });
    }
    // custom version of a block type from kaupenjoe
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

}
