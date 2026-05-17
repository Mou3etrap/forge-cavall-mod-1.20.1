package net.mousetrap.cavallmod.entity.client.Northrunner;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.mousetrap.cavallmod.entity.animations.ModAnimationDefinitions;
import net.mousetrap.cavallmod.entity.custom.FogFoxEntity;
import net.mousetrap.cavallmod.entity.custom.NorthrunnerEntity;

public class NorthrunnerModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "northrunner"), "main");
    private final ModelPart northrunner;
    private final ModelPart legs;
    private final ModelPart right_leg;
    private final ModelPart right_bottom;
    private final ModelPart right_foot;
    private final ModelPart left_leg;
    private final ModelPart left_bottom;
    private final ModelPart left_foot;
    private final ModelPart body;
    private final ModelPart neck_and_head;
    private final ModelPart head;
    private final ModelPart tail;

    public NorthrunnerModel(ModelPart root) {
        this.northrunner = root.getChild("northrunner");
        this.legs = this.northrunner.getChild("legs");
        this.right_leg = this.legs.getChild("right_leg");
        this.right_bottom = this.right_leg.getChild("right_bottom");
        this.right_foot = this.right_bottom.getChild("right_foot");
        this.left_leg = this.legs.getChild("left_leg");
        this.left_bottom = this.left_leg.getChild("left_bottom");
        this.left_foot = this.left_bottom.getChild("left_foot");
        this.body = this.northrunner.getChild("body");
        this.neck_and_head = this.body.getChild("neck_and_head");
        this.head = this.neck_and_head.getChild("head");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition northrunner = partdefinition.addOrReplaceChild("northrunner", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));
        PartDefinition legs = northrunner.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 3.0F));
        PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, 0.0F));
        PartDefinition right_top_r1 = right_leg.addOrReplaceChild("right_top_r1", CubeListBuilder.create().texOffs(18, 38).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 10.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, 0.2618F, 0.0F, 0.0F));
        PartDefinition right_bottom = right_leg.addOrReplaceChild("right_bottom", CubeListBuilder.create(), PartPose.offset(-1.0F, 5.0F, 2.0F));
        PartDefinition rightleg_fur_r1 = right_bottom.addOrReplaceChild("rightleg_fur_r1", CubeListBuilder.create().texOffs(62, 64).addBox(0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 2.0F, new CubeDeformation(0.001F))
                .texOffs(54, 22).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.624F, 3.4519F, -0.2618F, 0.0F, 0.0F));
        PartDefinition right_foot = right_bottom.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(58, 43).addBox(-1.0F, 0.376F, -2.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0F, 6.624F, -2.5481F));
        PartDefinition backtoe_outer_r1 = right_foot.addOrReplaceChild("backtoe_outer_r1", CubeListBuilder.create().texOffs(66, 64).addBox(0.0F, 0.376F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, 0.634F, 0.0F, -0.829F, 0.0F));
        PartDefinition midtoe_outer_r1 = right_foot.addOrReplaceChild("midtoe_outer_r1", CubeListBuilder.create().texOffs(68, 22).addBox(0.0F, 0.376F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.5236F, 0.0F));
        PartDefinition fronttoe_outer_r1 = right_foot.addOrReplaceChild("fronttoe_outer_r1", CubeListBuilder.create().texOffs(66, 68).addBox(0.0F, 0.376F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.0F, -0.2618F, 0.0F));
        PartDefinition backtoe_inner_r1 = right_foot.addOrReplaceChild("backtoe_inner_r1", CubeListBuilder.create().texOffs(44, 67).addBox(-1.0F, 0.376F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.5236F, 0.0F));
        PartDefinition midtoe_inner_r1 = right_foot.addOrReplaceChild("midtoe_inner_r1", CubeListBuilder.create().texOffs(36, 65).addBox(-1.0F, 0.376F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0F, 0.634F, 0.0F, 0.829F, 0.0F));
        PartDefinition fronttoe_inner_r1 = right_foot.addOrReplaceChild("fronttoe_inner_r1", CubeListBuilder.create().texOffs(68, 32).addBox(-1.0F, 0.376F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.0F, 0.2618F, 0.0F));
        PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, 0.0F));
        PartDefinition left_top_r1 = left_leg.addOrReplaceChild("left_top_r1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 10.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1.0F, -3.0F, -1.0F, 0.2618F, 0.0F, 0.0F));
        PartDefinition left_bottom = left_leg.addOrReplaceChild("left_bottom", CubeListBuilder.create(), PartPose.offset(1.0F, 5.0F, 2.0F));
        PartDefinition leftleg_fur_r1 = left_bottom.addOrReplaceChild("leftleg_fur_r1", CubeListBuilder.create().texOffs(0, 65).addBox(6.0F, 0.0F, 0.0F, 0.0F, 8.0F, 2.0F, new CubeDeformation(0.001F))
                .texOffs(22, 54).addBox(5.0F, 0.0F, -5.0F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-6.0F, 0.624F, 3.4519F, -0.2618F, 0.0F, 0.0F));
        PartDefinition left_foot = left_bottom.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(58, 35).addBox(-1.0F, 0.376F, -2.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0F, 6.624F, -2.5481F));
        PartDefinition backtoe_outer_r2 = left_foot.addOrReplaceChild("backtoe_outer_r2", CubeListBuilder.create().texOffs(12, 65).addBox(0.0F, 0.376F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, 0.634F, 0.0F, -0.829F, 0.0F));
        PartDefinition midtoe_outer_r2 = left_foot.addOrReplaceChild("midtoe_outer_r2", CubeListBuilder.create().texOffs(20, 67).addBox(0.0F, 0.376F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.5236F, 0.0F));
        PartDefinition fronttoe_outer_r2 = left_foot.addOrReplaceChild("fronttoe_outer_r2", CubeListBuilder.create().texOffs(68, 29).addBox(0.0F, 0.376F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.0F, -0.2618F, 0.0F));
        PartDefinition backtoe_inner_r2 = left_foot.addOrReplaceChild("backtoe_inner_r2", CubeListBuilder.create().texOffs(28, 67).addBox(-1.0F, 0.376F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.5236F, 0.0F));
        PartDefinition midtoe_inner_r2 = left_foot.addOrReplaceChild("midtoe_inner_r2", CubeListBuilder.create().texOffs(4, 65).addBox(-1.0F, 0.376F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0F, 0.634F, 0.0F, 0.829F, 0.0F));
        PartDefinition fronttoe_inner_r2 = left_foot.addOrReplaceChild("fronttoe_inner_r2", CubeListBuilder.create().texOffs(68, 26).addBox(-1.0F, 0.376F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.0F, 0.2618F, 0.0F));
        PartDefinition body = northrunner.addOrReplaceChild("body", CubeListBuilder.create().texOffs(54, 11).addBox(-5.0F, -6.0F, 4.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(36, 49).addBox(-5.0F, -6.9929F, -8.1484F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, -3.0F, -10.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offset(2.0F, -9.0F, 0.0F));
        PartDefinition middlebody_r1 = body.addOrReplaceChild("middlebody_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -6.0F, -7.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.0F, -0.1305F, 3.0086F, -0.1309F, 0.0F, 0.0F));
        PartDefinition chest_fur_r1 = body.addOrReplaceChild("chest_fur_r1", CubeListBuilder.create().texOffs(28, 23).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 13.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -10.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition neck_and_head = body.addOrReplaceChild("neck_and_head", CubeListBuilder.create(), PartPose.offset(-2.0F, -8.1305F, -4.9914F));
        PartDefinition neck_r1 = neck_and_head.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(32, 11).addBox(-2.0F, 0.0F, -7.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.1819F, -0.2254F, -0.1309F, 0.0F, 0.0F));
        PartDefinition head = neck_and_head.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 54).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.001F))
                .texOffs(56, 0).addBox(0.0F, 2.0F, -8.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(36, 60).addBox(-1.0F, 0.0F, -10.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(58, 58).addBox(-0.5F, 0.5F, -15.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -5.0F));
        PartDefinition fur2_r1 = head.addOrReplaceChild("fur2_r1", CubeListBuilder.create().texOffs(54, 64).addBox(-3.0F, -1.2774F, -16.3178F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -5.6363F, 6.5492F, 0.3491F, 0.0F, 0.0F));
        PartDefinition fur3_r1 = head.addOrReplaceChild("fur3_r1", CubeListBuilder.create().texOffs(46, 60).addBox(-3.0F, 0.3183F, -11.1315F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -5.6363F, 6.5492F, 0.3927F, 0.0F, 0.0F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(36, 38).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(58, 51).addBox(-1.0F, 0.0F, 7.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.9392F, 8.3054F, 0.1745F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animate(((NorthrunnerEntity) entity).idleAnimationState, ModAnimationDefinitions.NORTHRUNNER_INIT_POSE, ageInTicks, 1f);
        //this.animate(((NorthrunnerEntity) entity).sittingProcessAnimationState, ModAnimationDefinitions.NORTHRUNNER_SITTING_PROCESS_ANIMATION, ageInTicks, 1f);
        //this.animate(((NorthrunnerEntity) entity).sittingAnimationState, ModAnimationDefinitions.NORTHRUNNER_SITTING_POSE, ageInTicks, 1f);
        //this.animate(((NorthrunnerEntity) entity).standingUpProcessAnimationState, ModAnimationDefinitions.NORTHRUNNER_STANDING_PROCESS_ANIMATION, ageInTicks, 1f);

        // Walking animation
        if (limbSwingAmount > 0.01F) {
            // pMaxAnimationSpeed: makes gait run faster
            this.animateWalk(ModAnimationDefinitions.NORTHRUNNER_WALK,
                    limbSwing, limbSwingAmount, 4f, 2f);
        }
//        if (!((NorthrunnerEntity) entity).isInSittingPose()
//                && ((NorthrunnerEntity) entity).standingUpProcessAnimationState.getAccumulatedTime() > 1000L
//                && limbSwingAmount > 0.01F) {
//            this.animateWalk(ModAnimationDefinitions.NORTHRUNNER_WALK,
//                    limbSwing, limbSwingAmount, 4f, 2f);
//        }
    }

    private void applyHeadRotation(float HeadYaw, float HeadPitch, float pAgeInTicks){
        HeadYaw = Mth.clamp(HeadYaw, -30.0F, 30.0F);
        HeadPitch = Mth.clamp(HeadPitch, -5.0F, 5.0F);
        float NeckYaw = Mth.clamp(HeadYaw, -30.0F, 30.0F);
        float NeckPitch = Mth.clamp(HeadPitch, -5.0F, 5.0F);

        // it's not intuitive, but xRot refers to pitch and yRot to yaw
        this.head.yRot =  HeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = HeadPitch * ((float)Math.PI / 180F);

        this.neck_and_head.yRot = NeckYaw * ((float)Math.PI / 180F);
        this.neck_and_head.xRot = NeckPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        northrunner.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return northrunner;
    }

}
