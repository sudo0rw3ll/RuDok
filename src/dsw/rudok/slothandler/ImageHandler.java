package dsw.rudok.slothandler;

import dsw.rudok.gui.tab.SlotView;

import javax.swing.*;
import java.awt.*;

public class ImageHandler extends SlotHandler{

    private SlotView slotView;

    public ImageHandler(SlotView slotView) {
        super(slotView);
        this.slotView = slotView;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(new ImageIcon(slotView.getSlot().getContent()).getImage(), slotView.getX(), slotView.getY(),
                slotView.getWidth(),slotView.getHeight(),null);
    }

    @Override
    public void write(String content) {
        slotView.getSlot().setContent(content);
    }

    @Override
    public String read() {
        return null;
    }
}
