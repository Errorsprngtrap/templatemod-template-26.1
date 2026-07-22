package com.template.templatemod.datagen;

import com.template.templatemod.TemplateMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = TemplateMod.MOD_ID)
public class ModDataGenerators {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packoutput = generator.getPackOutput();

        generator.addProvider(true,new ModModelProviders(packoutput));

    }


}
