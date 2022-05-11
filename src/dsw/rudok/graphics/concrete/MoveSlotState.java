package dsw.rudok.graphics.concrete;

import dsw.rudok.graphics.RudokSlotState;
import dsw.rudok.gui.tab.SlideView;
import dsw.rudok.model.workspace.Slot;

import java.awt.*;

public class MoveSlotState extends RudokSlotState {

    private int startX;
    private int startY;
    private Slot selectedSlot;

    @Override
    public void mouseClicked(Point position, SlideView slideView) {
        for(int i=0;i<slideView.getSlotViews().size();i++){
            if(slideView.getSlotViews().get(i).elementAt(position) && !slideView.getSlotViews().get(i).getSlot().isSelected()){
                slideView.getSlotViews().get(i).getSlot().setSelected(true);
                startX = position.x;
                startY = position.y;
                selectedSlot = slideView.getSlotViews().get(i).getSlot();
            }
        }
    }

    @Override
    public void mouseDragged(Point position, SlideView slideView) {
                int pomX = position.x - startX;
                int pomY = position.y - startY;
                startX = position.x;
                startY = position.y;
                selectedSlot.setPosition(selectedSlot.getX() + pomX, selectedSlot.getY() + pomY);
                slideView.getSlide().updateSlot(selectedSlot);
    }

    @Override
    public void mouseReleased(Point position, SlideView slideView) {
        for(int i=0;i<slideView.getSlotViews().size();i++){
            if(slideView.getSlotViews().get(i).elementAt(position) && slideView.getSlotViews().get(i).getSlot().isSelected()){
                slideView.getSlotViews().get(i).getSlot().setSelected(false);
            }
        }
    }
}
