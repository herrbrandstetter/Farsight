package herrbrandstetter.farsight.util;

import herrbrandstetter.farsight.Farsight;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class FarsightKeybinding {
    public static KeyBinding zoomingKey = FarsightConfig.ZOOM_KEY.get()
            ? new KeyBinding("key." + Farsight.MOD_ID + ".zooming_key", GLFW.GLFW_KEY_Z, "key.category." + Farsight.MOD_ID)
            : new KeyBinding("key." + Farsight.MOD_ID + ".zooming_key", GLFW.GLFW_KEY_ESCAPE, "key.category." + Farsight.MOD_ID);
}