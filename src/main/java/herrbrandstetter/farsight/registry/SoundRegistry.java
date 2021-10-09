package herrbrandstetter.farsight.registry;

import herrbrandstetter.farsight.Farsight;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Farsight.MOD_ID);

    public static final RegistryObject<SoundEvent> SPYGLASS_EXTENSION = SOUNDS.register("item.spyglass_extension",
            () -> new SoundEvent(new ResourceLocation(Farsight.MOD_ID, "item.spyglass_extension")));
    public static final RegistryObject<SoundEvent> SPYGLASS_WHAT = SOUNDS.register("item.what",
            () -> new SoundEvent(new ResourceLocation(Farsight.MOD_ID, "item.what")));
}