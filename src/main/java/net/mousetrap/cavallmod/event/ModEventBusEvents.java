package net.mousetrap.cavallmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.entity.custom.FlayFolkEntity;
import net.mousetrap.cavallmod.entity.custom.FogFoxEntity;


@Mod.EventBusSubscriber(modid = CavallMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.FOGFOX.get(), FogFoxEntity.createAttributes().build());
        event.put(ModEntities.FLAYFOLK.get(), FlayFolkEntity.createAttributes().build());
    }
}

