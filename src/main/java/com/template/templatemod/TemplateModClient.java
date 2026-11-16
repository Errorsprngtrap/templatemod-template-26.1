package com.template.templatemod;

import com.template.templatemod.entity.ModEntities;
import com.template.templatemod.entity.client.CubeMob.CubeMobModel;
import com.template.templatemod.entity.client.CubeMob.CubeMobRenderer;
import com.template.templatemod.entity.client.Giraffe.GiraffeModel;
import com.template.templatemod.entity.client.Giraffe.GiraffeRenderer;
import com.template.templatemod.entity.client.Giraffe.GiraffeSaddleModel;
import com.template.templatemod.entity.client.ModModelLayerLocations;
import com.template.templatemod.entity.client.PuffBall.PuffBallModel;
import com.template.templatemod.entity.client.PuffBall.PuffBallRenderer;
import com.template.templatemod.entity.client.Rock.RockModel;
import com.template.templatemod.entity.client.Rock.RockRenderer;
import com.template.templatemod.entity.client.WarTurtle.WarTurtleModel;
import com.template.templatemod.entity.client.WarTurtle.WarTurtleRenderer;
import com.template.templatemod.keymapping.ModKeyMappings;
import com.template.templatemod.menu.ModMenuTypes;
import com.template.templatemod.menu.custom.WarTurtleScreen;
import com.template.templatemod.networking.packet.TestPacketC2S;
import com.template.templatemod.particles.ModParticles;
import com.template.templatemod.particles.TestParticle;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

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

        ModKeyMappings.register();
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

        EntityRenderers.register(ModEntities.CUBEMOB.get(), CubeMobRenderer::new);
        EntityRenderers.register(ModEntities.PUFFBALL.get(), PuffBallRenderer::new);
        EntityRenderers.register(ModEntities.WAR_TURTLE.get(), WarTurtleRenderer::new);
        EntityRenderers.register(ModEntities.GIRAFFE.get(), GiraffeRenderer::new);

        EntityRenderers.register(ModEntities.ROCK.get(), RockRenderer::new);
    }

    @SubscribeEvent
    public static void  registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayerLocations.CUBEMOB, CubeMobModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayerLocations.PUFFBALL, PuffBallModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayerLocations.WARTURTLE, WarTurtleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayerLocations.WARTURTLE_ARMOR, WarTurtleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayerLocations.GIRAFFE, GiraffeModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayerLocations.GIRAFFE_SADDLE, GiraffeSaddleModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayerLocations.ROCK, RockModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.WARTURTLE_MENU.get(),WarTurtleScreen::new);
    }

    //Custom category
    public static final KeyMapping.Category EXAMPLE_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath("template", "category"));

    @SubscribeEvent
    public static void registerKeymapping(RegisterKeyMappingsEvent event){
        //category
        event.registerCategory(EXAMPLE_CATEGORY);

        //key
        event.register(ModKeyMappings.PRESS_TEST_KEY.get());
    }

    @SubscribeEvent
    public static void OnClientTick(ClientTickEvent.Post event){
        while(ModKeyMappings.PRESS_TEST_KEY.get().consumeClick()){
            ClientPacketDistributor.sendToServer(new TestPacketC2S("Spring",1));
        }
    }

    @SubscribeEvent
    public static void registerGUI(RegisterGuiLayersEvent event){
        event.registerAboveAll(Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"testgui"),(guiGraphics, deltaTracker) -> {
            int x = guiGraphics.guiWidth()/2;
            int y = guiGraphics.guiHeight();

            if(!Minecraft.getInstance().player.isCreative() && !Minecraft.getInstance().player.isSpectator()){
                for (int i = 0 ; i < 4; i++){
                    guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"testgui"),
                            16,16,0,0,x - 95 + i * 16,y-55,16,16);
                }
            }
        });
    }

    @SubscribeEvent
    public static void registerParticleFatory(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ModParticles.TEST_PARTICLE.get(), TestParticle.Provider::new);
    }

}
