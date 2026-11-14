package com.template.templatemod.item.Custom;

import com.template.templatemod.component.ModDataComponentTypes;
import com.template.templatemod.tags.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class TestItemComponent extends Item {
    public TestItemComponent(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block ClickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (!level.isClientSide()) {
            if  (ClickedBlock != Blocks.AIR) {
                context.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(),context.getClickedPos());
                context.getItemInHand().set(ModDataComponentTypes.BLOCK_NAME.get(),ClickedBlock.getName().getString());
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(itemStack.get(ModDataComponentTypes.COORDINATES) != null) {
            builder.accept(Component.literal("Last Block changed at " + itemStack.get(ModDataComponentTypes.COORDINATES)));
        }

        if(itemStack.get(ModDataComponentTypes.BLOCK_NAME) != null) {
            builder.accept(Component.literal("Block Name " + itemStack.get(ModDataComponentTypes.BLOCK_NAME)));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
