package net.mousetrap.cavallmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.block.entity.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CavallMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // this.tag(ModTags.blocks.)
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.XYLO_PLANKS.get())
                .add(ModBlocks.XYLO_LOG.get())
                .add(ModBlocks.XYLO_WOOD.get())
                .add(ModBlocks.STRIPPED_XYLO_WOOD.get())
                .add(ModBlocks.STRIPPED_XYLO_LOG.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.XYLO_PLANKS.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.XYLO_LOG.get())
                .add(ModBlocks.XYLO_WOOD.get())
                .add(ModBlocks.STRIPPED_XYLO_WOOD.get())
                .add(ModBlocks.STRIPPED_XYLO_LOG.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.XYLO_LOG.get())
                .add(ModBlocks.XYLO_WOOD.get())
                .add(ModBlocks.STRIPPED_XYLO_LOG.get())
                .add(ModBlocks.STRIPPED_XYLO_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.XYLO_PLANKS.get());
    }
}
