package com.template.templatemod.event;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.ModEntities;
import com.template.templatemod.entity.custom.CubeMobEntity;
import com.template.templatemod.item.ModItems;
import com.template.templatemod.potion.ModPotion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TemplateMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.AWKWARD, ModItems.TEST_ITEM.asItem(), ModPotion.TEST_POTION);
    }

    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CUBEMOB.get(), CubeMobEntity.createCubeMobAttributes().build());
    }
}
