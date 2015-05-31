package dmillerw.entanglement.tile;

import dmillerw.entanglement.entanglement.BlockData;
import dmillerw.entanglement.entanglement.FrequencyHandler;
import dmillerw.entanglement.entanglement.Registry;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAnalyzer extends TileCore implements FrequencyHandler {

    public ForgeDirection orientation;

    private int frequency = 0;

    private Block lastBlock;
    private int lastMeta;

    @Override
    public void onBlockInitialized() {
        onNeighborsUpdate();
    }

    @Override
    public void onBlockRemoved() {
        Registry.INSTANCE.updateData(frequency, null);
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public void changeFrequency(int freq) {
        Registry.INSTANCE.updateData(frequency, null);
        frequency = freq;

        if (orientation == null)
            return;

        int x = xCoord + orientation.getOpposite().offsetX;
        int y = yCoord + orientation.getOpposite().offsetY;
        int z = zCoord + orientation.getOpposite().offsetZ;
        Registry.INSTANCE.updateData(freq, BlockData.of(worldObj, x, y, z));
    }

    public void onNeighborsUpdate() {
        if (orientation == null)
            return;

        int x = xCoord + orientation.getOpposite().offsetX;
        int y = yCoord + orientation.getOpposite().offsetY;
        int z = zCoord + orientation.getOpposite().offsetZ;

        Block block = worldObj.getBlock(x, y, z);
        int meta = worldObj.getBlockMetadata(x, y, z);

        if (block != lastBlock || meta != lastMeta) {
            lastBlock = block;
            lastMeta = meta;

            Registry.INSTANCE.updateData(frequency, BlockData.of(worldObj, x, y, z));
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {
        nbt.setByte("orientation", (byte) orientation.ordinal());
        nbt.setInteger("freq", frequency);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {
        orientation = ForgeDirection.getOrientation(nbt.getByte("orientation"));
        frequency = nbt.getInteger("freq");
    }
}
