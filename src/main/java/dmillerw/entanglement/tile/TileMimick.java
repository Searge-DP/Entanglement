package dmillerw.entanglement.tile;

import cpw.mods.fml.server.FMLServerHandler;
import dmillerw.entanglement.entanglement.BlockData;
import dmillerw.entanglement.entanglement.FrequencyHandler;
import dmillerw.entanglement.entanglement.FrequencyListener;
import dmillerw.entanglement.entanglement.Registry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

public class TileMimick extends TileCore implements FrequencyHandler, FrequencyListener, IInventory {

    private BlockData dataCache;

    private int frequency = 0;

    @Override
    public void onBlockInitialized() {
        Registry.INSTANCE.registerListener(frequency, this);
    }

    @Override
    public void onBlockRemoved() {
        Registry.INSTANCE.removeListener(frequency, this);
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public void changeFrequency(int freq) {
        Registry.INSTANCE.removeListener(frequency, this);
        frequency = freq;
        Registry.INSTANCE.registerListener(frequency, this);
    }

    @Override
    public void onDataUpdate(BlockData data) {
        this.dataCache = data;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {
        nbt.setInteger("freq", frequency);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {
        frequency = nbt.getInteger("freq");
    }

    /* HELPERS */
    public TileEntity getTile() {
        if (dataCache == null)
            return null;

        if (dataCache.dimension == worldObj.provider.dimensionId) {
            return worldObj.getTileEntity(dataCache.x, dataCache.y, dataCache.z);
        } else {
            WorldServer world = FMLServerHandler.instance().getServer().worldServerForDimension(dataCache.dimension);
            return world.getTileEntity(dataCache.x, dataCache.y, dataCache.z);
        }
    }

    public IInventory getInventory() {
        if (dataCache == null)
            return null;

        if (dataCache.canHandleItems()) {
            return (IInventory)getTile();
        } else {
            return null;
        }
    }

    /* IMPLEMENTATIONS */
    @Override
    public int getSizeInventory() {
        IInventory inventory = getInventory();
        return inventory != null ? inventory.getSizeInventory() : 0;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        IInventory inventory = getInventory();
        return inventory != null ? inventory.getStackInSlot(slot) : null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        IInventory inventory = getInventory();
        return inventory != null ? inventory.decrStackSize(slot, amount) : null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        IInventory inventory = getInventory();
        return inventory != null ? getStackInSlotOnClosing(slot) : null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        IInventory inventory = getInventory();
        if (inventory != null) inventory.setInventorySlotContents(slot, itemStack);
    }

    @Override
    public String getInventoryName() {
        IInventory inventory = getInventory();
        return inventory != null ? inventory.getInventoryName() : "";
    }

    @Override
    public boolean hasCustomInventoryName() {
        IInventory inventory = getInventory();
        return inventory != null && inventory.hasCustomInventoryName();
    }

    @Override
    public int getInventoryStackLimit() {
        IInventory inventory = getInventory();
        return inventory != null ? inventory.getInventoryStackLimit() : 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        IInventory inventory = getInventory();
        if (inventory != null) inventory.openInventory();
    }

    @Override
    public void closeInventory() {
        IInventory inventory = getInventory();
        if (inventory != null) inventory.closeInventory();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        IInventory inventory = getInventory();
        return inventory != null ? inventory.isItemValidForSlot(slot, itemStack) : false;
    }
}
