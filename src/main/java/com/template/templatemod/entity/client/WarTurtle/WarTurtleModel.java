package com.template.templatemod.entity.client.WarTurtle;

import com.template.templatemod.entity.client.PuffBall.PuffBallRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class WarTurtleModel extends EntityModel<WarTurtleRenderState> {

    private final ModelPart WeirdTurle;
    private final ModelPart Lower_Body;
    private final ModelPart L_Leg;
    private final ModelPart R_Leg;
    private final ModelPart L_Leg2;
    private final ModelPart R_Leg2;
    private final ModelPart UpperBody;
    private final ModelPart Head;
    private final ModelPart Armor;
    private final ModelPart Chest;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation sitAnimation;
    private final KeyframeAnimation sitPoseAnimation;
    private final KeyframeAnimation standupAnimation;

    public WarTurtleModel(ModelPart root) {
        super(root);
        this.WeirdTurle = root.getChild("WeirdTurle");
        this.Lower_Body = this.WeirdTurle.getChild("Lower_Body");
        this.L_Leg = this.Lower_Body.getChild("L_Leg");
        this.R_Leg = this.Lower_Body.getChild("R_Leg");
        this.L_Leg2 = this.Lower_Body.getChild("L_Leg2");
        this.R_Leg2 = this.Lower_Body.getChild("R_Leg2");
        this.UpperBody = this.WeirdTurle.getChild("UpperBody");
        this.Head = this.UpperBody.getChild("Head");
        this.Armor = this.Head.getChild("Armor");
        this.Chest = this.UpperBody.getChild("Chest");

        walkingAnimation = WarTurleAnimations.Walk.bake(root);
        sitAnimation = WarTurleAnimations.Sit.bake(root);
        sitPoseAnimation = WarTurleAnimations.Sitting.bake(root);
        standupAnimation = WarTurleAnimations.GetUp.bake(root);
        idleAnimation = WarTurleAnimations.Idle.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition WeirdTurle = partdefinition.addOrReplaceChild("WeirdTurle", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Lower_Body = WeirdTurle.addOrReplaceChild("Lower_Body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition L_Leg = Lower_Body.addOrReplaceChild("L_Leg", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -6.0F, -5.0F));

        PartDefinition R_Leg = Lower_Body.addOrReplaceChild("R_Leg", CubeListBuilder.create().texOffs(16, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -6.0F, -5.0F));

        PartDefinition L_Leg2 = Lower_Body.addOrReplaceChild("L_Leg2", CubeListBuilder.create().texOffs(8, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -6.0F, 5.0F));

        PartDefinition R_Leg2 = Lower_Body.addOrReplaceChild("R_Leg2", CubeListBuilder.create().texOffs(24, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -6.0F, 5.0F));

        PartDefinition UpperBody = WeirdTurle.addOrReplaceChild("UpperBody", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.0F, -6.0F, 8.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition Head = UpperBody.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 20).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -6.0F));

        PartDefinition Armor = Head.addOrReplaceChild("Armor", CubeListBuilder.create().texOffs(20, 16).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(3.0F, -3.0F, -4.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 32).addBox(-3.0F, -3.0F, -4.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = Armor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -4.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition Chest = UpperBody.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(32, 20).addBox(-5.0F, -9.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(WarTurtleRenderState state) {
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
