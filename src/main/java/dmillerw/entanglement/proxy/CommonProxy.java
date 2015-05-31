package dmillerw.entanglement.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import dmillerw.entanglement.Entanglement;
import dmillerw.entanglement.block.ModBlocks;
import dmillerw.entanglement.core.handler.GuiHandler;
import dmillerw.entanglement.network.PacketHandler;

/**
 * @author dmillerw
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.initialize();

        NetworkRegistry.INSTANCE.registerGuiHandler(Entanglement.instance, new GuiHandler());

        ModBlocks.initialize();
    }
}
