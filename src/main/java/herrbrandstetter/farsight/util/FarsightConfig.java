package herrbrandstetter.farsight.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class FarsightConfig {
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue FOV_MODIFIER;
    public static ForgeConfigSpec.BooleanValue SMOOTH_CAMERA;
    public static ForgeConfigSpec.BooleanValue SCOPE_OVERLAY;
    public static ForgeConfigSpec.BooleanValue SCROLLING;
    public static ForgeConfigSpec.BooleanValue ZOOM_KEY;
    public static ForgeConfigSpec.BooleanValue WHAT_MEME;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.push("settings");
        FOV_MODIFIER = CLIENT_BUILDER
                .comment("How much the FOV should change when zooming", "Calculated as current FOV divided by this number (higher value equals greater zooming effect)")
                .defineInRange("fovModifier", 6, 2, 16);
        SMOOTH_CAMERA = CLIENT_BUILDER
                .comment("Whether smooth camera movement should be enabled while zooming")
                .define("smoothCamera", true);
        SCOPE_OVERLAY = CLIENT_BUILDER
                .comment("Whether the scope overlay should be rendered while zooming")
                .define("scopeOverlay", true);
        SCROLLING = CLIENT_BUILDER
                .comment("Whether scrolling should be enabled to dynamically change the FOV while zooming", "If enabled, FOV modifier is the initial zoom amount")
                .define("scrolling", false);
        ZOOM_KEY = CLIENT_BUILDER
                .comment("Whether a zooming keybinding should be enabled to activate the spyglass from anywhere in the inventory",
                        "Required when using the spyglass as a Curio",
                        "If disabled, is not bound by default and has no effect")
                .define("zoomingKey", false);
        WHAT_MEME = CLIENT_BUILDER
                .comment("Whether the Sanctuary Guardian What meme should be shown when zooming", "If enabled, sound and scope assets are replaced with the respective What assets")
                .define("whatMeme", false);
        CLIENT_BUILDER.pop();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
