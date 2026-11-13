package com.template.templatemod.datagen;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.ModEntities;
import com.template.templatemod.entity.custom.GiraffeEntity;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {
    public ModEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TemplateMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(EntityTypeTags.CAN_EQUIP_SADDLE)
                .add(ModEntities.GIRAFFE.get())
        ;
    }
}
