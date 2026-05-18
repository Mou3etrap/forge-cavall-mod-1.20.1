package net.mousetrap.cavallmod.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.mousetrap.cavallmod.CavallMod;

public class ModModelLayers {
    // the layers are sortof like the texture
    public static final ModelLayerLocation FOGFOX_LAYER = new ModelLayerLocation(
            new ResourceLocation(CavallMod.MOD_ID, "fogfox_layer"), "main");
    public static final ModelLayerLocation FLAYFOLK_LAYER = new ModelLayerLocation(
            new ResourceLocation(CavallMod.MOD_ID, "flayfolk_layer"), "main");
    public static final ModelLayerLocation NORTHRUNNER_LAYER = new ModelLayerLocation(
            new ResourceLocation(CavallMod.MOD_ID, "northrunner_layer"), "main");
    public static final ModelLayerLocation YAKDOG_LAYER = new ModelLayerLocation(
            new ResourceLocation(CavallMod.MOD_ID, "yakdog_layer"), "main");

}
