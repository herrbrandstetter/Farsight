package herrbrandstetter.farsight.event;

import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.registry.SoundRegistry;
import herrbrandstetter.farsight.util.FarsightConfig;
import herrbrandstetter.farsight.registry.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FOVUpdateEvent {
    private static final int FOV_MODIFIER = FarsightConfig.FOV_MODIFIER.get();
    private static final Minecraft MC = Minecraft.getInstance();
    private static PlayerEntity player;
    private static int currentModifier = FOV_MODIFIER;
    private static int ticksZoomed = 0;

    @SubscribeEvent
    public static void updateFOV(net.minecraftforge.client.event.FOVUpdateEvent event) {
        player = event.getEntity();
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);

        if ((stack.getItem() == ItemRegistry.SPYGLASS.get() && MC.gameSettings.keyBindUseItem.isKeyDown()) || KeyInputEvent.isZoomingByKey) {
            event.setNewfov(event.getFov() / currentModifier);
            MC.gameSettings.smoothCamera = FarsightConfig.SMOOTH_CAMERA.get();
            RenderGameOverlayEvent.shouldRenderOverlay = true;
            ticksZoomed++;

            // 'w' for What sound / 'n' for normal sound
            if (ticksZoomed == 1) playSound(FarsightConfig.WHAT_MEME.get() ? 'w' : 'n');
        } else {
            MC.gameSettings.smoothCamera = false;
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

    private static void playSound(char option) {
        if (option == 'n') {
            MC.world.playSound(player, player.getPosition(), SoundRegistry.SPYGLASS_EXTENSION.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        } else if (option == 'w') {
            MC.world.playSound(player, player.getPosition(), SoundRegistry.SPYGLASS_WHAT.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
    }
}

