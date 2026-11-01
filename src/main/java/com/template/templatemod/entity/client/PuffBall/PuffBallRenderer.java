package com.template.templatemod.entity.client.PuffBall;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.client.CubeMob.CubeMobModel;
import com.template.templatemod.entity.client.CubeMob.CubeMobRenderState;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import com.template.templatemod.entity.custom.CubeMobEntity;
import com.template.templatemod.entity.custom.PuffBallEntity;
import com.template.templatemod.entity.variant.CubeMobVariant;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PuffBallRenderer extends MobRenderer<PuffBallEntity, PuffBallRenderState, PuffBallModel> {

    public PuffBallRenderer(EntityRendererProvider.Context context) {
        super(context, new PuffBallModel(context.bakeLayer(ModModelLayerLocations.PUFFBALL)), 0.05f);
    }

    @Override
    public Identifier getTextureLocation(PuffBallRenderState state) {
        return Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/puffball/puffball.png");
    }

    @Override
    public PuffBallRenderState createRenderState() {
        return new PuffBallRenderState();
    }

    @Override
    public void submit(PuffBallRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if(state.isBaby){
            poseStack.scale(0.5f,0.5f,0.5f);

        }else{
            poseStack.scale(1f,1f,1f);
        }
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public void extractRenderState(PuffBallEntity entity, PuffBallRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }
}
