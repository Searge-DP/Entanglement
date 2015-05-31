package dmillerw.entanglement;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dmillerw.entanglement.proxy.CommonProxy;

@Mod(modid = Entanglement.ID, name = Entanglement.NAME, version = Entanglement.VERSION)
public class Entanglement {

    public static final String ID = "entanglement";
    public static final String NAME = "Entanglement";
    public static final String VERSION = "%MOD_VERSION%";

    @Mod.Instance(ID)
    public static Entanglement instance;

    @SidedProxy(serverSide = "dmillerw.entanglement.proxy.CommonProxy", clientSide = "dmillerw.entanglement.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}
