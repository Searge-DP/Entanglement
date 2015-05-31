package dmillerw.entanglement.entanglement;

import com.google.common.collect.Lists;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.EnumSet;
import java.util.List;

/**
 * @author dmillerw
 */
public class BlockData {

    public static BlockData of(World world, int x, int y, int z) {
        BlockData blockData = new BlockData();
        blockData.dimension = world.provider.dimensionId;
        blockData.x = x;
        blockData.y = y;
        blockData.z = z;

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null) {
            List<Capability> list = Lists.newArrayList();

            if (tileEntity instanceof IInventory)
                list.add(Capability.ITEM);
            if (tileEntity instanceof IFluidHandler)
                list.add(Capability.FLUID);

            //TODO Power

            blockData.capabilities = EnumSet.copyOf(list);
        }

        return blockData;
    }

    public int dimension;

    public int x;
    public int y;
    public int z;

    public EnumSet<Capability> capabilities = EnumSet.noneOf(Capability.class);

    public boolean canHandleItems() {
        return capabilities.contains(Capability.ITEM);
    }

    public boolean canHandleFluids() {
        return capabilities.contains(Capability.FLUID);
    }

    public boolean canHandlePower() {
        return capabilities.contains(Capability.POWER);
    }

    public enum Capability {
        ITEM,
        FLUID,
        POWER
    }
}
