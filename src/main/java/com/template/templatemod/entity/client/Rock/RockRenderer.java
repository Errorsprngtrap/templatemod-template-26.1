package com.template.templatemod.entity.client.Rock;

import com.mojang.blaze3d.vertex.PoseStack;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import com.template.templatemod.entity.custom.RockProjectileEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;

public class RockRenderer extends EntityRenderer<RockProjectileEntity, RockRenderState> {
    private RockModel model;

    public RockRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new RockModel(context.bakeLayer(ModModelLayerLocations.ROCK));
    }

    @Override
    public RockRenderState createRenderState() {
        return new RockRenderState();
    }

    @Override
    public void extractRenderState(RockProjectileEntity entity, RockRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

    }

    @Override
    public void submit(RockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);


    }


}
