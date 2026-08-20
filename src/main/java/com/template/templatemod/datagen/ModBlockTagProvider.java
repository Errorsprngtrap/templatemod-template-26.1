package com.template.templatemod.datagen;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TemplateMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //here you put tags ect with tag add ect
        tag(ModTags.Blocks.NEEDS_TEST_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .add(Blocks.OBSIDIAN);

        tag(ModTags.Blocks.INCORRECT_FOR_TEST_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_TEST_TOOL);
    }
}
