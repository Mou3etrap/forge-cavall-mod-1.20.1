package net.mousetrap.cavallmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.client.FogFox.FogFoxModel;
import net.mousetrap.cavallmod.entity.client.FlayFolk.FlayFolkModel;
import net.mousetrap.cavallmod.entity.client.ModModelLayers;

@Mod.EventBusSubscriber(modid = CavallMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.FOGFOX_LAYER, FogFoxModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FLAYFOLK_LAYER, FlayFolkModel::createBodyLayer);
    }
}
