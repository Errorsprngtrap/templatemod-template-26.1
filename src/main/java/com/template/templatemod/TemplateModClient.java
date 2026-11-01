package com.template.templatemod;

import com.template.templatemod.entity.ModEntities;
import com.template.templatemod.entity.client.CubeMob.CubeMobModel;
import com.template.templatemod.entity.client.CubeMob.CubeMobRenderer;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import com.template.templatemod.entity.client.PuffBall.PuffBallModel;
import com.template.templatemod.entity.client.PuffBall.PuffBallRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = TemplateMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = TemplateMod.MOD_ID, value = Dist.CLIENT)
public class TemplateModClient {
    public TemplateModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

        EntityRenderers.register(ModEntities.CUBEMOB.get(), CubeMobRenderer::new);
        EntityRenderers.register(ModEntities.PUFFBALL.get(), PuffBallRenderer::new);

        // Some client setup code
//        TemplateMod.LOGGER.info("HELLO FROM CLIENT SETUP");
//        TemplateMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void  registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayerLocations.CUBEMOB, CubeMobModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayerLocations.PUFFBALL, PuffBallModel::createBodyLayer);
    }

}
