package com.template.templatemod.entity.client.WarTurtle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.custom.WarTurtleEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class WarTurtleRenderer extends MobRenderer<WarTurtleEntity, WarTurtleRenderState, WarTurtleModel> {

    public WarTurtleRenderer(EntityRendererProvider.Context context, WarTurtleModel model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public Identifier getTextureLocation(WarTurtleRenderState state) {
        return Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "textures/entity/war_turtle/war_turtle.png");
    }

    @Override
    public WarTurtleRenderState createRenderState() {
        return new WarTurtleRenderState();
    }

    @Override
    public void submit(WarTurtleRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.isBaby){
            poseStack.scale(0.5f,0.5f,0.5f);
        }else {
            poseStack.scale(1f,1f,1f);
        }
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public void extractRenderState(WarTurtleEntity entity, WarTurtleRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.sitAnimationState.copyFrom(entity.sitAnimationState);
        state.sitPoseAnimationState.copyFrom(entity.sitPoseAnimationState);
        state.sitUpAnimationState.copyFrom(entity.sitUpAnimationState);
    }
}
