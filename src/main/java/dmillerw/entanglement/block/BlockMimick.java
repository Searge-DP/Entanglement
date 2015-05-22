package dmillerw.entanglement.block;

import dmillerw.entanglement.tile.TileMimick;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMimick extends BlockContainer {

    public BlockMimick() {
        super(Material.iron);

        setHardness(2F);
        setResistance(2F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileMimick();
    }
}