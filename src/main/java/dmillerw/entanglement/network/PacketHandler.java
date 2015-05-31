package dmillerw.entanglement.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import dmillerw.entanglement.Entanglement;

/**
 * @author dmillerw
 */
public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Entanglement.ID);

    public static void initialize() {
        INSTANCE.registerMessage(PacketUpdateFrequency.Handler.class, PacketUpdateFrequency.class, 0, Side.SERVER);
    }
}
