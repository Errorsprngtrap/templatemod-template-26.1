package com.template.templatemod.entity.client.Giraffe;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.template.templatemod.entity.client.CubeMob.CubeMobAnimations;
import com.template.templatemod.entity.client.CubeMob.CubeMobRenderState;
import com.template.templatemod.entity.client.Giraffe.GiraffeRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GiraffeModel extends EntityModel<GiraffeRenderState> {
	private final ModelPart Giraffe;
	private final ModelPart upper_body_group;
	private final ModelPart Neck;
	private final ModelPart Head;
	private final ModelPart lower_body_group;
	private final ModelPart l_leg;
	private final ModelPart l_leg2;
	private final ModelPart r_leg;
	private final ModelPart r_leg2;

	private final KeyframeAnimation walkingAnimation;
	private final KeyframeAnimation idleAnimation;

	public GiraffeModel(ModelPart root) {
		super(root);
		this.Giraffe = root.getChild("Giraffe");
		this.upper_body_group = this.Giraffe.getChild("upper_body_group");
		this.Neck = this.upper_body_group.getChild("Neck");
		this.Head = this.Neck.getChild("Head");
		this.lower_body_group = this.Giraffe.getChild("lower_body_group");
		this.l_leg = this.lower_body_group.getChild("l_leg");
		this.l_leg2 = this.lower_body_group.getChild("l_leg2");
		this.r_leg = this.lower_body_group.getChild("r_leg");
		this.r_leg2 = this.lower_body_group.getChild("r_leg2");

		idleAnimation = CubeMobAnimations.Idle.bake(root);
		walkingAnimation = CubeMobAnimations.Walk.bake(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Giraffe = partdefinition.addOrReplaceChild("Giraffe", CubeListBuilder.create(), PartPose.offset(-7.0F, -17.0F, -11.0F));

		PartDefinition upper_body_group = Giraffe.addOrReplaceChild("upper_body_group", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -4.0F, -12.0F, 16.0F, 7.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 23.0F, 15.0F));

		PartDefinition Neck = upper_body_group.addOrReplaceChild("Neck", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, -10.0F));

		PartDefinition cube_r1 = Neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 28).addBox(4.0F, -23.0F, -15.0F, 6.0F, 23.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 13.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(7.0F, -17.0F, -5.0F));

		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 47).addBox(4.0F, 1.3661F, -13.2384F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(2.0F, -3.6339F, -7.2384F, 10.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -5.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition lower_body_group = Giraffe.addOrReplaceChild("lower_body_group", CubeListBuilder.create(), PartPose.offset(7.0F, 21.0F, 11.0F));

		PartDefinition l_leg = lower_body_group.addOrReplaceChild("l_leg", CubeListBuilder.create().texOffs(24, 47).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 5.0F, -7.0F));

		PartDefinition l_leg2 = lower_body_group.addOrReplaceChild("l_leg2", CubeListBuilder.create().texOffs(40, 56).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 5.0F, 12.0F));

		PartDefinition r_leg = lower_body_group.addOrReplaceChild("r_leg", CubeListBuilder.create().texOffs(32, 56).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 5.0F, -7.0F));

		PartDefinition r_leg2 = lower_body_group.addOrReplaceChild("r_leg2", CubeListBuilder.create().texOffs(48, 56).addBox(13.0F, 1.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 4.0F, 12.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(GiraffeRenderState state) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(state.yRot,state.xRot);

		this.walkingAnimation.applyWalk(state.walkAnimationPos,state.walkAnimationSpeed,2f,2.5f);
		this.idleAnimation.apply(state.idleAnimationState,state.ageInTicks,1);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -25f, 45);

		this.Head.yRot = headYaw * ((float)Math.PI / 180f);
		this.Head.xRot = headPitch *  ((float)Math.PI / 180f);
		//this.head_group.yRot = headYaw * ((float)Math.PI / 180f);
		//this.head_group.xRot = headPitch *  ((float)Math.PI / 180f);
	}
}