package committee.nova.vlzoomer.client.handler

import committee.nova.vlzoomer.client.config.ClientConfig
import committee.nova.vlzoomer.client.handler.ClientHandler.ZOOM
import cpw.mods.fml.client.registry.ClientRegistry
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.client.event.FOVUpdateEvent
import net.minecraftforge.common.MinecraftForge
import org.lwjgl.input.Keyboard

object ClientHandler {
  final val ZOOM: KeyBinding = new KeyBinding("key.vlzoomer.zoom", Keyboard.KEY_Z, "category.vlzoomer")
  final val SWITCH: KeyBinding = new KeyBinding("key.vlzoomer.switchMode", Keyboard.KEY_X, "category.vlzoomer")

  def init(): Unit = {
    ClientRegistry.registerKeyBinding(ZOOM)
    ClientRegistry.registerKeyBinding(SWITCH)
    MinecraftForge.EVENT_BUS.register(new ClientHandler)
    FMLCommonHandler.instance().bus().register(new FMLEventHandler)
  }
}

class ClientHandler {
  @SubscribeEvent
  def onFov(e: FOVUpdateEvent): Unit = {
    if (!ClientConfig.isToggleMode) {
      // Weird, but have to do so to avoid glitches
      if (ZOOM.getIsKeyPressed | ZOOM.isPressed) e.newfov = e.fov * ClientConfig.getHoldFovModifier
      return
    }
    e.newfov = e.fov * ClientConfig.getToggled
  }
}
