package com.template.templatemod.entity.client.Rock;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import com.template.templatemod.entity.custom.RockProjectileEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

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

        poseStack.pushPose();

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0F, -1.501F, 0.0F);

        submitNodeCollector.submitModel(
                this.model, state, poseStack, RenderTypes.entitySolid(getTextureLocation()), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null
        );
        poseStack.popPose();
    }

    public Identifier getTextureLocation() {
        return Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "textures/entity/rock/rock.png");
    }

}
