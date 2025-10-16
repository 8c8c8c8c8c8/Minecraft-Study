package zz.dbrvkf.minecraft_study.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import zz.dbrvkf.minecraft_study.MinecraftStudy;

public class GemPolishingStationScreen extends AbstractContainerScreen<GemPolishingStationMenu> {
    private static final ResourceLocation TEXTURE_LOCATION =
            new ResourceLocation(MinecraftStudy.MOD_ID, "textures/gui/gem_polishing_station.png");

    public GemPolishingStationScreen(GemPolishingStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        inventoryLabelY = 10000;
        titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE_LOCATION);
        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(TEXTURE_LOCATION, width, height, 0, 0, this.imageWidth, this.imageHeight);
        renderProgressArrow(pGuiGraphics, width, height);
    }

    private void renderProgressArrow(GuiGraphics pGuiGraphics, int width, int height) {
        if (!menu.isCrafting())
            return;
        pGuiGraphics.blit(TEXTURE_LOCATION, width + 85, height + 30, 176, 0, 8, menu.getScaledProgress());
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
