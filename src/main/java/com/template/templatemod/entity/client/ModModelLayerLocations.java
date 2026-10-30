package com.template.templatemod.entity.client;

import com.template.templatemod.TemplateMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayerLocations {
    public static final ModelLayerLocation CUBEMOB =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"cubemob"),"main");
}
