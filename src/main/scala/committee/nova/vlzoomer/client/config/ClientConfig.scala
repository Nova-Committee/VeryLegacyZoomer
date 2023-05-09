package committee.nova.vlzoomer.client.config

import net.minecraft.client.Minecraft
import net.minecraft.util.{ChatComponentTranslation, ChatStyle, EnumChatFormatting}
import net.minecraftforge.common.config.Configuration

import java.io.File

object ClientConfig {
  private var cfg: Configuration = _
  private var toggle: Boolean = _
  private var fovModifier: Float = _
  private var toggleFovModifier1: Float = _
  private var toggleFovModifier2: Float = _
  private var toggleStatus: Int = 0

  def init(file: File): Unit = {
    cfg = new Configuration(file)
    sync()
  }

  def sync(): Unit = {
    cfg.load()
    toggle = cfg.getBoolean("toggleMode", Configuration.CATEGORY_GENERAL, false, "false: HOLD MODE, true: TOGGLE MODE")
    fovModifier = cfg.getFloat("holdFovModifier", Configuration.CATEGORY_GENERAL, .125F, .01F, 5.0F,
      "(HOLD MODE ONLY) Fov modifier when the zoom key is pressed.")
    toggleFovModifier1 = cfg.getFloat("toggleFovModifier1", Configuration.CATEGORY_GENERAL, .5F, .01F, 5.0F,
      "(TOGGLE MODE ONLY) Fov modifier when the zoom key is pressed once.")
    toggleFovModifier2 = cfg.getFloat("toggleFovModifier2", Configuration.CATEGORY_GENERAL, .125F, .01F, 5.0F,
      "(TOGGLE MODE ONLY) Fov modifier when the zoom key is pressed twice.")
    cfg.save()
  }

  def getConfig: Configuration = cfg

  def isToggleMode: Boolean = toggle

  def switchToggleMode(): Unit = {
    toggle = !toggle
    toggleStatus = 0
    val mc = Minecraft.getMinecraft
    if (mc == null) return
    val player = mc.thePlayer
    if (player == null) return
    player.addChatMessage(new ChatComponentTranslation("msg.vlzoomer.switchTo." + (if (toggle) "toggle" else "hold"))
      .setChatStyle(new ChatStyle().setColor(if (toggle) EnumChatFormatting.AQUA else EnumChatFormatting.DARK_GREEN)))
  }

  def getHoldFovModifier: Float = fovModifier

  def switchStatus(): Unit = {
    toggleStatus += 1
    toggleStatus %= 3
  }

  def getToggled: Float = toggleStatus match {
    case 1 => toggleFovModifier1
    case 2 => toggleFovModifier2
    case _ => 1.0F
  }
}
