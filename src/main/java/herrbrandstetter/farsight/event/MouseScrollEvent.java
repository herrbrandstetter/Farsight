package herrbrandstetter.farsight.event;

import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.util.FarsightConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MouseScrollEvent {
    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollEvent event) {
        if (FarsightConfig.scrolling.get()) {
            int delta = (int) event.getScrollDelta();

            if (RenderGameOverlayEvent.shouldRenderOverlay) {
                event.setCanceled(true);

                if (delta != 0) {
                    if (delta > 0) {
                        FOVUpdateEvent.changeModifier(1);
                    } else {
                        FOVUpdateEvent.changeModifier(-1);
                    }
                }
            }
        }
    }
}