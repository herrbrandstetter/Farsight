package herrbrandstetter.farsight;

import herrbrandstetter.farsight.item.ItemRegistry;
import herrbrandstetter.farsight.util.FarsightConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("farsight_spyglasses")
@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Farsight {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "farsight_spyglasses";

    public Farsight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, FarsightConfig.CLIENT_CONFIG);
    }
}
