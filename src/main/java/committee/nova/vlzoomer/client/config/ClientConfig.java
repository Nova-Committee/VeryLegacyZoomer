package committee.nova.vlzoomer.client.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.Configuration;

import java.io.File;

public class ClientConfig {
    private static Configuration cfg;
    private static float fovModifier;
    private static boolean toggle;
    private static float toggleFovModifier1;
    private static float toggleFovModifier2;
    private static int toggleStatus;

    public static void init(File file) {
        cfg = new Configuration(file);
        cfg.load();
        toggle = cfg.get("settings", "toggleMode", false,
                "false: HOLD MODE, true: TOGGLE MODE").getBoolean(false);
        fovModifier = getClamped(cfg.get("settings", "holdFovModifier", 0.125,
                "(HOLD MODE ONLY) Fov modifier when the zoom key is pressed.").getDouble(0.125));
        toggleFovModifier1 = getClamped(cfg.get("settings", "toggleFovModifier1", 0.5,
                "(TOGGLE MODE ONLY) Fov modifier when the zoom key is pressed once").getDouble(0.5));
        toggleFovModifier2 = getClamped(cfg.get("settings", "toggleFovModifier2", 0.125,
                "(TOGGLE MODE ONLY) Fov modifier when the zoom key is pressed twice").getDouble(0.125));
        cfg.save();
    }

    public static Configuration getCfg() {
        return cfg;
    }

    private static float getClamped(double raw) {
        return MathHelper.clamp_float((float) raw, .01F, 5.0F);
    }

    public static boolean isToggleMode() {
        return toggle;
    }

    public static void switchToggleMode() {
        toggle = !toggle;
        if (!toggle) toggleStatus = 0;
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc == null) return;
        final EntityClientPlayerMP player = mc.thePlayer;
        if (player == null) return;
        player.sendQueue.addToSendQueue(new Packet3Chat(I18n.getString(String.format("msg.vlzoomer.switchTo.%s", toggle ? "toggle" : "hold")), false));
    }

    public static float getFovModifier() {
        return fovModifier;
    }

    public static void toggle() {
        toggleStatus++;
        toggleStatus %= 3;
    }

    public static float getToggled() {
        switch (toggleStatus) {
            case 1:
                return toggleFovModifier1;
            case 2:
                return toggleFovModifier2;
            default:
                return 1.0F;
        }
    }
}
