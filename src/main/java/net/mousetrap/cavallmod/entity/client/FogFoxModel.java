package net.mousetrap.cavallmod.entity.client;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.mousetrap.cavallmod.entity.animations.ModAnimationDefinitions;
import net.mousetrap.cavallmod.entity.custom.FogFoxEntity;

public class FogFoxModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart fogfox;
	private final ModelPart armsandeyes;
	private final ModelPart leftarm;
	private final ModelPart lowerarmleft;
	private final ModelPart lefthand;
	private final ModelPart rightarm;
	private final ModelPart lowerarmright;
	private final ModelPart righthand;
	private final ModelPart rightpedipalp;
	private final ModelPart middle_right_ped;
	private final ModelPart upper_right_ped;
	private final ModelPart leftpedipalp;
	private final ModelPart middle_left_ped;
	private final ModelPart upper_left_ped;
	private final ModelPart eyes;
	private final ModelPart bodygroup;
	private final ModelPart mouthgroup;
	private final ModelPart mouth1;
	private final ModelPart mouth2;
	private final ModelPart mouth3;
	private final ModelPart mouth4;
	private final ModelPart body;
	private final ModelPart middlebody;
	private final ModelPart lowerbodyandlegs;
	private final ModelPart leg1;
	private final ModelPart upper_leg_1;
	private final ModelPart middle_leg_1;
	private final ModelPart lower_leg_1;
	private final ModelPart foot_1;
	private final ModelPart leg2;
	private final ModelPart upper_leg_2;
	private final ModelPart middle_leg_2;
	private final ModelPart lower_leg_2;
	private final ModelPart foot_2;
	private final ModelPart leg3;
	private final ModelPart upper_leg_3;
	private final ModelPart middle_leg_3;
	private final ModelPart lower_leg_3;
	private final ModelPart foot_3;
	private final ModelPart leg4;
	private final ModelPart upper_leg_4;
	private final ModelPart middle_leg_4;
	private final ModelPart lower_leg_4;
	private final ModelPart foot_4;

	public FogFoxModel(ModelPart root) {
		this.fogfox = root.getChild("fogfox");
		this.armsandeyes = this.fogfox.getChild("armsandeyes");
		this.leftarm = this.armsandeyes.getChild("leftarm");
		this.lowerarmleft = this.leftarm.getChild("lowerarmleft");
		this.lefthand = this.lowerarmleft.getChild("lefthand");
		this.rightarm = this.armsandeyes.getChild("rightarm");
		this.lowerarmright = this.rightarm.getChild("lowerarmright");
		this.righthand = this.lowerarmright.getChild("righthand");
		this.rightpedipalp = this.armsandeyes.getChild("rightpedipalp");
		this.middle_right_ped = this.rightpedipalp.getChild("middle_right_ped");
		this.upper_right_ped = this.middle_right_ped.getChild("upper_right_ped");
		this.leftpedipalp = this.armsandeyes.getChild("leftpedipalp");
		this.middle_left_ped = this.leftpedipalp.getChild("middle_left_ped");
		this.upper_left_ped = this.middle_left_ped.getChild("upper_left_ped");
		this.eyes = this.armsandeyes.getChild("eyes");
		this.bodygroup = this.fogfox.getChild("bodygroup");
		this.mouthgroup = this.bodygroup.getChild("mouthgroup");
		this.mouth1 = this.mouthgroup.getChild("mouth1");
		this.mouth2 = this.mouthgroup.getChild("mouth2");
		this.mouth3 = this.mouthgroup.getChild("mouth3");
		this.mouth4 = this.mouthgroup.getChild("mouth4");
		this.body = this.bodygroup.getChild("body");
		this.middlebody = this.body.getChild("middlebody");
		this.lowerbodyandlegs = this.body.getChild("lowerbodyandlegs");
		this.leg1 = this.lowerbodyandlegs.getChild("leg1");
		this.upper_leg_1 = this.leg1.getChild("upper_leg_1");
		this.middle_leg_1 = this.upper_leg_1.getChild("middle_leg_1");
		this.lower_leg_1 = this.middle_leg_1.getChild("lower_leg_1");
		this.foot_1 = this.lower_leg_1.getChild("foot_1");
		this.leg2 = this.lowerbodyandlegs.getChild("leg2");
		this.upper_leg_2 = this.leg2.getChild("upper_leg_2");
		this.middle_leg_2 = this.leg2.getChild("middle_leg_2");
		this.lower_leg_2 = this.middle_leg_2.getChild("lower_leg_2");
		this.foot_2 = this.lower_leg_2.getChild("foot_2");
		this.leg3 = this.lowerbodyandlegs.getChild("leg3");
		this.upper_leg_3 = this.leg3.getChild("upper_leg_3");
		this.middle_leg_3 = this.leg3.getChild("middle_leg_3");
		this.lower_leg_3 = this.middle_leg_3.getChild("lower_leg_3");
		this.foot_3 = this.lower_leg_3.getChild("foot_3");
		this.leg4 = this.lowerbodyandlegs.getChild("leg4");
		this.upper_leg_4 = this.leg4.getChild("upper_leg_4");
		this.middle_leg_4 = this.leg4.getChild("middle_leg_4");
		this.lower_leg_4 = this.middle_leg_4.getChild("lower_leg_4");
		this.foot_4 = this.lower_leg_4.getChild("foot_4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition fogfox = partdefinition.addOrReplaceChild("fogfox", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition armsandeyes = fogfox.addOrReplaceChild("armsandeyes", CubeListBuilder.create(), PartPose.offset(9.5F, -18.2F, 0.0F));

		PartDefinition leftarm = armsandeyes.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(24, 13).addBox(-0.5F, -0.8F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 0.0F, 0.0F));

		PartDefinition lowerarmleft = leftarm.addOrReplaceChild("lowerarmleft", CubeListBuilder.create(), PartPose.offset(7.0F, 0.2F, 0.0F));

		PartDefinition lower_r1 = lowerarmleft.addOrReplaceChild("lower_r1", CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, -0.55F, -1.0F, 7.5F, 1.5F, 1.5F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.2F, -6.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition lefthand = lowerarmleft.addOrReplaceChild("lefthand", CubeListBuilder.create(), PartPose.offset(0.0F, -0.2F, -7.0F));

		PartDefinition fingerl_r1 = lefthand.addOrReplaceChild("fingerl_r1", CubeListBuilder.create().texOffs(56, 32).addBox(-1.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.3066F, 0.0F, -0.0588F, 0.0F, 2.7489F, 0.0F));

		PartDefinition fingerl_r2 = lefthand.addOrReplaceChild("fingerl_r2", CubeListBuilder.create().texOffs(32, 56).addBox(0.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.134F, 0.0F, -0.5F, 0.0F, 2.0944F, 0.0F));

		PartDefinition fingerl_r3 = lefthand.addOrReplaceChild("fingerl_r3", CubeListBuilder.create().texOffs(56, 30).addBox(-2.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.8066F, 0.0F, -0.0588F, 0.0F, -2.7489F, 0.0F));

		PartDefinition fingerl_r4 = lefthand.addOrReplaceChild("fingerl_r4", CubeListBuilder.create().texOffs(56, 28).addBox(-3.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.366F, 0.0F, -0.5F, 0.0F, -2.0944F, 0.0F));

		PartDefinition middlefingerl_r1 = lefthand.addOrReplaceChild("middlefingerl_r1", CubeListBuilder.create().texOffs(56, 26).addBox(-3.0F, -0.05F, -0.55F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.2F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition wrist_r1 = lefthand.addOrReplaceChild("wrist_r1", CubeListBuilder.create().texOffs(56, 23).addBox(-2.5F, -0.05F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition rightarm = armsandeyes.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(24, 17).addBox(-7.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, 0.2F, 0.0F));

		PartDefinition lowerarmright = rightarm.addOrReplaceChild("lowerarmright", CubeListBuilder.create(), PartPose.offset(-7.0F, 0.0F, 0.0F));

		PartDefinition lower_r2 = lowerarmright.addOrReplaceChild("lower_r2", CubeListBuilder.create().texOffs(20, 28).addBox(-6.5F, -0.55F, -1.0F, 7.5F, 1.5F, 1.5F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.2F, -6.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition righthand = lowerarmright.addOrReplaceChild("righthand", CubeListBuilder.create(), PartPose.offset(0.0F, -0.2F, -7.0F));

		PartDefinition fingerr_r1 = righthand.addOrReplaceChild("fingerr_r1", CubeListBuilder.create().texOffs(56, 40).addBox(-1.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.8066F, 0.0F, -0.0588F, 0.0F, 2.7489F, 0.0F));

		PartDefinition fingerr_r2 = righthand.addOrReplaceChild("fingerr_r2", CubeListBuilder.create().texOffs(40, 56).addBox(0.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.366F, 0.0F, -0.5F, 0.0F, 2.0944F, 0.0F));

		PartDefinition fingerr_r3 = righthand.addOrReplaceChild("fingerr_r3", CubeListBuilder.create().texOffs(56, 38).addBox(-3.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.134F, 0.0F, -0.5F, 0.0F, -2.0944F, 0.0F));

		PartDefinition fingerr_r4 = righthand.addOrReplaceChild("fingerr_r4", CubeListBuilder.create().texOffs(56, 36).addBox(-2.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.3066F, 0.0F, -0.0588F, 0.0F, -2.7489F, 0.0F));

		PartDefinition middlefinger_r1 = righthand.addOrReplaceChild("middlefinger_r1", CubeListBuilder.create().texOffs(56, 34).addBox(0.0F, -0.05F, -0.55F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-0.2F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition wrist_r2 = righthand.addOrReplaceChild("wrist_r2", CubeListBuilder.create().texOffs(24, 56).addBox(0.5F, -0.05F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition rightpedipalp = armsandeyes.addOrReplaceChild("rightpedipalp", CubeListBuilder.create().texOffs(56, 42).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.5F, -6.8F, 0.0F));

		PartDefinition middle_right_ped = rightpedipalp.addOrReplaceChild("middle_right_ped", CubeListBuilder.create(), PartPose.offset(-2.0F, -0.5F, -0.5F));

		PartDefinition middler_r1 = middle_right_ped.addOrReplaceChild("middler_r1", CubeListBuilder.create().texOffs(44, 17).addBox(-4.5F, -0.5F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition upper_right_ped = middle_right_ped.addOrReplaceChild("upper_right_ped", CubeListBuilder.create().texOffs(32, 10).addBox(-4.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.5F));

		PartDefinition leftpedipalp = armsandeyes.addOrReplaceChild("leftpedipalp", CubeListBuilder.create().texOffs(56, 44).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, -6.8F, 0.0F));

		PartDefinition middle_left_ped = leftpedipalp.addOrReplaceChild("middle_left_ped", CubeListBuilder.create(), PartPose.offset(2.0F, -0.5F, -0.5F));

		PartDefinition middlel_r1 = middle_left_ped.addOrReplaceChild("middlel_r1", CubeListBuilder.create().texOffs(44, 19).addBox(-0.5F, -0.5F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition upper_left_ped = middle_left_ped.addOrReplaceChild("upper_left_ped", CubeListBuilder.create().texOffs(48, 21).addBox(0.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.5F));

		PartDefinition eyes = armsandeyes.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(42, 7).addBox(-2.0F, -24.5F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(46, 28).addBox(-2.0F, -24.5F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(46, 33).addBox(1.0F, -24.5F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(16, 47).addBox(1.0F, -24.5F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.5F, 18.2F, 0.0F));

		PartDefinition eye7_r1 = eyes.addOrReplaceChild("eye7_r1", CubeListBuilder.create().texOffs(46, 43).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -24.0F, -1.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition eye6_r1 = eyes.addOrReplaceChild("eye6_r1", CubeListBuilder.create().texOffs(46, 38).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -24.0F, 1.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition eye3_r1 = eyes.addOrReplaceChild("eye3_r1", CubeListBuilder.create().texOffs(44, 12).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -24.0F, 1.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition eye2_r1 = eyes.addOrReplaceChild("eye2_r1", CubeListBuilder.create().texOffs(16, 42).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -24.0F, -1.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bodygroup = fogfox.addOrReplaceChild("bodygroup", CubeListBuilder.create(), PartPose.offset(-1.0F, -7.0F, 0.0F));

		PartDefinition mouthgroup = bodygroup.addOrReplaceChild("mouthgroup", CubeListBuilder.create(), PartPose.offset(1.0F, -20.0F, 0.0F));

		PartDefinition mouth1 = mouthgroup.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(26, 42).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth2 = mouthgroup.addOrReplaceChild("mouth2", CubeListBuilder.create().texOffs(26, 45).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth3 = mouthgroup.addOrReplaceChild("mouth3", CubeListBuilder.create().texOffs(12, 57).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth4 = mouthgroup.addOrReplaceChild("mouth4", CubeListBuilder.create().texOffs(32, 58).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = bodygroup.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 21).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -17.0F, 0.0F));

		PartDefinition middlebody = body.addOrReplaceChild("middlebody", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition lowerbodyandlegs = body.addOrReplaceChild("lowerbodyandlegs", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.1F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.1F, 0.0F));

		PartDefinition leg1 = lowerbodyandlegs.addOrReplaceChild("leg1", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, 2.0F));

		PartDefinition upper_leg_1 = leg1.addOrReplaceChild("upper_leg_1", CubeListBuilder.create(), PartPose.offset(-0.2241F, -0.6863F, 0.4098F));

		PartDefinition upper_r1 = upper_leg_1.addOrReplaceChild("upper_r1", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition middle_leg_1 = upper_leg_1.addOrReplaceChild("middle_leg_1", CubeListBuilder.create(), PartPose.offset(0.0F, 3.621F, 2.6245F));

		PartDefinition middle_r1 = middle_leg_1.addOrReplaceChild("middle_r1", CubeListBuilder.create().texOffs(40, 21).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 5.0F, 2.5F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_1 = middle_leg_1.addOrReplaceChild("lower_leg_1", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 4.0F));

		PartDefinition lower_r3 = lower_leg_1.addOrReplaceChild("lower_r3", CubeListBuilder.create().texOffs(30, 32).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -0.0409F, 0.0089F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot_1 = lower_leg_1.addOrReplaceChild("foot_1", CubeListBuilder.create().texOffs(34, 48).addBox(-1.0F, 0.0062F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.9591F, 0.0089F));

		PartDefinition toe_r1 = foot_1.addOrReplaceChild("toe_r1", CubeListBuilder.create().texOffs(56, 52).addBox(-1.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition toe_r2 = foot_1.addOrReplaceChild("toe_r2", CubeListBuilder.create().texOffs(50, 0).addBox(-1.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 1.4142F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r3 = foot_1.addOrReplaceChild("toe_r3", CubeListBuilder.create().texOffs(48, 56).addBox(0.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.99F, 1.0F, -0.0041F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition toe_r4 = foot_1.addOrReplaceChild("toe_r4", CubeListBuilder.create().texOffs(8, 49).addBox(0.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.99F, 1.0F, 1.4101F, 0.0F, -0.4363F, 0.0F));

		PartDefinition outer_claw_1_r1 = foot_1.addOrReplaceChild("outer_claw_1_r1", CubeListBuilder.create().texOffs(0, 49).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, -0.866F, 3.2321F, -1.0472F, 0.0F, 0.0F));

		PartDefinition inner_claw_1_r1 = foot_1.addOrReplaceChild("inner_claw_1_r1", CubeListBuilder.create().texOffs(42, 48).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition leg2 = lowerbodyandlegs.addOrReplaceChild("leg2", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, 2.0F));

		PartDefinition upper_leg_2 = leg2.addOrReplaceChild("upper_leg_2", CubeListBuilder.create(), PartPose.offset(0.2241F, -0.6863F, 0.4098F));

		PartDefinition upper_r2 = upper_leg_2.addOrReplaceChild("upper_r2", CubeListBuilder.create().texOffs(10, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition middle_leg_2 = leg2.addOrReplaceChild("middle_leg_2", CubeListBuilder.create(), PartPose.offset(0.2241F, 3.0F, 4.0F));

		PartDefinition middle_r2 = middle_leg_2.addOrReplaceChild("middle_r2", CubeListBuilder.create().texOffs(38, 41).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 5.0F, 2.5F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.0653F, -0.9657F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_2 = middle_leg_2.addOrReplaceChild("lower_leg_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.9347F, 3.0343F));

		PartDefinition lower_r4 = lower_leg_2.addOrReplaceChild("lower_r4", CubeListBuilder.create().texOffs(38, 32).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -0.0409F, 0.0089F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot_2 = lower_leg_2.addOrReplaceChild("foot_2", CubeListBuilder.create().texOffs(50, 48).addBox(-1.0F, 0.0062F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.9591F, 0.0089F));

		PartDefinition toe_r5 = foot_2.addOrReplaceChild("toe_r5", CubeListBuilder.create().texOffs(16, 52).addBox(-1.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.99F, 1.0F, 1.4101F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r6 = foot_2.addOrReplaceChild("toe_r6", CubeListBuilder.create().texOffs(0, 57).addBox(0.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition toe_r7 = foot_2.addOrReplaceChild("toe_r7", CubeListBuilder.create().texOffs(52, 8).addBox(0.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 1.4142F, 0.0F, -0.4363F, 0.0F));

		PartDefinition toe_r8 = foot_2.addOrReplaceChild("toe_r8", CubeListBuilder.create().texOffs(54, 56).addBox(-1.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.99F, 1.0F, -0.0041F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition inner_claw_2_r1 = foot_2.addOrReplaceChild("inner_claw_2_r1", CubeListBuilder.create().texOffs(52, 4).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition outer_claw_2_r1 = foot_2.addOrReplaceChild("outer_claw_2_r1", CubeListBuilder.create().texOffs(16, 56).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, -0.866F, 3.2321F, -1.0472F, 0.0F, 0.0F));

		PartDefinition leg3 = lowerbodyandlegs.addOrReplaceChild("leg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 2.0F, -2.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition upper_leg_3 = leg3.addOrReplaceChild("upper_leg_3", CubeListBuilder.create(), PartPose.offset(-0.2257F, -0.6863F, 0.3169F));

		PartDefinition upper_r3 = upper_leg_3.addOrReplaceChild("upper_r3", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0016F, 0.0F, 0.0928F, 0.4363F, 0.0F, 0.0F));

		PartDefinition middle_leg_3 = leg3.addOrReplaceChild("middle_leg_3", CubeListBuilder.create(), PartPose.offset(-0.2241F, 3.0F, 3.0F));

		PartDefinition middle_r3 = middle_leg_3.addOrReplaceChild("middle_r3", CubeListBuilder.create().texOffs(30, 41).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 5.0F, 2.5F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.0653F, 0.0343F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_3 = middle_leg_3.addOrReplaceChild("lower_leg_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.9347F, 4.0343F));

		PartDefinition lower_r5 = lower_leg_3.addOrReplaceChild("lower_r5", CubeListBuilder.create().texOffs(8, 40).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -0.0409F, 0.0089F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot_3 = lower_leg_3.addOrReplaceChild("foot_3", CubeListBuilder.create().texOffs(48, 23).addBox(-1.0F, 0.0062F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.9591F, 0.0089F));

		PartDefinition toe_r9 = foot_3.addOrReplaceChild("toe_r9", CubeListBuilder.create().texOffs(40, 52).addBox(0.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.99F, 1.0F, 1.4101F, 0.0F, -0.4363F, 0.0F));

		PartDefinition toe_r10 = foot_3.addOrReplaceChild("toe_r10", CubeListBuilder.create().texOffs(40, 28).addBox(-1.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition toe_r11 = foot_3.addOrReplaceChild("toe_r11", CubeListBuilder.create().texOffs(32, 52).addBox(-1.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 1.4142F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r12 = foot_3.addOrReplaceChild("toe_r12", CubeListBuilder.create().texOffs(6, 57).addBox(0.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.99F, 1.0F, -0.0041F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition outer_claw_3_r1 = foot_3.addOrReplaceChild("outer_claw_3_r1", CubeListBuilder.create().texOffs(56, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, -0.866F, 3.2321F, -1.0472F, 0.0F, 0.0F));

		PartDefinition inner_claw_3_r1 = foot_3.addOrReplaceChild("inner_claw_3_r1", CubeListBuilder.create().texOffs(24, 52).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition leg4 = lowerbodyandlegs.addOrReplaceChild("leg4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 2.0F, -2.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition upper_leg_4 = leg4.addOrReplaceChild("upper_leg_4", CubeListBuilder.create(), PartPose.offset(0.0F, -0.6863F, 0.3169F));

		PartDefinition upper_r4 = upper_leg_4.addOrReplaceChild("upper_r4", CubeListBuilder.create().texOffs(20, 32).addBox(-1.0F, -1.9155F, -0.8187F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2241F, 0.0F, -0.0928F, 0.4363F, 0.0F, 0.0F));

		PartDefinition middle_leg_4 = leg4.addOrReplaceChild("middle_leg_4", CubeListBuilder.create(), PartPose.offset(0.2241F, 3.0F, 3.0F));

		PartDefinition middle_r4 = middle_leg_4.addOrReplaceChild("middle_r4", CubeListBuilder.create().texOffs(42, 0).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 5.0F, 2.5F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.0653F, 0.0343F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_4 = middle_leg_4.addOrReplaceChild("lower_leg_4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.9347F, 4.0343F));

		PartDefinition lower_r6 = lower_leg_4.addOrReplaceChild("lower_r6", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -0.0409F, 0.0089F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot_4 = lower_leg_4.addOrReplaceChild("foot_4", CubeListBuilder.create().texOffs(48, 52).addBox(-1.0F, 0.0062F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.9591F, 0.0089F));

		PartDefinition toe_r13 = foot_4.addOrReplaceChild("toe_r13", CubeListBuilder.create().texOffs(26, 48).addBox(-1.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.99F, 1.0F, 1.4101F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r14 = foot_4.addOrReplaceChild("toe_r14", CubeListBuilder.create().texOffs(58, 20).addBox(0.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition toe_r15 = foot_4.addOrReplaceChild("toe_r15", CubeListBuilder.create().texOffs(58, 0).addBox(-1.0F, 0.0062F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.99F, 1.0F, -0.0041F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition toe_r16 = foot_4.addOrReplaceChild("toe_r16", CubeListBuilder.create().texOffs(54, 12).addBox(0.0F, 0.0062F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 1.4142F, 0.0F, -0.4363F, 0.0F));

		PartDefinition outer_claw_4_r1 = foot_4.addOrReplaceChild("outer_claw_4_r1", CubeListBuilder.create().texOffs(0, 53).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, -0.866F, 3.2321F, -1.0472F, 0.0F, 0.0F));

		PartDefinition inner_claw_4_r1 = foot_4.addOrReplaceChild("inner_claw_4_r1", CubeListBuilder.create().texOffs(8, 53).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(((FogFoxEntity) entity).poseAnimationState, ModAnimationDefinitions.FOGFOX_INITIAL_POSE, ageInTicks, 1f);

		// 3. Walking animation
		if (limbSwingAmount > 0.01F) {
			// pMaxAnimationSpeed: makes gait run faster
			this.animateWalk(ModAnimationDefinitions.FOGFOX_WALK,
					limbSwing, limbSwingAmount, 8f, 2f);
		}
		// 4. Idle animation (only when not moving)
		else {
			this.animate(((FogFoxEntity) entity).idleAnimationState,
					ModAnimationDefinitions.FOGFOX_IDLE, ageInTicks, 1f);
		}
	}
	// look back into kaupens' code about an Apply Head Rotation method

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fogfox.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return fogfox;
	}
}