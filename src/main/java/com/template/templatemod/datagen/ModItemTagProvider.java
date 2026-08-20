package com.template.templatemod.datagen;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TemplateMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //do add tag do teh same as block so tag .add ect
        tag(ItemTags.SHOVELS)
                .add(ModItems.TEST_SHOVEL.get());

        tag(ItemTags.AXES)
                .add(ModItems.TEST_AXE.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.TEST_PICKAXE.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.TEST_SWORD.get());

        tag(ItemTags.HOES)
                .add(ModItems.TEST_HOE.get());

        tag(ItemTags.SPEARS)
                .add(ModItems.TEST_SPEAR.get());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.TEST_CHESTPLATE.get());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.TEST_BOOTS.get());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.TEST_LEGGINGS.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.TEST_HELMET.get());

    }
}
