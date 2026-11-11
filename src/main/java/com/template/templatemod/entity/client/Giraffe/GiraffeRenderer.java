package com.template.templatemod.entity.client.Giraffe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.client.CubeMob.CubeMobRenderState;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import com.template.templatemod.entity.custom.CubeMobEntity;
import com.template.templatemod.entity.custom.GiraffeEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class GiraffeRenderer extends MobRenderer<GiraffeEntity,GiraffeRenderState,GiraffeModel> {

    public GiraffeRenderer(EntityRendererProvider.Context context) {
        super(context, new GiraffeModel(context.bakeLayer(ModModelLayerLocations.GIRAFFE)), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(GiraffeRenderState state) {
        return Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/giraffe/giraffe.png");    }

    @Override
    public void submit(GiraffeRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if(state.isBaby){
            poseStack.scale(0.5f,0.5f,0.5f);

        }else{
            poseStack.scale(1f,1f,1f);
        }
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public GiraffeRenderState createRenderState() {
        return new GiraffeRenderState();
    }

    @Override
    public void extractRenderState(GiraffeEntity entity, GiraffeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }
}
