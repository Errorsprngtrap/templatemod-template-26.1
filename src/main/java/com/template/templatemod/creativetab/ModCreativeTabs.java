package com.template.templatemod.creativetab;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.block.ModBlocks;
import com.template.templatemod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TemplateMod.MOD_ID);


    public static final Supplier<CreativeModeTab> TEST_TAB = CREATIVE_MODE_TABS.register("test_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEST_ITEM.get()))
                    .title(Component.translatable("creativetabs.templatemod.test_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.TEST_ITEM);
                        output.accept(ModBlocks.TEST_BLOCK);
                        output.accept(ModItems.TEST_SWORD);
                        output.accept(ModItems.TEST_PICKAXE);
                        output.accept(ModItems.TEST_AXE);
                        output.accept(ModItems.TEST_SHOVEL);
                        output.accept(ModItems.TEST_HOE);
                        output.accept(ModItems.TEST_SPEAR);

                        output.accept(ModItems.TEST_HELMET);
                        output.accept(ModItems.TEST_CHESTPLATE);
                        output.accept(ModItems.TEST_BOOTS);
                        output.accept(ModItems.TEST_LEGGINGS);

                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
