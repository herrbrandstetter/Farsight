package herrbrandstetter.farsight;

import herrbrandstetter.farsight.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SpyglassItem extends Item {
    public SpyglassItem() {
        super(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).rarity(Rarity.RARE));
    }

    /*
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.setHeldItem(handIn, ItemRegistry.SPYGLASS.get().getDefaultInstance());
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }*/
}
