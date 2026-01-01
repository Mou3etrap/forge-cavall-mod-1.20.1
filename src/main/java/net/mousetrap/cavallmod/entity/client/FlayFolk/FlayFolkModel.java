package net.mousetrap.cavallmod.entity.client.FlayFolk;// Made with Blockbench 5.0.7
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
import net.mousetrap.cavallmod.entity.custom.FlayFolkEntity;

public class FlayFolkModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart flayfolk;
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
	private final ModelPart eyes1;
	private final ModelPart eyes2;
	private final ModelPart eyes3;
	private final ModelPart eyes4;
	private final ModelPart bodygroup;
	private final ModelPart mouthgroup;
	private final ModelPart mouth1;
	private final ModelPart mouth2;
	private final ModelPart mouth3;
	private final ModelPart mouth4;
	private final ModelPart body;
	private final ModelPart middlebody;
	private final ModelPart lowerbodyandlegs;
	private final ModelPart all_legs;
	private final ModelPart leg1;
	private final ModelPart upper_leg_1;
	private final ModelPart middle_leg_1;
	private final ModelPart lower_leg_1;
	private final ModelPart foot1;
	private final ModelPart leg2;
	private final ModelPart upper_leg_2;
	private final ModelPart middle_leg_2;
	private final ModelPart lower_leg_2;
	private final ModelPart foot2;
	private final ModelPart leg3;
	private final ModelPart upper_leg_3;
	private final ModelPart middle_leg_3;
	private final ModelPart lower_leg_3;
	private final ModelPart foot3;
	private final ModelPart leg4;
	private final ModelPart upper_leg_4;
	private final ModelPart middle_leg_4;
	private final ModelPart lower_leg_4;
	private final ModelPart foot4;

	public FlayFolkModel(ModelPart root) {
		this.flayfolk = root.getChild("flayfolk");
		this.armsandeyes = this.flayfolk.getChild("armsandeyes");
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
		this.eyes1 = this.eyes.getChild("eyes1");
		this.eyes2 = this.eyes.getChild("eyes2");
		this.eyes3 = this.eyes.getChild("eyes3");
		this.eyes4 = this.eyes.getChild("eyes4");
		this.bodygroup = this.flayfolk.getChild("bodygroup");
		this.mouthgroup = this.bodygroup.getChild("mouthgroup");
		this.mouth1 = this.mouthgroup.getChild("mouth1");
		this.mouth2 = this.mouthgroup.getChild("mouth2");
		this.mouth3 = this.mouthgroup.getChild("mouth3");
		this.mouth4 = this.mouthgroup.getChild("mouth4");
		this.body = this.bodygroup.getChild("body");
		this.middlebody = this.body.getChild("middlebody");
		this.lowerbodyandlegs = this.body.getChild("lowerbodyandlegs");
		this.all_legs = this.lowerbodyandlegs.getChild("all_legs");
		this.leg1 = this.all_legs.getChild("leg1");
		this.upper_leg_1 = this.leg1.getChild("upper_leg_1");
		this.middle_leg_1 = this.upper_leg_1.getChild("middle_leg_1");
		this.lower_leg_1 = this.middle_leg_1.getChild("lower_leg_1");
		this.foot1 = this.lower_leg_1.getChild("foot1");
		this.leg2 = this.all_legs.getChild("leg2");
		this.upper_leg_2 = this.leg2.getChild("upper_leg_2");
		this.middle_leg_2 = this.upper_leg_2.getChild("middle_leg_2");
		this.lower_leg_2 = this.middle_leg_2.getChild("lower_leg_2");
		this.foot2 = this.lower_leg_2.getChild("foot2");
		this.leg3 = this.all_legs.getChild("leg3");
		this.upper_leg_3 = this.leg3.getChild("upper_leg_3");
		this.middle_leg_3 = this.upper_leg_3.getChild("middle_leg_3");
		this.lower_leg_3 = this.middle_leg_3.getChild("lower_leg_3");
		this.foot3 = this.lower_leg_3.getChild("foot3");
		this.leg4 = this.all_legs.getChild("leg4");
		this.upper_leg_4 = this.leg4.getChild("upper_leg_4");
		this.middle_leg_4 = this.upper_leg_4.getChild("middle_leg_4");
		this.lower_leg_4 = this.middle_leg_4.getChild("lower_leg_4");
		this.foot4 = this.lower_leg_4.getChild("foot4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition flayfolk = partdefinition.addOrReplaceChild("flayfolk", CubeListBuilder.create(), PartPose.offset(6.0F, -0.9F, 2.0F));

		PartDefinition armsandeyes = flayfolk.addOrReplaceChild("armsandeyes", CubeListBuilder.create(), PartPose.offset(3.5F, -11.3F, -2.0F));

		PartDefinition leftarm = armsandeyes.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 41).addBox(-0.5F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -2.8F, 0.0F));

		PartDefinition lowerarmleft = leftarm.addOrReplaceChild("lowerarmleft", CubeListBuilder.create().texOffs(0, 49).addBox(0.5F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, 0.0F, 0.0F));

		PartDefinition lefthand = lowerarmleft.addOrReplaceChild("lefthand", CubeListBuilder.create(), PartPose.offsetAndRotation(12.4872F, -1.0F, -1.1962F, 0.0F, -1.5708F, 0.0F));

		PartDefinition fingerl_r1 = lefthand.addOrReplaceChild("fingerl_r1", CubeListBuilder.create().texOffs(92, 16).addBox(-3.0412F, 1.999F, 0.6993F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.7998F, -1.0F, -0.0128F, 0.0F, 2.7489F, 0.0F));

		PartDefinition fingerl_r2 = lefthand.addOrReplaceChild("fingerl_r2", CubeListBuilder.create().texOffs(92, 14).addBox(-0.5753F, 1.999F, 2.2445F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.7998F, -1.0F, -0.0128F, 0.0F, 2.0944F, 0.0F));

		PartDefinition fingerl_r3 = lefthand.addOrReplaceChild("fingerl_r3", CubeListBuilder.create().texOffs(62, 91).addBox(-5.4965F, 1.999F, -1.5946F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.7998F, -1.0F, -0.0128F, 0.0F, -2.7489F, 0.0F));

		PartDefinition fingerl_r4 = lefthand.addOrReplaceChild("fingerl_r4", CubeListBuilder.create().texOffs(88, 76).addBox(-5.4217F, 1.999F, -2.9465F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.7998F, -1.0F, -0.0128F, 0.0F, -2.0944F, 0.0F));

		PartDefinition middlefingerl_r1 = lefthand.addOrReplaceChild("middlefingerl_r1", CubeListBuilder.create().texOffs(32, 22).addBox(-4.499F, 1.999F, -3.497F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-1.7998F, -1.0F, -0.0128F, 0.0F, -1.5708F, 0.0F));

		PartDefinition wrist_r1 = lefthand.addOrReplaceChild("wrist_r1", CubeListBuilder.create().texOffs(32, 14).addBox(-1.999F, -0.001F, -1.999F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.1982F, 0.0F, -0.0128F, 0.0F, -1.5708F, 0.0F));

		PartDefinition rightarm = armsandeyes.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 45).addBox(-11.5F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, -2.8F, 0.0F));

		PartDefinition lowerarmright = rightarm.addOrReplaceChild("lowerarmright", CubeListBuilder.create().texOffs(28, 49).addBox(-12.5F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 0.0F, 0.0F));

		PartDefinition righthand = lowerarmright.addOrReplaceChild("righthand", CubeListBuilder.create(), PartPose.offsetAndRotation(-12.5F, -1.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition fingerr_r1 = righthand.addOrReplaceChild("fingerr_r1", CubeListBuilder.create().texOffs(92, 26).addBox(-1.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.5576F, 1.049F, -0.5578F, 0.0F, 2.7489F, 0.0F));

		PartDefinition fingerr_r2 = righthand.addOrReplaceChild("fingerr_r2", CubeListBuilder.create().texOffs(92, 24).addBox(0.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.117F, 1.049F, -0.999F, 0.0F, 2.0944F, 0.0F));

		PartDefinition fingerr_r3 = righthand.addOrReplaceChild("fingerr_r3", CubeListBuilder.create().texOffs(92, 22).addBox(-3.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.885F, 1.049F, -0.999F, 0.0F, -2.0944F, 0.0F));

		PartDefinition fingerr_r4 = righthand.addOrReplaceChild("fingerr_r4", CubeListBuilder.create().texOffs(92, 20).addBox(-2.0F, -0.05F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.4444F, 1.049F, -0.5578F, 0.0F, -2.7489F, 0.0F));

		PartDefinition middlefinger_r1 = righthand.addOrReplaceChild("middlefinger_r1", CubeListBuilder.create().texOffs(92, 18).addBox(0.0F, -0.05F, -0.55F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-0.951F, 1.049F, -1.499F, 0.0F, 1.5708F, 0.0F));

		PartDefinition wrist_r2 = righthand.addOrReplaceChild("wrist_r2", CubeListBuilder.create().texOffs(32, 18).addBox(0.5F, -1.05F, -1.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.751F, 1.049F, 0.501F, 0.0F, 1.5708F, 0.0F));

		PartDefinition rightpedipalp = armsandeyes.addOrReplaceChild("rightpedipalp", CubeListBuilder.create().texOffs(84, 10).addBox(-4.0F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.5F, -21.8F, 0.0F));

		PartDefinition middle_right_ped = rightpedipalp.addOrReplaceChild("middle_right_ped", CubeListBuilder.create(), PartPose.offset(-4.0F, -0.5F, -0.5F));

		PartDefinition middler_r1 = middle_right_ped.addOrReplaceChild("middler_r1", CubeListBuilder.create().texOffs(72, 12).addBox(-6.5F, -0.5F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition upper_right_ped = middle_right_ped.addOrReplaceChild("upper_right_ped", CubeListBuilder.create().texOffs(84, 6).addBox(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.5F));

		PartDefinition leftpedipalp = armsandeyes.addOrReplaceChild("leftpedipalp", CubeListBuilder.create().texOffs(88, 12).addBox(-1.0F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, -21.8F, 0.0F));

		PartDefinition middle_left_ped = leftpedipalp.addOrReplaceChild("middle_left_ped", CubeListBuilder.create(), PartPose.offset(4.0F, -0.5F, -0.5F));

		PartDefinition middlel_r1 = middle_left_ped.addOrReplaceChild("middlel_r1", CubeListBuilder.create().texOffs(72, 77).addBox(-0.5F, -0.5F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition upper_left_ped = middle_left_ped.addOrReplaceChild("upper_left_ped", CubeListBuilder.create().texOffs(84, 8).addBox(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.5F));

		PartDefinition eyes = armsandeyes.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(-9.5F, 7.2F, 0.0F));

		PartDefinition eyes1 = eyes.addOrReplaceChild("eyes1", CubeListBuilder.create().texOffs(12, 84).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(84, 71).addBox(2.5F, -0.5F, 3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -24.0F, -2.0F));

		PartDefinition eyes2 = eyes.addOrReplaceChild("eyes2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye6_r1 = eyes2.addOrReplaceChild("eye6_r1", CubeListBuilder.create().texOffs(84, 79).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -24.0F, 1.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition eye2_r1 = eyes2.addOrReplaceChild("eye2_r1", CubeListBuilder.create().texOffs(84, 56).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -24.0F, -1.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition eyes3 = eyes.addOrReplaceChild("eyes3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye7_r1 = eyes3.addOrReplaceChild("eye7_r1", CubeListBuilder.create().texOffs(84, 84).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -24.0F, -1.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition eye3_r1 = eyes3.addOrReplaceChild("eye3_r1", CubeListBuilder.create().texOffs(84, 61).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -24.0F, 1.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition eyes4 = eyes.addOrReplaceChild("eyes4", CubeListBuilder.create().texOffs(84, 66).addBox(-2.0F, -24.5F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 85).addBox(1.0F, -24.5F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bodygroup = flayfolk.addOrReplaceChild("bodygroup", CubeListBuilder.create(), PartPose.offset(-12.0F, 0.0F, 0.0F));

		PartDefinition mouthgroup = bodygroup.addOrReplaceChild("mouthgroup", CubeListBuilder.create(), PartPose.offset(6.0F, -33.1F, -2.0F));

		PartDefinition mouth1 = mouthgroup.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(56, 49).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth2 = mouthgroup.addOrReplaceChild("mouth2", CubeListBuilder.create().texOffs(68, 42).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth3 = mouthgroup.addOrReplaceChild("mouth3", CubeListBuilder.create().texOffs(8, 90).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth4 = mouthgroup.addOrReplaceChild("mouth4", CubeListBuilder.create().texOffs(78, 90).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = bodygroup.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-11.0F, -33.1F, -7.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 0.0F, 0.0F));

		PartDefinition middlebody = body.addOrReplaceChild("middlebody", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -20.0F, -4.0F, 8.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -9.1F, -2.0F));

		PartDefinition lowerbodyandlegs = body.addOrReplaceChild("lowerbodyandlegs", CubeListBuilder.create().texOffs(0, 25).addBox(1.0F, -12.1F, -7.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(40, 26).addBox(4.0F, -25.1F, -7.0F, 4.0F, 13.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(40, 14).addBox(1.0F, -27.1F, -7.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, 0.0F, 0.0F));

		PartDefinition all_legs = lowerbodyandlegs.addOrReplaceChild("all_legs", CubeListBuilder.create(), PartPose.offset(6.0F, 2.9F, -2.0F));

		PartDefinition leg1 = all_legs.addOrReplaceChild("leg1", CubeListBuilder.create(), PartPose.offset(2.5F, -10.9F, 2.5F));

		PartDefinition upper_leg_1 = leg1.addOrReplaceChild("upper_leg_1", CubeListBuilder.create(), PartPose.offset(-7.7241F, 7.3137F, -5.0902F));

		PartDefinition upper_new_r1 = upper_leg_1.addOrReplaceChild("upper_new_r1", CubeListBuilder.create().texOffs(0, 53).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7241F, -5.4399F, 6.9277F, 0.3491F, 0.0F, 0.0F));

		PartDefinition middle_leg_1 = upper_leg_1.addOrReplaceChild("middle_leg_1", CubeListBuilder.create(), PartPose.offset(7.7241F, 7.2891F, 9.9644F));

		PartDefinition middle_new_r1 = middle_leg_1.addOrReplaceChild("middle_new_r1", CubeListBuilder.create().texOffs(48, 69).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_1 = middle_leg_1.addOrReplaceChild("lower_leg_1", CubeListBuilder.create(), PartPose.offset(0.0F, 1.4947F, 9.3684F));

		PartDefinition armour1_r1 = lower_leg_1.addOrReplaceChild("armour1_r1", CubeListBuilder.create().texOffs(24, 72).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 53).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot1 = lower_leg_1.addOrReplaceChild("foot1", CubeListBuilder.create().texOffs(28, 41).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.8025F, 2.2574F));

		PartDefinition toe_r1 = foot1.addOrReplaceChild("toe_r1", CubeListBuilder.create().texOffs(12, 89).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 3.0F, -3.0F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition toe_r2 = foot1.addOrReplaceChild("toe_r2", CubeListBuilder.create().texOffs(80, 20).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5937F, 3.0F, 0.4226F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r3 = foot1.addOrReplaceChild("toe_r3", CubeListBuilder.create().texOffs(62, 87).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 3.0F, -3.0F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition toe_r4 = foot1.addOrReplaceChild("toe_r4", CubeListBuilder.create().texOffs(80, 14).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5937F, 3.0F, 0.4226F, 0.0F, -0.4363F, 0.0F));

		PartDefinition outer_claw_1_r1 = foot1.addOrReplaceChild("outer_claw_1_r1", CubeListBuilder.create().texOffs(22, 86).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.634F, 3.8301F, -1.0472F, 0.0F, 0.0F));

		PartDefinition inner_claw_1_r1 = foot1.addOrReplaceChild("inner_claw_1_r1", CubeListBuilder.create().texOffs(72, 79).addBox(-0.5F, -0.9918F, 0.0022F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition leg2 = all_legs.addOrReplaceChild("leg2", CubeListBuilder.create(), PartPose.offset(-2.5F, -10.9F, 2.5F));

		PartDefinition upper_leg_2 = leg2.addOrReplaceChild("upper_leg_2", CubeListBuilder.create(), PartPose.offset(-7.7241F, 7.3137F, -5.0902F));

		PartDefinition upper_new_r2 = upper_leg_2.addOrReplaceChild("upper_new_r2", CubeListBuilder.create().texOffs(12, 53).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7241F, -5.4399F, 6.9277F, 0.3491F, 0.0F, 0.0F));

		PartDefinition middle_leg_2 = upper_leg_2.addOrReplaceChild("middle_leg_2", CubeListBuilder.create(), PartPose.offset(7.7241F, 7.2891F, 9.9644F));

		PartDefinition middle_new_r2 = middle_leg_2.addOrReplaceChild("middle_new_r2", CubeListBuilder.create().texOffs(0, 72).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_2 = middle_leg_2.addOrReplaceChild("lower_leg_2", CubeListBuilder.create(), PartPose.offset(0.0F, 1.4947F, 9.3684F));

		PartDefinition armour2_r1 = lower_leg_2.addOrReplaceChild("armour2_r1", CubeListBuilder.create().texOffs(32, 72).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 49).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot2 = lower_leg_2.addOrReplaceChild("foot2", CubeListBuilder.create().texOffs(72, 56).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.8025F, 2.2574F));

		PartDefinition toe_r5 = foot2.addOrReplaceChild("toe_r5", CubeListBuilder.create().texOffs(0, 90).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 3.0F, -3.0F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition toe_r6 = foot2.addOrReplaceChild("toe_r6", CubeListBuilder.create().texOffs(80, 38).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5937F, 3.0F, 0.4226F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r7 = foot2.addOrReplaceChild("toe_r7", CubeListBuilder.create().texOffs(82, 89).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 3.0F, -3.0F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition toe_r8 = foot2.addOrReplaceChild("toe_r8", CubeListBuilder.create().texOffs(80, 32).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5937F, 3.0F, 0.4226F, 0.0F, -0.4363F, 0.0F));

		PartDefinition outer_claw_2_r1 = foot2.addOrReplaceChild("outer_claw_2_r1", CubeListBuilder.create().texOffs(32, 86).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.634F, 3.8301F, -1.0472F, 0.0F, 0.0F));

		PartDefinition inner_claw_2_r1 = foot2.addOrReplaceChild("inner_claw_2_r1", CubeListBuilder.create().texOffs(80, 26).addBox(-0.5F, -0.9918F, 0.0022F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition leg3 = all_legs.addOrReplaceChild("leg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -10.9F, -2.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition upper_leg_3 = leg3.addOrReplaceChild("upper_leg_3", CubeListBuilder.create(), PartPose.offset(-7.7241F, 7.3137F, -5.0902F));

		PartDefinition upper_new_r3 = upper_leg_3.addOrReplaceChild("upper_new_r3", CubeListBuilder.create().texOffs(24, 53).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7241F, -5.4399F, 6.9277F, 0.3491F, 0.0F, 0.0F));

		PartDefinition middle_leg_3 = upper_leg_3.addOrReplaceChild("middle_leg_3", CubeListBuilder.create(), PartPose.offset(7.7241F, 7.2891F, 9.9644F));

		PartDefinition middle_new_r3 = middle_leg_3.addOrReplaceChild("middle_new_r3", CubeListBuilder.create().texOffs(72, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_3 = middle_leg_3.addOrReplaceChild("lower_leg_3", CubeListBuilder.create(), PartPose.offset(0.0F, 1.4947F, 9.3684F));

		PartDefinition armour3_r1 = lower_leg_3.addOrReplaceChild("armour3_r1", CubeListBuilder.create().texOffs(40, 72).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 65).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot3 = lower_leg_3.addOrReplaceChild("foot3", CubeListBuilder.create().texOffs(72, 63).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.8025F, 2.2574F));

		PartDefinition toe_r9 = foot3.addOrReplaceChild("toe_r9", CubeListBuilder.create().texOffs(90, 89).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 3.0F, -3.0F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition toe_r10 = foot3.addOrReplaceChild("toe_r10", CubeListBuilder.create().texOffs(60, 81).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5937F, 3.0F, 0.4226F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r11 = foot3.addOrReplaceChild("toe_r11", CubeListBuilder.create().texOffs(70, 90).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 3.0F, -3.0F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition toe_r12 = foot3.addOrReplaceChild("toe_r12", CubeListBuilder.create().texOffs(48, 81).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5937F, 3.0F, 0.4226F, 0.0F, -0.4363F, 0.0F));

		PartDefinition outer_claw_3_r1 = foot3.addOrReplaceChild("outer_claw_3_r1", CubeListBuilder.create().texOffs(42, 87).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.634F, 3.8301F, -1.0472F, 0.0F, 0.0F));

		PartDefinition inner_claw_3_r1 = foot3.addOrReplaceChild("inner_claw_3_r1", CubeListBuilder.create().texOffs(80, 50).addBox(-0.5F, -0.9918F, 0.0022F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition leg4 = all_legs.addOrReplaceChild("leg4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, -10.9F, -2.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition upper_leg_4 = leg4.addOrReplaceChild("upper_leg_4", CubeListBuilder.create(), PartPose.offset(-7.7241F, 7.3137F, -5.0902F));

		PartDefinition upper_new_r4 = upper_leg_4.addOrReplaceChild("upper_new_r4", CubeListBuilder.create().texOffs(36, 53).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7241F, -5.4399F, 6.9277F, 0.3491F, 0.0F, 0.0F));

		PartDefinition middle_leg_4 = upper_leg_4.addOrReplaceChild("middle_leg_4", CubeListBuilder.create(), PartPose.offset(7.7241F, 7.2891F, 9.9644F));

		PartDefinition middle_new_r4 = middle_leg_4.addOrReplaceChild("middle_new_r4", CubeListBuilder.create().texOffs(12, 72).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0908F, 0.0F, 0.0F));

		PartDefinition lower_leg_4 = middle_leg_4.addOrReplaceChild("lower_leg_4", CubeListBuilder.create(), PartPose.offset(0.0F, 1.4947F, 9.3684F));

		PartDefinition armour4_r1 = lower_leg_4.addOrReplaceChild("armour4_r1", CubeListBuilder.create().texOffs(72, 42).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 26).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition foot4 = lower_leg_4.addOrReplaceChild("foot4", CubeListBuilder.create().texOffs(72, 70).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.8025F, 2.2574F));

		PartDefinition toe_r13 = foot4.addOrReplaceChild("toe_r13", CubeListBuilder.create().texOffs(28, 91).addBox(0.28F, 3.0F, 4.2547F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -3.1416F, 1.2217F, -3.1416F));

		PartDefinition toe_r14 = foot4.addOrReplaceChild("toe_r14", CubeListBuilder.create().texOffs(80, 44).addBox(3.0784F, 3.0F, 0.9018F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition toe_r15 = foot4.addOrReplaceChild("toe_r15", CubeListBuilder.create().texOffs(20, 91).addBox(-3.3321F, 3.0F, -1.3835F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -3.1416F, -1.2217F, 3.1416F));

		PartDefinition toe_r16 = foot4.addOrReplaceChild("toe_r16", CubeListBuilder.create().texOffs(84, 0).addBox(1.3595F, 3.0F, -1.6339F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition outer_claw_4_r1 = foot4.addOrReplaceChild("outer_claw_4_r1", CubeListBuilder.create().texOffs(52, 87).addBox(2.5F, -3.0F, 2.4641F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition inner_claw_4_r1 = foot4.addOrReplaceChild("inner_claw_4_r1", CubeListBuilder.create().texOffs(0, 84).addBox(2.5F, 2.4723F, -1.9978F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		FlayFolkEntity flayfolkEntity = (FlayFolkEntity) entity;
		// call flayfolkEntity to summon methods from the FlayFolkEntity class
		// such as the getters and setters

		// sort of like "registering" the AnimationStates
		this.animate(((FlayFolkEntity) entity).poseAnimationState, ModAnimationDefinitions.FLAYFOLK_INITIAL_POSE, ageInTicks, 1f);
		this.animate(((FlayFolkEntity) entity).attackAnimationState, ModAnimationDefinitions.FLAYFOLK_ATTACK, ageInTicks, 1f);

		// 3. Walking animation
		if (flayfolkEntity.isWaitingToAmbush()){
			this.animate(((FlayFolkEntity) entity).waitingToAmbushAnimationState,
					ModAnimationDefinitions.FLAYFOLK_AMBUSH_POSE, ageInTicks, 1f);
		} else if (limbSwingAmount > 0.01F) {
			// pMaxAnimationSpeed: makes gait run faster
			this.animateWalk(ModAnimationDefinitions.FLAYFOLK_WALK,
					limbSwing, limbSwingAmount, 7f, 2f);
		}
		// 4. Idle animation (only when not moving)
		else {
			this.animate(((FlayFolkEntity) entity).idleAnimationState,
					ModAnimationDefinitions.HAPLO_IDLE, ageInTicks, 1f);
		}
	}
	// look back into kaupens' code about an Apply Head Rotation method

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		flayfolk.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return flayfolk;
	}
}