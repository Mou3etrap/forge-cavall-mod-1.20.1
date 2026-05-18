package net.mousetrap.cavallmod.entity.client.YakDog;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.client.ModModelLayers;
import net.mousetrap.cavallmod.entity.client.Northrunner.NorthrunnerModel;
import net.mousetrap.cavallmod.entity.custom.NorthrunnerEntity;
import net.mousetrap.cavallmod.entity.custom.YakDogEntity;


public class YakDogRenderer extends MobRenderer<YakDogEntity, YakDogModel<YakDogEntity>> {

    public YakDogRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new YakDogModel<>(pContext.bakeLayer(ModModelLayers.YAKDOG_LAYER)), 1.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(YakDogEntity pEntity) {
        return new ResourceLocation(CavallMod.MOD_ID, "textures/entity/yakdog.png");
    }

    @Override
    public void render(YakDogEntity pEntity, float pEntityYaw,
                       float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        // in kaupenjoes video, he writes pMatrixStack instead of pPoseStack

        // scales babies down to a smaller size
        if(pEntity.isBaby()){
            pPoseStack.scale(0.4f,0.4f,0.4f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
