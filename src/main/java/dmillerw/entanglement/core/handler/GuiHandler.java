package dmillerw.entanglement.core.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import dmillerw.entanglement.client.gui.GuiUpdateFrequency;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return ID == 0 ? new GuiUpdateFrequency(world.getTileEntity(x, y, z)) : null;
    }
}
