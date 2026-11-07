package com.template.templatemod.menu.custom;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.custom.WarTurtleEntity;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class WarTurtleScreen extends AbstractContainerScreen<WarTurtleMenu> {
    private static final Identifier GUI_TEXTURE_T0 =
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/warturtle/gui/war_turtle_base.png");
    private static final Identifier GUI_TEXTURE_T1 =
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/warturtle/gui/war_turtle_inv_1.png");
    private static final Identifier GUI_TEXTURE_T2 =
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/warturtle/gui/war_turtle_inv_2.png");
    private static final Identifier GUI_TEXTURE_T3 =
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID,"textures/entity/warturtle/gui/war_turtle_inv_3.png");

    private final WarTurtleEntity warturtle;

    public WarTurtleScreen(WarTurtleMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.warturtle = menu.warturtle;
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 20;
        titleLabelY = 5;

        inventoryLabelX = 2222222;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(!warturtle.has1Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T0, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        } else if(warturtle.has1Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T1, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        }

        if(warturtle.has2Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T2, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        }
        if(warturtle.has3Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T3, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        }

        InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, x + 8, y + 9, x + 90, y + 80, 30, 0.05F,
                mouseX, mouseY, this.warturtle);
    }

}
