package dsw.rudok.graphics.concrete;

import dsw.rudok.graphics.RudokSlotState;
import dsw.rudok.gui.tab.SlideView;
import dsw.rudok.model.workspace.Slot;
import dsw.rudok.model.workspace.SlotType;

import java.awt.*;

public class AddSlotState extends RudokSlotState {

    private Color color = new Color(157,213,213,179);
    private int strokeSize;
    private int strokeType;
    private SlotType slotType;

    @Override
    public void mouseClicked(Point position, SlideView slideView) {
        System.out.println("[State] Add Slot");
        Slot slot = new Slot(position.x, position.y, slideView.getWidth()/3, slideView.getHeight()/3);
        slot.setRed(color.getRed());
        slot.setGreen(color.getGreen());
        slot.setBlue(color.getBlue());
        slot.setStroke(strokeSize);
        slot.setStrokeType(strokeType);
        slot.setSlotType(slotType);
        slideView.getSlide().addSlot(slot);
    }

    public void setColor(Color color){this.color = color;}

    public void setStrokeSize(int strokeSize){this.strokeSize = strokeSize;}

    public void setStrokeType(int strokeType){this.strokeType = strokeType;}

    public void setSlotType(SlotType slotType){this.slotType = slotType;}
}
