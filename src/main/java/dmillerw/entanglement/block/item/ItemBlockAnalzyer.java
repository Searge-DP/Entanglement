package dmillerw.entanglement.block.item;

import dmillerw.entanglement.tile.TileAnalyzer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemBlockAnalzyer extends ItemBlock {

    public ItemBlockAnalzyer(Block block) {
        super(block);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        if (result) {
            TileAnalyzer tile = (TileAnalyzer) world.getTileEntity(x, y, z);
            tile.orientation = ForgeDirection.getOrientation(side);
        }

        return result;
    }
}
