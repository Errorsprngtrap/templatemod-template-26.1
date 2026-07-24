package com.template.templatemod.worldgen;

import com.template.templatemod.TemplateMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        var configuredFeatures = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);

    }

    public static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,name));
    };

    private static  void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?,?>> configuration,
                                  List<PlacementModifier> modifiers) {
        context.register(key,new PlacedFeature(configuration,List.copyOf(modifiers))
        );
    }
}
