package net.mousetrap.cavallmod.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.mousetrap.cavallmod.block.entity.ModBlocks;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.CAVALL_DIRT.get());
        this.dropSelf(ModBlocks.XYLO_LOG.get());
        this.dropSelf(ModBlocks.XYLO_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_XYLO_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_XYLO_WOOD.get());

        // example of dropsOneToFive method
        // this.add(ModBlocks.BLOCK_NAME.get()), block -> dropsOneToFive(ModBlocks.BLOCK_NAME.get(), ModItems.ITEM_NAME.get()));
    }

    protected LootTable.Builder dropsOneToFive(Block pBlock, Item item){
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    // this means all blocks in the deferred register must have a loot table, ie, they must drop something (probs just themselves)
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
