package herrbrandstetter.farsight.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.util.FarsightConfig;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RenderGameOverlayEvent {
    public static boolean shouldRenderOverlay = false;
    private static final ResourceLocation OVERLAY_LOCATION = new ResourceLocation("farsight:textures/screen/spyglass_scope.png");
    private static final Minecraft MC = Minecraft.getInstance();

    /* The following section was mainly written by Alyce Osbourne (https://github.com/AlyceOsbourne).
     * Please check out her own projects.
     * Thanks very much for the great help!
     */

    @SubscribeEvent
    public static void renderOverlay(net.minecraftforge.client.event.RenderGameOverlayEvent.Pre event) {
        if (FarsightConfig.SCOPE_OVERLAY.get()) {
            MainWindow window = event.getWindow();
            MatrixStack matrixStack = event.getMatrixStack();
            int width = window.getScaledWidth();
            int height = window.getScaledHeight();
            int textureHeight = height;
            int textureWidth = textureHeight * 3;

            MC.getTextureManager().bindTexture(OVERLAY_LOCATION);

            if (shouldRenderOverlay) {
                event.setCanceled(true);
                matrixStack.push();
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();

                AbstractGui.blit(matrixStack, 0, 0, ((float) (textureWidth - width) / 2), 0f, width, height, textureWidth, textureHeight);
            }
        }
    }
}
