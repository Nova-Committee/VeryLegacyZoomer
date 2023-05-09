package committee.nova.vlzoomer.client.key;

import committee.nova.vlzoomer.client.config.ClientConfig;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.settings.KeyBinding;

import java.util.EnumSet;

public class ZoomKeyHandler extends KeyBindingRegistry.KeyHandler {
    private final EnumSet<TickType> tickTypes = EnumSet.of(TickType.CLIENT);
    private static boolean hasBeenPressed = false;

    public ZoomKeyHandler(KeyBinding[] keyBindings) {
        super(keyBindings, new boolean[]{false});
    }

    @Override
    public void keyDown(EnumSet<TickType> var1, KeyBinding var2, boolean var3, boolean var4) {
        if (!hasBeenPressed && ClientConfig.isToggleMode()) ClientConfig.toggle();
        hasBeenPressed = true;
    }

    @Override
    public void keyUp(EnumSet<TickType> var1, KeyBinding var2, boolean var3) {
        hasBeenPressed = false;
    }

    @Override
    public EnumSet<TickType> ticks() {
        return tickTypes;
    }

    @Override
    public String getLabel() {
        return "Zoom";
    }

    public static boolean hasBeenPressed() {
        return hasBeenPressed;
    }
}
