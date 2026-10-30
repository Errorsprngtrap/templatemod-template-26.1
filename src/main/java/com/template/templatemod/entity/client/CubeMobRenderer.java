package com.template.templatemod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.custom.CubeMobEntity;
import com.template.templatemod.entity.variant.CubeMobVariant;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class CubeMobRenderer extends MobRenderer<CubeMobEntity,CubeMobRenderState,CubeMobModel> {

    private static final Map<CubeMobVariant,Identifier> TEXTURE_BY_VARIANT =
            Util.make(Maps.newEnumMap(CubeMobVariant.class),map ->{
                map.put(CubeMobVariant.DEFAULT,
                Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/cubemob/cubemob.png"));
                map.put(CubeMobVariant.RED,
                        Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/cubemob/red_cubemob.png"));
            });
    public CubeMobRenderer(EntityRendererProvider.Context context) {
        super(context, new CubeMobModel(context.bakeLayer(ModModelLayerLocations.CUBEMOB)), 0.05f);
    }

    @Override
    public Identifier getTextureLocation(CubeMobRenderState state) {
        return TEXTURE_BY_VARIANT.get(state.variant);
        //return Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/cubemob/cubemob.png");
    }

    @Override
    public CubeMobRenderState createRenderState() {
        return new CubeMobRenderState();
    }

    @Override
    public void submit(CubeMobRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if(state.isBaby){
            poseStack.scale(0.5f,0.5f,0.5f);

        }else{
            poseStack.scale(1f,1f,1f);
        }
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public void extractRenderState(CubeMobEntity entity, CubeMobRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.variant = entity.getVariant();
    }
}
