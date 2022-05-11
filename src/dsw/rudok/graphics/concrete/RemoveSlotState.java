package dsw.rudok.graphics.concrete;

import dsw.rudok.graphics.RudokSlotState;
import dsw.rudok.gui.tab.SlideView;
import dsw.rudok.gui.tab.SlotView;

import java.awt.*;

public class RemoveSlotState extends RudokSlotState {

    @Override
    public void mouseClicked(Point position, SlideView slideView) {
        System.out.println("[State] Remove Slot");
        for(int i=0;i<slideView.getSlotViews().size();i++){
            if(slideView.getSlotViews().get(i).elementAt(position)){
                SlotView slotView = slideView.getSlotViews().get(i);
                slideView.getSlide().removeSlot(slotView.getSlot());
            }
        }
    }
}
