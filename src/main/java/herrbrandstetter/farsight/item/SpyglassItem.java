package herrbrandstetter.farsight.item;

import herrbrandstetter.farsight.event.FOVUpdateEvent;
import herrbrandstetter.farsight.registry.SoundRegistry;
import herrbrandstetter.farsight.util.FarsightConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SpyglassItem extends Item {
    private static int ticksZoomed = 0;

    public SpyglassItem() {
        super(new Item.Properties().tab(ItemGroup.TAB_TOOLS).stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public ActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {
        pPlayer.startUsingItem(pHand);
        FOVUpdateEvent.zooming = true;
        ticksZoomed++;

        if (ticksZoomed == 1)
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), FarsightConfig.whatMeme.get()
                    ? SoundRegistry.SPYGLASS_WHAT.get()
                    : SoundRegistry.SPYGLASS_EXTENSION.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);

        return super.use(pLevel, pPlayer, pHand);
    }

    @Override
    public void releaseUsing(ItemStack pStack, World pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        FOVUpdateEvent.zooming = false;
        ticksZoomed = 0;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return Integer.MAX_VALUE;
    }
}