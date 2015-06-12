package dmillerw.entanglement.client.gui;

import dmillerw.entanglement.network.PacketUpdateFrequency;
import dmillerw.entanglement.tile.IFreqProvider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;

/**
 * @author dmillerw
 */
public class GuiUpdateFrequency extends GuiScreen {

    private TileEntity handler;

    public GuiUpdateFrequency(TileEntity handler) {
        this.handler = handler;
    }

    @Override
    public void initGui() {
        buttonList.add(new GuiButton(0, width / 2 - 20 - 200, height / 2 - 50, "Dec"));
        buttonList.add(new GuiButton(1, width / 2 + 20, height / 2 - 50, "Inc"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float button) {
        super.drawScreen(mouseX, mouseY, button);
        String freq = Integer.toString(((IFreqProvider)handler).getFrequency());
        drawCenteredString(mc.fontRenderer, freq, width / 2, height / 2 - 50, 0xFFFFFF);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            PacketUpdateFrequency.decrement(handler);
        } else if (button.id == 1) {
            PacketUpdateFrequency.increment(handler);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
