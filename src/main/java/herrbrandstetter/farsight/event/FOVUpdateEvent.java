package herrbrandstetter.farsight.event;

import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.util.FarsightConfig;
import herrbrandstetter.farsight.item.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FOVUpdateEvent {
    private static final int fovModifier = FarsightConfig.FOV_MODIFIER.get();
    private static int currentModifier = fovModifier;

    @SubscribeEvent
    public static void updateFOV(net.minecraftforge.client.event.FOVUpdateEvent event) {
        PlayerEntity player = event.getEntity();
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        Minecraft mc = Minecraft.getInstance();

        if (stack.getItem() == ItemRegistry.SPYGLASS.get() && mc.gameSettings.keyBindUseItem.isKeyDown()) {
            event.setNewfov(event.getFov() / currentModifier);
            mc.gameSettings.smoothCamera = FarsightConfig.SMOOTH_CAMERA.get();
            RenderGameOverlayEvent.shouldRenderOverlay = true;
        } else {
            mc.gameSettings.smoothCamera = false;
            RenderGameOverlayEvent.shouldRenderOverlay = false;
            currentModifier = fovModifier;
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

