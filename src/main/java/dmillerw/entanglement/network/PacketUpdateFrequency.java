package dmillerw.entanglement.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dmillerw.entanglement.entanglement.FrequencyHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class PacketUpdateFrequency implements IMessage {

    public static void decrement(TileEntity tileEntity) {
        PacketHandler.INSTANCE.sendToServer(new PacketUpdateFrequency(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, 0));
    }

    public static void increment(TileEntity tileEntity) {
        PacketHandler.INSTANCE.sendToServer(new PacketUpdateFrequency(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, 1));
    }

    public int x;
    public int y;
    public int z;

    public byte type; // 0 is decrement, 1 is increment

    public PacketUpdateFrequency() {

    }

    public PacketUpdateFrequency(int x, int y, int z, int type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = (byte) type;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(type);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        type = buf.readByte();
    }

    public static class Handler implements IMessageHandler<PacketUpdateFrequency, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdateFrequency message, MessageContext ctx) {
            World world = ctx.getServerHandler().playerEntity.worldObj;
            FrequencyHandler handler = (FrequencyHandler) world.getTileEntity(message.x, message.y, message.z);
            if (handler != null) {
                if (message.type == 1) {
                    handler.changeFrequency(handler.getFrequency() + 1);
                } else if (message.type == 0) {
                    handler.changeFrequency(handler.getFrequency() - 1);
                }
                world.markBlockForUpdate(message.x, message.y, message.z);
            }
            return null;
        }
    }
}
