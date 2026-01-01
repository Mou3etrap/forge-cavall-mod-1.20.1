package net.mousetrap.cavallmod.entity.client.FlayFolk;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.client.FlayFolk.FlayFolkModel;
import net.mousetrap.cavallmod.entity.client.ModModelLayers;
import net.mousetrap.cavallmod.entity.custom.FlayFolkEntity;


public class FlayFolkRenderer extends MobRenderer<FlayFolkEntity, FlayFolkModel<FlayFolkEntity>> {

    public FlayFolkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FlayFolkModel<>(pContext.bakeLayer(ModModelLayers.FLAYFOLK_LAYER)), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(FlayFolkEntity pEntity) {
        return new ResourceLocation(CavallMod.MOD_ID, "textures/entity/flayfolk.png");
    }

    @Override
    public void render(FlayFolkEntity pEntity, float pEntityYaw,
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
