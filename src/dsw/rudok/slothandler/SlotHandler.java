package dsw.rudok.slothandler;

import dsw.rudok.gui.tab.SlotView;

import java.awt.*;

public abstract class SlotHandler {
    private SlotView slotView;

    public SlotHandler(SlotView slotView) {
        this.slotView = slotView;
    }

    public abstract void paint(Graphics g);
    public abstract void write(String content);
    public abstract String read();
}
