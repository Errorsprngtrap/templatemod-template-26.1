package com.template.templatemod.datagen;

import com.template.templatemod.TemplateMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = TemplateMod.MOD_ID)
public class ModDataGenerators {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packoutput = generator.getPackOutput();
        var lookupProvider= event.getLookupProvider();

        generator.addProvider(true,new ModModelProviders(packoutput));
        generator.addProvider(true, new LootTableProvider(packoutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
                lookupProvider)
        );
        generator.addProvider(true,new ModBlockTagProvider(packoutput, lookupProvider));
        generator.addProvider(true,new ModRecipeProvider.Runner(packoutput, lookupProvider));
        generator.addProvider(true,new ModItemTagProvider(packoutput, lookupProvider));

        generator.addProvider(true,new ModDataPackProvider(packoutput, lookupProvider));
        generator.addProvider(true,new ModEquipmentAsset(packoutput));



    }


}
