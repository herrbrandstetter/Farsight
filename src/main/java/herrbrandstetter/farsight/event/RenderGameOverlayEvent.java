package herrbrandstetter.farsight.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.util.FarsightConfig;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderGameOverlayEvent {
    public static boolean shouldRenderOverlay = false;
    private static final Minecraft MC = Minecraft.getInstance();

    /* The following section was mainly written by Alyce Osbourne (https://github.com/AlyceOsbourne).
     * Please check out her own projects.
     * Thanks very much for the great help!
     */

    @SubscribeEvent
    @SuppressWarnings("deprecation")
    public static void renderOverlay(net.minecraftforge.client.event.RenderGameOverlayEvent.Pre event) {
        if (shouldRenderOverlay && FarsightConfig.SCOPE_OVERLAY.get()) {
            MC.gameRenderer.renderHand = false;
            event.setCanceled(true);

            MainWindow window = event.getWindow();
            MatrixStack matrixStack = event.getMatrixStack();
            int width = window.getGuiScaledWidth();
            int height = window.getGuiScaledHeight();
            int textureWidth = height * 3;

            MC.getTextureManager().bind(FarsightConfig.WHAT_MEME.get()
                    ? new ResourceLocation("farsight_spyglasses:textures/screen/what_scope.png")
                    : new ResourceLocation("farsight_spyglasses:textures/screen/spyglass_scope.png"));
            matrixStack.pushPose();
            RenderSystem.enableAlphaTest();
            RenderSystem.enableBlend();
            AbstractGui.blit(matrixStack, 0, 0, ((float) (textureWidth - width) / 2), 0f, width, height, textureWidth, height);
        } else {
            MC.gameRenderer.renderHand = true;
        }
    }
}

