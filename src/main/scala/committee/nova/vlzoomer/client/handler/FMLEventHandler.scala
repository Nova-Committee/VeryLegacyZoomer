package committee.nova.vlzoomer.client.handler

import committee.nova.vlzoomer.client.config.ClientConfig
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import cpw.mods.fml.common.gameevent.TickEvent.Phase

class FMLEventHandler {
  @SubscribeEvent
  def onClientTick(e: TickEvent.ClientTickEvent): Unit = {
    if (e.phase != Phase.END) return
    if (ClientHandler.SWITCH.isPressed) ClientConfig.switchToggleMode()
    else if (ClientConfig.isToggleMode && ClientHandler.ZOOM.isPressed) ClientConfig.switchStatus()
  }
}
