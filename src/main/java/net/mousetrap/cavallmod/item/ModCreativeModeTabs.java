package net.mousetrap.cavallmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.block.entity.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CavallMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CAVALL_TAB = CREATIVE_MODE_TABS.register("cavall_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HAPLO_EYE.get()))
                    .title(Component.translatable("creativetab.cavall_tab"))
                    .displayItems((itemDisplayParameters, pOutput) -> {
                        // items in the tab
                        pOutput.accept(ModItems.HAPLO_EYE.get());
                        pOutput.accept(ModItems.HAPLO_CLAW.get());
                        pOutput.accept(ModItems.FOGFOX_SPAWN_EGG.get());
                        pOutput.accept(ModItems.FLAYFOLK_SPAWN_EGG.get());

                        pOutput.accept(ModBlocks.CAVALL_DIRT.get());

                        pOutput.accept(ModBlocks.XYLO_LOG.get());
                        pOutput.accept(ModBlocks.XYLO_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_XYLO_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_XYLO_WOOD.get());
                        pOutput.accept(ModBlocks.XYLO_PLANKS.get());
                        pOutput.accept(ModBlocks.XYLO_LEAVES.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
