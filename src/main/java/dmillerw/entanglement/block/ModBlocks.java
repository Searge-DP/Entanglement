package dmillerw.entanglement.block;

import cpw.mods.fml.common.registry.GameRegistry;
import dmillerw.entanglement.Entanglement;
import dmillerw.entanglement.block.item.ItemBlockAnalzyer;
import dmillerw.entanglement.tile.TileAnalyzer;
import dmillerw.entanglement.tile.TileMimick;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block analyzer;
    public static Block mimick;

    public static void initialize() {
        analyzer = new BlockAnalyzer().setBlockName("analyzer");
        GameRegistry.registerBlock(analyzer, ItemBlockAnalzyer.class, "analyzer");
        GameRegistry.registerTileEntity(TileAnalyzer.class, Entanglement.ID + ":analyzer");

        mimick = new BlockMimick().setBlockName("mimick");
        GameRegistry.registerBlock(mimick, "mimick");
        GameRegistry.registerTileEntity(TileMimick.class, Entanglement.ID + ":mimick");
    }
}
