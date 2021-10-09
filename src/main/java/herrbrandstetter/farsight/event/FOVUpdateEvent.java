package herrbrandstetter.farsight.event;

import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.registry.SoundRegistry;
import herrbrandstetter.farsight.util.FarsightConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FOVUpdateEvent {
    public static boolean zooming = false;
    private static final int FOV_MODIFIER = FarsightConfig.fovModifier.get();
    private static final Minecraft MC = Minecraft.getInstance();
    private static int currentModifier = FOV_MODIFIER;
    private static int ticksZoomed = 0;

    @SubscribeEvent
    public static void updateFOV(net.minecraftforge.client.event.FOVUpdateEvent event) {
        if (zooming) {
            ticksZoomed++;

            if (ticksZoomed == 1) {
                if (KeyInputEvent.isZoomingByKey) {
                    if (MC.level != null && MC.player != null) {
                        System.out.println(true);
                        MC.level.playSound(MC.player, MC.player.blockPosition(), FarsightConfig.whatMeme.get()
                                ? SoundRegistry.SPYGLASS_WHAT.get()
                                : SoundRegistry.SPYGLASS_EXTENSION.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
                    }
                }
            }

            event.setNewfov(event.getFov() / currentModifier);
            MC.options.smoothCamera = FarsightConfig.smoothCamera.get();
            RenderGameOverlayEvent.shouldRenderOverlay = true;
        } else {
            MC.options.smoothCamera = false;
            RenderGameOverlayEvent.shouldRenderOverlay = false;
            currentModifier = FOV_MODIFIER;
            ticksZoomed = 0;
        }
    }

    public static void changeModifier(int scroll) {
        if (currentModifier >= 3 && currentModifier <= 15) {
            currentModifier += scroll;
        } else if (currentModifier == 2) {
            currentModifier += 1;
        } else if (currentModifier == 16) {
            currentModifier -= 1;
        }
    }
}