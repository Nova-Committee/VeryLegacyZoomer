package committee.nova.vlzoomer.client.gui.screen

import committee.nova.vlzoomer.VeryLegacyZoomer
import committee.nova.vlzoomer.client.config.ClientConfig
import cpw.mods.fml.client.config.GuiConfig
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.common.config.{ConfigElement, Configuration}

class GuiScreenConfig(parent: GuiScreen) extends GuiConfig(parent,
  new ConfigElement(ClientConfig.getConfig.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements,
  VeryLegacyZoomer.MODID,
  VeryLegacyZoomer.MODID,
  false,
  false,
  "VeryLegacyZoomer Config"
)
