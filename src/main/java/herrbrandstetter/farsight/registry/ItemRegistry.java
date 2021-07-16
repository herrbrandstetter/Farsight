package herrbrandstetter.farsight.registry;

import herrbrandstetter.farsight.Farsight;
import herrbrandstetter.farsight.item.SpyglassItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Farsight.MOD_ID);

    public static final RegistryObject<Item> SPYGLASS = ITEMS.register("spyglass", SpyglassItem::new);
}
