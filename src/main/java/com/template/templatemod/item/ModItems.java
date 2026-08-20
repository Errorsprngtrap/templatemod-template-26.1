package com.template.templatemod.item;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.armormaterial.ModArmorMaterial;
import com.template.templatemod.toolmaterial.ModToolMaterial;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TemplateMod.MOD_ID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerSimpleItem(
            "test_item",
            properties ->  properties
    );

    public static final DeferredItem<Item> TEST_SWORD = ITEMS.registerSimpleItem(
            "test_sword",
            properties ->  properties.sword(ModToolMaterial.TestMaterial,2,2)
    );
    public static final DeferredItem<Item> TEST_PICKAXE = ITEMS.registerItem(
            "test_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterial.TestMaterial,1,2))
    );
    public static final DeferredItem<Item> TEST_SHOVEL = ITEMS.registerItem(
            "test_shovel",
            properties -> new ShovelItem(ModToolMaterial.TestMaterial,1,-2,properties)
    );
    public static final DeferredItem<Item> TEST_AXE = ITEMS.registerItem(
            "test_axe",
            properties -> new AxeItem(ModToolMaterial.TestMaterial,1,-2,properties)
    );

    public static final DeferredItem<Item> TEST_HOE = ITEMS.registerItem(
            "test_hoe",
            properties -> new HoeItem(ModToolMaterial.TestMaterial,1,-2,properties)
    );

    public static final DeferredItem<Item> TEST_SPEAR = ITEMS.registerItem(
            "test_spear",
            properties -> new Item(properties.spear(ModToolMaterial.TestMaterial,1,2
            ,1,1,1,1,1,1,1)
            )
    );

    public static final DeferredItem<Item> TEST_BOOTS = ITEMS.registerItem(
            "test_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterial.TEST_ARMOR_MATERIAL, ArmorType.BOOTS))
    );

    public static final DeferredItem<Item> TEST_HELMET = ITEMS.registerItem(
            "test_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterial.TEST_ARMOR_MATERIAL, ArmorType.HELMET))
    );

    public static final DeferredItem<Item> TEST_LEGGINGS = ITEMS.registerItem(
            "test_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterial.TEST_ARMOR_MATERIAL, ArmorType.LEGGINGS))
    );

    public static final DeferredItem<Item> TEST_CHESTPLATE = ITEMS.registerItem(
            "test_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterial.TEST_ARMOR_MATERIAL, ArmorType.CHESTPLATE))
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
