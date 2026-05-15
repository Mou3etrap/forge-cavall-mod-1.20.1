package net.mousetrap.cavallmod.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mousetrap.cavallmod.entity.CavallCreature;
import org.joml.Matrix4f;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityStateOverlayRenderer {

    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Post<?, ?> event) {
        if (!(event.getEntity() instanceof CavallCreature mob)) return;

        if (!mob.isPursuing() && !mob.isFleeing()) return;

        PoseStack pose = event.getPoseStack();
        MultiBufferSource buffer = event.getMultiBufferSource();

        pose.pushPose();

        // Move above the head
        pose.translate(0.0, mob.getBbHeight() + 0.5, 0.0);

        // Face camera
        pose.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        pose.scale(-0.025F, -0.025F, 0.025F);

        float r = mob.isPursuing() ? 1f : 1f;
        float g = mob.isPursuing() ? 0f : 1f;
        float b = 0f;

        renderCircle(pose, buffer, r, g, b);

        pose.popPose();
    }
    private static void renderCircle(PoseStack pose, MultiBufferSource buffer, float r, float g, float b) {
        VertexConsumer vc = buffer.getBuffer(RenderType.gui());

        Matrix4f mat = pose.last().pose();
        float radius = 4f;

        for (int i = 0; i < 16; i++) {
            double a1 = 2 * Math.PI * i / 16;
            double a2 = 2 * Math.PI * (i + 1) / 16;

            vc.vertex(mat, (float)Math.cos(a1)*radius, (float)Math.sin(a1)*radius, 0)
                    .color(r, g, b, 1f).endVertex();
            vc.vertex(mat, (float)Math.cos(a2)*radius, (float)Math.sin(a2)*radius, 0)
                    .color(r, g, b, 1f).endVertex();
        }
    }

}

