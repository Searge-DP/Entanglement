package dmillerw.entanglement.block;

import dmillerw.entanglement.Entanglement;
import dmillerw.entanglement.tile.TileCore;
import dmillerw.entanglement.tile.TileMimick;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMimick extends BlockContainer {

    public BlockMimick() {
        super(Material.iron);

        setHardness(2F);
        setResistance(2F);
        setCreativeTab(CreativeTabs.tabRedstone);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float fx, float fy, float fz) {
        if (world.isRemote) {
            entityPlayer.openGui(Entanglement.instance, 0, world, x, y, z);
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileCore tileCore = (TileCore) world.getTileEntity(x, y, z);
        if (tileCore != null) {
            tileCore.onBlockRemoved();
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileMimick();
    }
}
