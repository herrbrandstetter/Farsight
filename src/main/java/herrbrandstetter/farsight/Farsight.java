package herrbrandstetter.farsight;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("farsight")
@Mod.EventBusSubscriber(modid = Farsight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Farsight {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "farsight";

    public Farsight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
