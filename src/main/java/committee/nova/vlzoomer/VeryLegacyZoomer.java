package committee.nova.vlzoomer;

import committee.nova.vlzoomer.client.config.ClientConfig;
import committee.nova.vlzoomer.client.event.ClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;

import java.util.logging.Logger;

@Mod(modid = VeryLegacyZoomer.MODID, useMetadata = true)
@NetworkMod(clientSideRequired = true)
public class VeryLegacyZoomer {
    public static final String MODID = "vlzoomer";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        final Logger logger = e.getModLog();
        if (e.getSide() != Side.CLIENT) {
            logger.warning("This mod should only be installed on client.");
            return;
        }
        ClientConfig.init(e.getSuggestedConfigurationFile());
        ClientHandler.init();
    }
}
