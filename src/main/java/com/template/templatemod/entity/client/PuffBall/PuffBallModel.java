package com.template.templatemod.entity.client.PuffBall;

import com.template.templatemod.entity.client.CubeMob.CubeMobAnimations;
import com.template.templatemod.entity.client.CubeMob.CubeMobRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class PuffBallModel extends EntityModel<PuffBallRenderState> {
    private final ModelPart PuffBall;
    private final ModelPart upper_body_group;
    private final ModelPart Head;
    private final ModelPart lower_body_group;
    private final ModelPart l_leg;
    private final ModelPart l_leg2;
    private final ModelPart r_leg;
    private final ModelPart r_leg2;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idleAnimation;

    public PuffBallModel(ModelPart root) {
        super(root);
        this.PuffBall = root.getChild("PuffBall");
        this.upper_body_group = this.PuffBall.getChild("upper_body_group");
        this.Head = this.upper_body_group.getChild("Head");
        this.lower_body_group = this.PuffBall.getChild("lower_body_group");
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

        PartDefinition PuffBall = partdefinition.addOrReplaceChild("PuffBall", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition upper_body_group = PuffBall.addOrReplaceChild("upper_body_group", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Head = upper_body_group.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -9.0F, -5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition lower_body_group = PuffBall.addOrReplaceChild("lower_body_group", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition l_leg = lower_body_group.addOrReplaceChild("l_leg", CubeListBuilder.create().texOffs(12, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -7.0F, 2.0F));

        PartDefinition l_leg2 = lower_body_group.addOrReplaceChild("l_leg2", CubeListBuilder.create().texOffs(8, 21).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -7.0F, -2.0F));

        PartDefinition r_leg = lower_body_group.addOrReplaceChild("r_leg", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -7.0F, 2.0F));

        PartDefinition r_leg2 = lower_body_group.addOrReplaceChild("r_leg2", CubeListBuilder.create().texOffs(20, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -7.0F, -2.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(PuffBallRenderState state) {
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
    }
}
