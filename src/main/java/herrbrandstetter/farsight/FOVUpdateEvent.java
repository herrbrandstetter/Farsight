package herrbrandstetter.farsight;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FOVUpdateEvent {
    @SubscribeEvent
    public static void updateFOV(net.minecraftforge.client.event.FOVUpdateEvent event) {
        PlayerEntity player = event.getEntity();
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        Minecraft mc = Minecraft.getInstance();

        if (stack.getItem() == ItemRegistry.SPYGLASS.get() && mc.gameSettings.keyBindUseItem.isKeyDown()) {
            event.setNewfov(event.getFov() / 4);
            mc.gameSettings.smoothCamera = true;
        } else {
            mc.gameSettings.smoothCamera = false;
        }
    }
}

