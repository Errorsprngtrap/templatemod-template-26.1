package com.template.templatemod.item;

import com.template.templatemod.TemplateMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TemplateMod.MOD_ID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerSimpleItem(
            "test_item",
            properties ->  properties
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
