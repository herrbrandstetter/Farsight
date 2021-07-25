package herrbrandstetter.farsight.event;

import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.registry.ItemRegistry;
import herrbrandstetter.farsight.util.FarsightConfig;
import herrbrandstetter.farsight.util.FarsightKeybinding;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyInputEvent {
    public static boolean isZoomingByKey = false;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        if (mc.level == null || player == null) return;

        if (FarsightConfig.ZOOM_KEY.get()) {
            isZoomingByKey = FarsightKeybinding.zoomingKey.isDown()
                    && (player.inventory.contains(ItemRegistry.SPYGLASS.get().getDefaultInstance())
                    || CuriosApi.getCuriosHelper().findEquippedCurio(ItemRegistry.SPYGLASS.get(), player).isPresent());
        }
    }
}

