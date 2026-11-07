package com.template.templatemod.entity.client.WarTurtle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import com.template.templatemod.item.ModItems;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class WarTurtleArmorRenderLayer extends RenderLayer<WarTurtleRenderState, WarTurtleModel> {
    private final WarTurtleModel model;

    private final Map<Item, Identifier> ARMOR_MAP = Map.of(
            ModItems.WARTURTLE_ARMOR.get(), Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "textures/entity/warturtle/armor/warturtle_armor.png")
    );

    public WarTurtleArmorRenderLayer(RenderLayerParent<WarTurtleRenderState, WarTurtleModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        model = new WarTurtleModel(modelSet.bakeLayer(ModModelLayerLocations.WARTURTLE_ARMOR));
    }


    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, WarTurtleRenderState state, float yRot, float xRot) {
        if(state.bodyArmorItem != ItemStack.EMPTY) {
            ItemStack itemStack = state.bodyArmorItem;

            this.model.setupAnim(state);
            submitNodeCollector.order(1).submitModel(this.model, state, poseStack, RenderTypes.entityCutout(ARMOR_MAP.get(itemStack.getItem())),
                    lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
        }

    }
}
