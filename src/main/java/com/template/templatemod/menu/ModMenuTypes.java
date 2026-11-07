package com.template.templatemod.menu;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.menu.custom.WarTurtleMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, TemplateMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>,MenuType<WarTurtleMenu>> WARTURTLE_MENU =
            registerMenuTypes("warturtle_menu",WarTurtleMenu::create);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>,MenuType<T>> registerMenuTypes(String name, IContainerFactory<T> factory) {
return MENUS.register(name,()-> IMenuTypeExtension.create(factory));
    };
    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
