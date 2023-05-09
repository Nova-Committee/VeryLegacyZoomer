package committee.nova.vlzoomer.client.event;

import committee.nova.vlzoomer.client.config.ClientConfig;
import committee.nova.vlzoomer.client.key.ModeKeyHandler;
import committee.nova.vlzoomer.client.key.ZoomKeyHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.input.Keyboard;

public class ClientHandler {
    public static void init() {
        KeyBindingRegistry.registerKeyBinding(new ZoomKeyHandler(new KeyBinding[]{new KeyBinding("key.vlzoomer.zoom", Keyboard.KEY_Z)}));
        KeyBindingRegistry.registerKeyBinding(new ModeKeyHandler(new KeyBinding[]{new KeyBinding("key.vlzoomer.switchMode", Keyboard.KEY_X)}));
        MinecraftForge.EVENT_BUS.register(new ClientHandler());
    }

    @ForgeSubscribe
    public void onFov(FOVUpdateEvent e) {
        if (!ClientConfig.isToggleMode()) {
            if (ZoomKeyHandler.hasBeenPressed()) e.newfov = e.fov * ClientConfig.getFovModifier();
            return;
        }
        e.newfov = e.fov * ClientConfig.getToggled();

    }
}
