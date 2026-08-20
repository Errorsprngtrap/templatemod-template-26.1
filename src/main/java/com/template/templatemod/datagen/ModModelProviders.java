package com.template.templatemod.datagen;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.block.ModBlocks;
import com.template.templatemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProviders extends ModelProvider {

    public ModModelProviders(PackOutput output) {
        super(output, TemplateMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        //items
        itemModels.generateFlatItem(ModItems.TEST_ITEM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_SWORD.get(),ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_PICKAXE.get(),ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_SHOVEL.get(),ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_HOE.get(),ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_AXE.get(),ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateSpear(ModItems.TEST_SPEAR.get());

        itemModels.generateFlatItem(ModItems.TEST_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.TEST_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);

        //blocks
        blockModels.createTrivialCube(ModBlocks.TEST_BLOCK.get());
    }
}
