package com.template.templatemod.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CubeMobModel extends EntityModel<CubeMobRenderState> {
    private final ModelPart cubemob;
    private final ModelPart upper_body_group;
    private final ModelPart lower_body_group;
    private final ModelPart l_leg;
    private final ModelPart r_leg;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idleAnimation;

    public CubeMobModel(ModelPart root) {
        super(root);
        this.cubemob = root.getChild("cubemob");
        this.upper_body_group = this.cubemob.getChild("upper_body_group");
        this.lower_body_group = this.cubemob.getChild("lower_body_group");
        this.l_leg = this.lower_body_group.getChild("l_leg");
        this.r_leg = this.lower_body_group.getChild("r_leg");

        idleAnimation = CubeMobAnimations.Idle.bake(root);
        walkingAnimation = CubeMobAnimations.Walk.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition cubemob = partdefinition.addOrReplaceChild("cubemob", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition upper_body_group = cubemob.addOrReplaceChild("upper_body_group", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition lower_body_group = cubemob.addOrReplaceChild("lower_body_group", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition l_leg = lower_body_group.addOrReplaceChild("l_leg", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -7.0F, 0.0F));

        PartDefinition r_leg = lower_body_group.addOrReplaceChild("r_leg", CubeListBuilder.create().texOffs(8, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -7.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(CubeMobRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot,state.xRot);

        this.walkingAnimation.applyWalk(state.walkAnimationPos,state.walkAnimationSpeed,2f,2.5f);
        this.idleAnimation.apply(state.idleAnimationState,state.ageInTicks,1);

    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.upper_body_group.yRot = headYaw * ((float)Math.PI / 180f);
        this.upper_body_group.xRot = headPitch *  ((float)Math.PI / 180f);
        //this.head_group.yRot = headYaw * ((float)Math.PI / 180f);
        //this.head_group.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}
