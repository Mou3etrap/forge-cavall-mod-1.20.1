package net.mousetrap.cavallmod.entity.client.YakDog;

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
import net.mousetrap.cavallmod.entity.custom.NorthrunnerEntity;
import net.mousetrap.cavallmod.entity.custom.YakDogEntity;

public class YakDogModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "yakdog_model"), "main");
    private final ModelPart root;
    private final ModelPart yak_dog;
    private final ModelPart legs;
    private final ModelPart back_l_leg;
    private final ModelPart bll_bottom;
    private final ModelPart bll_foot;
    private final ModelPart back_r_leg;
    private final ModelPart brl_bottom;
    private final ModelPart brl_foot;
    private final ModelPart front_l_leg;
    private final ModelPart fll_mid;
    private final ModelPart fll_bottom;
    private final ModelPart fll_foot;
    private final ModelPart front_r_leg;
    private final ModelPart frl_mid;
    private final ModelPart frl_bottom;
    private final ModelPart frl_foot;
    private final ModelPart body_horn_l;
    private final ModelPart body_horn_r;
    private final ModelPart body;
    private final ModelPart neck_and_head;
    private final ModelPart head;
    private final ModelPart left_mouth;
    private final ModelPart right_mouth;
    private final ModelPart horn_l;
    private final ModelPart horn_r;
    private final ModelPart tail;

    public YakDogModel(ModelPart root) {
        this.root = root;
        this.yak_dog = root.getChild("yak_dog");
        this.legs = this.yak_dog.getChild("legs");
        this.back_l_leg = this.legs.getChild("back_l_leg");
        this.bll_bottom = this.back_l_leg.getChild("bll_bottom");
        this.bll_foot = this.bll_bottom.getChild("bll_foot");
        this.back_r_leg = this.legs.getChild("back_r_leg");
        this.brl_bottom = this.back_r_leg.getChild("brl_bottom");
        this.brl_foot = this.brl_bottom.getChild("brl_foot");
        this.front_l_leg = this.legs.getChild("front_l_leg");
        this.fll_mid = this.front_l_leg.getChild("fll_mid");
        this.fll_bottom = this.fll_mid.getChild("fll_bottom");
        this.fll_foot = this.fll_bottom.getChild("fll_foot");
        this.front_r_leg = this.legs.getChild("front_r_leg");
        this.frl_mid = this.front_r_leg.getChild("frl_mid");
        this.frl_bottom = this.frl_mid.getChild("frl_bottom");
        this.frl_foot = this.frl_bottom.getChild("frl_foot");
        this.body_horn_l = this.legs.getChild("body_horn_l");
        this.body_horn_r = this.legs.getChild("body_horn_r");
        this.body = this.yak_dog.getChild("body");
        this.neck_and_head = this.body.getChild("neck_and_head");
        this.head = this.neck_and_head.getChild("head");
        this.left_mouth = this.head.getChild("left_mouth");
        this.right_mouth = this.head.getChild("right_mouth");
        this.horn_l = this.head.getChild("horn_l");
        this.horn_r = this.head.getChild("horn_r");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition yak_dog = partdefinition.addOrReplaceChild("yak_dog", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 12.0F));
        PartDefinition legs = yak_dog.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, -12.0F));
        PartDefinition back_l_leg = legs.addOrReplaceChild("back_l_leg", CubeListBuilder.create(), PartPose.offset(6.0F, -26.0F, 11.0F));
        PartDefinition bll_top_r1 = back_l_leg.addOrReplaceChild("bll_top_r1", CubeListBuilder.create().texOffs(112, 18).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition bll_bottom = back_l_leg.addOrReplaceChild("bll_bottom", CubeListBuilder.create(), PartPose.offset(0.9F, 13.0422F, 7.6035F));
        PartDefinition bll_fur_r1 = bll_bottom.addOrReplaceChild("bll_fur_r1", CubeListBuilder.create().texOffs(36, 149).addBox(-5.7F, 2.5921F, -6.1436F, 0.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8F, -0.8667F, 6.0262F, -0.1745F, 0.0F, 0.0F));
        PartDefinition bll_bottom_r1 = bll_bottom.addOrReplaceChild("bll_bottom_r1", CubeListBuilder.create().texOffs(30, 129).addBox(-2.9F, -0.1F, -7.9F, 5.0F, 12.0F, 8.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition bll_foot = bll_bottom.addOrReplaceChild("bll_foot", CubeListBuilder.create().texOffs(134, 133).addBox(-2.0F, -0.624F, -4.0F, 5.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.9F, 10.5818F, -6.1516F));
        PartDefinition backtoe_outer_r1 = bll_foot.addOrReplaceChild("backtoe_outer_r1", CubeListBuilder.create().texOffs(126, 154).addBox(-12.8231F, 11.8245F, -8.0535F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, -11.4485F, 12.1778F, 0.0F, -0.829F, 0.0F));
        PartDefinition midtoe_outer_r1 = bll_foot.addOrReplaceChild("midtoe_outer_r1", CubeListBuilder.create().texOffs(118, 152).addBox(-11.1592F, 11.8245F, -12.9284F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, -11.4485F, 12.1778F, 0.0F, -0.5236F, 0.0F));
        PartDefinition fronttoe_outer_r1 = bll_foot.addOrReplaceChild("fronttoe_outer_r1", CubeListBuilder.create().texOffs(46, 95).addBox(-9.6929F, 11.8245F, -16.1513F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, -11.4485F, 12.1778F, 0.0F, -0.2618F, 0.0F));
        PartDefinition backtoe_inner_r1 = bll_foot.addOrReplaceChild("backtoe_inner_r1", CubeListBuilder.create().texOffs(156, 109).addBox(-0.5795F, 11.8245F, -19.1284F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, -11.4485F, 12.1778F, 0.0F, 0.5236F, 0.0F));
        PartDefinition midtoe_inner_r1 = bll_foot.addOrReplaceChild("midtoe_inner_r1", CubeListBuilder.create().texOffs(122, 61).addBox(3.4457F, 11.8245F, -17.1958F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, -11.4485F, 12.1778F, 0.0F, 0.829F, 0.0F));
        PartDefinition fronttoe_inner_r1 = bll_foot.addOrReplaceChild("fronttoe_inner_r1", CubeListBuilder.create().texOffs(46, 91).addBox(-3.2846F, 11.8245F, -19.3607F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7F, -11.4485F, 12.1778F, 0.0F, 0.2618F, 0.0F));
        PartDefinition back_r_leg = legs.addOrReplaceChild("back_r_leg", CubeListBuilder.create(), PartPose.offset(-6.0F, -26.0F, 11.0F));
        PartDefinition brl_top_r1 = back_r_leg.addOrReplaceChild("brl_top_r1", CubeListBuilder.create().texOffs(0, 117).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition brl_bottom = back_r_leg.addOrReplaceChild("brl_bottom", CubeListBuilder.create(), PartPose.offset(-0.9F, 13.0422F, 7.6035F));
        PartDefinition brl_fur_r1 = brl_bottom.addOrReplaceChild("brl_fur_r1", CubeListBuilder.create().texOffs(44, 149).addBox(2.7F, 2.5921F, -6.1436F, 0.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, -0.8667F, 6.0262F, -0.1745F, 0.0F, 0.0F));
        PartDefinition brl_bottom_r1 = brl_bottom.addOrReplaceChild("brl_bottom_r1", CubeListBuilder.create().texOffs(130, 61).addBox(-2.1F, -0.1F, -7.9F, 5.0F, 12.0F, 8.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition brl_foot = brl_bottom.addOrReplaceChild("brl_foot", CubeListBuilder.create().texOffs(0, 142).addBox(-3.0F, -0.624F, -4.0F, 5.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.9F, 10.5818F, -6.1516F));
        PartDefinition backtoe_outer_r2 = brl_foot.addOrReplaceChild("backtoe_outer_r2", CubeListBuilder.create().texOffs(156, 114).addBox(-6.4725F, 11.8245F, -14.9839F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -11.4485F, 12.1778F, 0.0F, -0.829F, 0.0F));
        PartDefinition midtoe_outer_r2 = brl_foot.addOrReplaceChild("midtoe_outer_r2", CubeListBuilder.create().texOffs(156, 84).addBox(-3.0186F, 11.8245F, -17.6284F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -11.4485F, 12.1778F, 0.0F, -0.5236F, 0.0F));
        PartDefinition fronttoe_outer_r2 = brl_foot.addOrReplaceChild("fronttoe_outer_r2", CubeListBuilder.create().texOffs(156, 119).addBox(-0.6132F, 11.8245F, -18.5842F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -11.4485F, 12.1778F, 0.0F, -0.2618F, 0.0F));
        PartDefinition backtoe_inner_r2 = brl_foot.addOrReplaceChild("backtoe_inner_r2", CubeListBuilder.create().texOffs(156, 79).addBox(7.5612F, 11.8245F, -14.4284F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -11.4485F, 12.1778F, 0.0F, 0.5236F, 0.0F));
        PartDefinition midtoe_inner_r2 = brl_foot.addOrReplaceChild("midtoe_inner_r2", CubeListBuilder.create().texOffs(156, 74).addBox(9.7963F, 11.8245F, -10.2654F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -11.4485F, 12.1778F, 0.0F, 0.829F, 0.0F));
        PartDefinition fronttoe_inner_r2 = brl_foot.addOrReplaceChild("fronttoe_inner_r2", CubeListBuilder.create().texOffs(46, 99).addBox(5.7951F, 11.8245F, -16.9278F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -11.4485F, 12.1778F, 0.0F, 0.2618F, 0.0F));
        PartDefinition front_l_leg = legs.addOrReplaceChild("front_l_leg", CubeListBuilder.create(), PartPose.offset(6.0F, -25.0F, -15.0F));
        PartDefinition fll_r1 = front_l_leg.addOrReplaceChild("fll_r1", CubeListBuilder.create().texOffs(78, 123).addBox(-3.0F, -3.0F, -3.0F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition fll_mid = front_l_leg.addOrReplaceChild("fll_mid", CubeListBuilder.create(), PartPose.offset(-0.01F, 6.3813F, -5.1587F));
        PartDefinition fllm_r1 = fll_mid.addOrReplaceChild("fllm_r1", CubeListBuilder.create().texOffs(130, 81).addBox(-4.99F, -0.01F, -0.01F, 5.0F, 10.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));
        PartDefinition fll_bottom = fll_mid.addOrReplaceChild("fll_bottom", CubeListBuilder.create(), PartPose.offset(0.11F, 8.8521F, 9.204F));
        PartDefinition fllb_fur_r1 = fll_bottom.addOrReplaceChild("fllb_fur_r1", CubeListBuilder.create().texOffs(78, 103).addBox(-3.98F, 7.9086F, 7.0286F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(56, 129).addBox(-5.98F, 7.9086F, 3.0286F, 4.0F, 9.0F, 7.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(3.88F, -8.8435F, -9.1928F, -0.0873F, 0.0F, 0.0F));
        PartDefinition fll_foot = fll_bottom.addOrReplaceChild("fll_foot", CubeListBuilder.create().texOffs(148, 0).addBox(-1.0F, -0.624F, -4.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1F, 7.3905F, -3.5934F));
        PartDefinition backtoe_outer_r3 = fll_foot.addOrReplaceChild("backtoe_outer_r3", CubeListBuilder.create().texOffs(156, 69).addBox(1.4457F, 16.6101F, 2.6885F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.98F, -16.2341F, -5.5994F, 0.0F, -0.829F, 0.0F));
        PartDefinition midtoe_outer_r3 = fll_foot.addOrReplaceChild("midtoe_outer_r3", CubeListBuilder.create().texOffs(156, 64).addBox(-0.7811F, 16.6101F, 1.6072F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.98F, -16.2341F, -5.5994F, 0.0F, -0.5236F, 0.0F));
        PartDefinition fronttoe_outer_r3 = fll_foot.addOrReplaceChild("fronttoe_outer_r3", CubeListBuilder.create().texOffs(156, 127).addBox(-2.8509F, 16.6101F, 0.4197F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.98F, -16.2341F, -5.5994F, 0.0F, -0.2618F, 0.0F));
        PartDefinition backtoe_inner_r3 = fll_foot.addOrReplaceChild("backtoe_inner_r3", CubeListBuilder.create().texOffs(156, 59).addBox(-7.1125F, 16.6101F, -2.3728F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.98F, -16.2341F, -5.5994F, 0.0F, 0.5236F, 0.0F));
        PartDefinition midtoe_inner_r3 = fll_foot.addOrReplaceChild("midtoe_inner_r3", CubeListBuilder.create().texOffs(134, 154).addBox(-7.8234F, 16.6101F, -3.1802F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.98F, -16.2341F, -5.5994F, 0.0F, 0.829F, 0.0F));
        PartDefinition fronttoe_inner_r3 = fll_foot.addOrReplaceChild("fronttoe_inner_r3", CubeListBuilder.create().texOffs(156, 123).addBox(-5.8379F, 16.6101F, -1.6405F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.98F, -16.2341F, -5.5994F, 0.0F, 0.2618F, 0.0F));
        PartDefinition front_r_leg = legs.addOrReplaceChild("front_r_leg", CubeListBuilder.create(), PartPose.offset(-6.0F, -25.0F, -15.0F));
        PartDefinition frl_r1 = front_r_leg.addOrReplaceChild("frl_r1", CubeListBuilder.create().texOffs(106, 123).addBox(-2.0F, -3.0F, -3.0F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition frl_mid = front_r_leg.addOrReplaceChild("frl_mid", CubeListBuilder.create(), PartPose.offset(-0.001F, 6.3735F, -5.1711F));
        PartDefinition frlm_r1 = frl_mid.addOrReplaceChild("frlm_r1", CubeListBuilder.create().texOffs(130, 99).addBox(0.001F, -0.0007F, 0.0013F, 5.0F, 10.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));
        PartDefinition frl_bottom = frl_mid.addOrReplaceChild("frl_bottom", CubeListBuilder.create(), PartPose.offset(0.101F, 8.8599F, 9.2164F));
        PartDefinition frlb_fur_r1 = frl_bottom.addOrReplaceChild("frlb_fur_r1", CubeListBuilder.create().texOffs(26, 149).addBox(2.001F, 7.9229F, 7.0535F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(134, 117).addBox(0.001F, 7.9229F, 3.0535F, 4.0F, 9.0F, 7.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.101F, -8.8599F, -9.2164F, -0.0873F, 0.0F, 0.0F));
        PartDefinition frl_foot = frl_bottom.addOrReplaceChild("frl_foot", CubeListBuilder.create().texOffs(148, 8).addBox(-3.0F, -0.624F, -4.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.9F, 7.3905F, -3.5934F));
        PartDefinition backtoe_outer_r4 = frl_foot.addOrReplaceChild("backtoe_outer_r4", CubeListBuilder.create().texOffs(156, 104).addBox(5.5038F, 16.6265F, -1.7052F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.001F, -16.2505F, -5.623F, 0.0F, -0.829F, 0.0F));
        PartDefinition midtoe_outer_r4 = frl_foot.addOrReplaceChild("midtoe_outer_r4", CubeListBuilder.create().texOffs(156, 99).addBox(4.4104F, 16.6265F, -1.3629F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.001F, -16.2505F, -5.623F, 0.0F, -0.5236F, 0.0F));
        PartDefinition fronttoe_outer_r4 = frl_foot.addOrReplaceChild("fronttoe_outer_r4", CubeListBuilder.create().texOffs(90, 159).addBox(2.9324F, 16.6265F, -1.1055F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.001F, -16.2505F, -5.623F, 0.0F, -0.2618F, 0.0F));
        PartDefinition backtoe_inner_r4 = frl_foot.addOrReplaceChild("backtoe_inner_r4", CubeListBuilder.create().texOffs(156, 94).addBox(-1.9446F, 16.6265F, 0.6381F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.001F, -16.2505F, -5.623F, 0.0F, 0.5236F, 0.0F));
        PartDefinition midtoe_inner_r4 = frl_foot.addOrReplaceChild("midtoe_inner_r4", CubeListBuilder.create().texOffs(156, 89).addBox(-3.8001F, 16.6265F, 1.2454F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.001F, -16.2505F, -5.623F, 0.0F, 0.829F, 0.0F));
        PartDefinition fronttoe_inner_r4 = frl_foot.addOrReplaceChild("fronttoe_inner_r4", CubeListBuilder.create().texOffs(118, 157).addBox(-0.0668F, 16.6265F, -0.0697F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.001F, -16.2505F, -5.623F, 0.0F, 0.2618F, 0.0F));
        PartDefinition body_horn_l = legs.addOrReplaceChild("body_horn_l", CubeListBuilder.create().texOffs(110, 142).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(112, 61).addBox(-1.0F, -2.17F, 10.3727F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -27.0F, -6.0F, 0.1745F, 0.5236F, 0.0F));
        PartDefinition horn3_r1 = body_horn_l.addOrReplaceChild("horn3_r1", CubeListBuilder.create().texOffs(90, 152).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.17F, 13.3727F, 0.1745F, 0.0F, 0.0F));
        PartDefinition horn_r1 = body_horn_l.addOrReplaceChild("horn_r1", CubeListBuilder.create().texOffs(56, 145).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 2.0F, 4.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition body_horn_r = legs.addOrReplaceChild("body_horn_r", CubeListBuilder.create().texOffs(128, 144).addBox(-2.0F, -1.0F, -2.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(78, 118).addBox(-1.0F, -2.17F, 10.3727F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.134F, -27.0F, -5.5F, 0.1745F, -0.5236F, 0.0F));
        PartDefinition horn4_r1 = body_horn_r.addOrReplaceChild("horn4_r1", CubeListBuilder.create().texOffs(104, 152).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.17F, 13.3727F, 0.1745F, 0.0F, 0.0F));
        PartDefinition horn_r2 = body_horn_r.addOrReplaceChild("horn_r2", CubeListBuilder.create().texOffs(146, 144).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 2.0F, 4.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition body = yak_dog.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 66).addBox(-7.0F, -3.0F, -7.0F, 14.0F, 13.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 91).addBox(-6.0F, -3.0F, -30.0F, 12.0F, 15.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-8.0F, -2.0F, -22.0F, 16.0F, 13.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        PartDefinition midbody_fur_l_r1 = body.addOrReplaceChild("midbody_fur_l_r1", CubeListBuilder.create().texOffs(62, 33).addBox(0.0F, -11.0F, -10.0F, 0.0F, 8.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(60, 0).addBox(-10.0F, -11.0F, -10.0F, 0.0F, 8.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 17.0F, -12.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition midbody_fur_r1 = body.addOrReplaceChild("midbody_fur_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -11.0F, -15.0F, 0.0F, 8.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -12.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition neck_and_head = body.addOrReplaceChild("neck_and_head", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -29.0F));
        PartDefinition neck_fur_r1 = neck_and_head.addOrReplaceChild("neck_fur_r1", CubeListBuilder.create().texOffs(78, 142).addBox(0.0F, -7.0F, 0.0F, 0.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, -7.0F, 0.4363F, 0.0F, 0.0F));
        PartDefinition neck_r1 = neck_and_head.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(92, 98).addBox(-5.0F, -9.0F, -7.0F, 10.0F, 16.0F, 9.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
        PartDefinition head = neck_and_head.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -7.0F));
        PartDefinition whiskers_l_r1 = head.addOrReplaceChild("whiskers_l_r1", CubeListBuilder.create().texOffs(149, 18).addBox(2.0F, 1.0F, -4.1F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4776F, -7.6809F, 1.0199F, 0.3007F, -0.18F));
        PartDefinition whiskers_r_r1 = head.addOrReplaceChild("whiskers_r_r1", CubeListBuilder.create().texOffs(142, 18).addBox(-5.0F, 1.0F, -4.1F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4776F, -7.6809F, 1.0199F, -0.3007F, 0.18F));
        PartDefinition snout_r1 = head.addOrReplaceChild("snout_r1", CubeListBuilder.create().texOffs(142, 23).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.3244F, -7.7373F, 1.1781F, 0.0F, 0.0F));
        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(112, 43).addBox(-4.0F, -1.0F, 1.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -7.0F, 0.829F, 0.0F, 0.0F));
        PartDefinition left_mouth = head.addOrReplaceChild("left_mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 6.1225F, -5.0547F));
        PartDefinition left_mouth_r1 = left_mouth.addOrReplaceChild("left_mouth_r1", CubeListBuilder.create().texOffs(14, 153).addBox(0.0F, -2.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.4399F, 0.0F, 0.0F));
        PartDefinition right_mouth = head.addOrReplaceChild("right_mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 6.1225F, -5.0547F));
        PartDefinition right_mouth_r1 = right_mouth.addOrReplaceChild("right_mouth_r1", CubeListBuilder.create().texOffs(66, 154).addBox(-2.0F, -2.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.4399F, 0.0F, 0.0F));
        PartDefinition horn_l = head.addOrReplaceChild("horn_l", CubeListBuilder.create().texOffs(30, 117).addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(148, 52).addBox(0.0F, 0.0F, 5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2006F, -4.5805F, -1.0534F, 0.8114F, 0.5335F, 0.6032F));
        PartDefinition horn3_r2 = horn_l.addOrReplaceChild("horn3_r2", CubeListBuilder.create().texOffs(52, 154).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 6.6744F, 14.8428F, 0.1745F, 0.0F, 0.0F));
        PartDefinition horn2_r1 = horn_l.addOrReplaceChild("horn2_r1", CubeListBuilder.create().texOffs(142, 33).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 10.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition horn_r = head.addOrReplaceChild("horn_r", CubeListBuilder.create().texOffs(148, 43).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(146, 153).addBox(-2.0F, 0.0F, 5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2006F, -4.5805F, -1.0534F, 0.8114F, -0.5335F, -0.6032F));
        PartDefinition horn3_r3 = horn_r.addOrReplaceChild("horn3_r3", CubeListBuilder.create().texOffs(0, 153).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.6744F, 14.8428F, 0.1745F, 0.0F, 0.0F));
        PartDefinition horn2_r2 = horn_r.addOrReplaceChild("horn2_r2", CubeListBuilder.create().texOffs(90, 142).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 10.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 5.0F));
        PartDefinition tail_fur_r1 = tail.addOrReplaceChild("tail_fur_r1", CubeListBuilder.create().texOffs(52, 66).addBox(0.0F, 0.0F, -8.0F, 0.0F, 17.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(92, 66).addBox(-1.0F, 0.0F, -11.0F, 2.0F, 15.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.6942F, 17.0057F, -1.1345F, 0.0F, 0.0F));
        PartDefinition tail_middle_r1 = tail.addOrReplaceChild("tail_middle_r1", CubeListBuilder.create().texOffs(46, 103).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0684F, 7.4027F, -1.1345F, 0.0F, 0.0F));
        PartDefinition main_tail_r1 = tail.addOrReplaceChild("main_tail_r1", CubeListBuilder.create().texOffs(110, 0).addBox(-3.0F, -5.0F, 0.0F, 6.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        // these lines link the idleAnimationState to the animation called NORTHRUNNER_INIT_POSE, etc.
        this.animate(((YakDogEntity) entity).idleAnimationState, ModAnimationDefinitions.YAKDOG_INIT_POSE, ageInTicks, 1f);
        this.animate(((YakDogEntity) entity).sittingProcessAnimationState, ModAnimationDefinitions.YAKDOG_SITTING_ANIM, ageInTicks, 1f);
        this.animate(((YakDogEntity) entity).sittingAnimationState, ModAnimationDefinitions.YAKDOG_SITTING_POSE, ageInTicks, 1f);
        this.animate(((YakDogEntity) entity).standingUpProcessAnimationState, ModAnimationDefinitions.YAKDOG_STANDING_ANIMATION, ageInTicks, 1f);

        // Walking animation
        if (limbSwingAmount > 0.01F) {
            // pMaxAnimationSpeed: makes gait run faster
            this.animateWalk(ModAnimationDefinitions.YAKDOG_WALKING_ANIM,
                    limbSwing, limbSwingAmount, 4f, 2f);
        }
        // lowering root of model for sitting
        // the amount that it is lowered (the 6.545) needs to be the same distance that the entire model is lowered
        // in the NORTHRUNNER_SITTING_PROCESS_ANIMATION
        // and the 20f is the total tick duration of the sitting animation (20 ticks = 1 second)
        if (((YakDogEntity) entity).isCavallCreatureSitting()) {
            long poseTime = ((YakDogEntity) entity).getPoseTime();
            float progress = Math.min(poseTime / 40f, 1.0f);
            this.root.y += 11f * progress;
        } else if (((YakDogEntity) entity).isInPoseTransition()) {
            long poseTime = ((YakDogEntity) entity).getPoseTime();
            float progress = Math.min(poseTime / 40f, 1.0f);
            this.root.y += 11f * (1.0f - progress);
        }
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
        yak_dog.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

//    @Override
//    public ModelPart root() {
//        return northrunner;
//    }

    @Override
    public ModelPart root() {
        return this.root;
    }

}
