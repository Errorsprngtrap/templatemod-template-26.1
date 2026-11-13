package com.template.templatemod.entity.client.Giraffe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class GiraffeSaddleLayer extends RenderLayer<GiraffeRenderState,GiraffeModel> {
    private final GiraffeSaddleModel saddleModel;
    private static final Identifier SADDLE_TEXTURE = Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "textures/entity/giraffe/giraffe_saddle.png");

    public GiraffeSaddleLayer(RenderLayerParent<GiraffeRenderState, GiraffeModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        saddleModel  = new GiraffeSaddleModel(modelSet.bakeLayer(ModModelLayerLocations.GIRAFFE_SADDLE));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, GiraffeRenderState state, float yRot, float xRot) {
        if (state.isSaddled) {
            this.saddleModel.setupAnim(state);
            submitNodeCollector.order(1).submitModel(this.saddleModel, state, poseStack, RenderTypes.entityCutout(SADDLE_TEXTURE),
                    lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
        }
    }
}
