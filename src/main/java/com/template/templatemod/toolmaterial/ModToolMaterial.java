package com.template.templatemod.toolmaterial;

import com.template.templatemod.datagen.ModBlockTagProvider;
import com.template.templatemod.tags.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterial {
    public static final ToolMaterial TestMaterial = new ToolMaterial(
            ModTags.Blocks.INCORRECT_FOR_TEST_TOOL,
            10,
            10,
            10,
            10,
            ItemTags.REPAIRS_DIAMOND_ARMOR
    );


}
