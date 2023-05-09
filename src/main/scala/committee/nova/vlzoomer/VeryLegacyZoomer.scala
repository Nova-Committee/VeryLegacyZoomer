package committee.nova.vlzoomer

import committee.nova.vlzoomer.VeryLegacyZoomer.MODID
import committee.nova.vlzoomer.client.config.ClientConfig
import committee.nova.vlzoomer.client.handler.ClientHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.relauncher.Side
import org.apache.logging.log4j.LogManager

@Mod(modid = MODID, useMetadata = true, modLanguage = "scala",
  acceptableRemoteVersions = "*", guiFactory = "committee.nova.vlzoomer.client.gui.factory.GuiFactory")
object VeryLegacyZoomer {
  final val MODID = "vlzoomer"
  final val LOGGER = LogManager.getLogger(MODID)

  @EventHandler def preInit(e: FMLPreInitializationEvent): Unit = {
    if (e.getSide != Side.CLIENT) LOGGER.warn("This mod should only be installed on client.")
    else {
      ClientConfig.init(e.getSuggestedConfigurationFile)
      ClientHandler.init()
    }
  }
}
