package herrbrandstetter.farsight.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class FarsightConfig {
    public static ForgeConfigSpec clientConfig;

    public static ForgeConfigSpec.IntValue fovModifier;
    public static ForgeConfigSpec.BooleanValue smoothCamera;
    public static ForgeConfigSpec.BooleanValue scopeOverlay;
    public static ForgeConfigSpec.BooleanValue scrolling;
    public static ForgeConfigSpec.BooleanValue zoomKey;
    public static ForgeConfigSpec.BooleanValue whatMeme;

    static {
        ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();

        clientBuilder.push("settings");
        fovModifier = clientBuilder
                .comment("How much the FOV should change when zooming", "Calculated as current FOV divided by this number (higher value equals greater zooming effect)")
                .defineInRange("fovModifier", 6, 2, 16);
        smoothCamera = clientBuilder
                .comment("Whether smooth camera movement should be enabled while zooming")
                .define("smoothCamera", true);
        scopeOverlay = clientBuilder
                .comment("Whether the scope overlay should be rendered while zooming")
                .define("scopeOverlay", true);
        scrolling = clientBuilder
                .comment("Whether scrolling should be enabled to dynamically change the FOV while zooming", "If enabled, FOV modifier is the initial zoom amount")
                .define("scrolling", false);
        zoomKey = clientBuilder
                .comment("Whether a zooming keybinding should be enabled to activate the spyglass from anywhere in the inventory",
                        "Required when using the spyglass as a Curio",
                        "If disabled, is not bound by default and has no effect")
                .define("zoomingKey", false);
        whatMeme = clientBuilder
                .comment("Whether the Sanctuary Guardian What meme should be shown when zooming", "If enabled, sound and scope assets are replaced with the respective What assets")
                .define("whatMeme", false);
        clientBuilder.pop();

        clientConfig = clientBuilder.build();
    }
}
