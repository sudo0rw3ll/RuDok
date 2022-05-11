package dsw.rudok.graphics.concrete;

import dsw.rudok.app.MainFrame;
import dsw.rudok.graphics.RudokSlotState;
import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.gui.tab.SlideView;
import dsw.rudok.model.workspace.Slot;

import java.awt.*;

public class SelectionSlotState extends RudokSlotState {

    private Color color;

    @Override
    public void mouseClicked(Point position, SlideView slideView) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
            for (int i = 0; i < slideView.getSlotViews().size(); i++) {
                if (slideView.getSlotViews().get(i).elementAt(position) && !slideView.getSlotViews().get(i).getSlot().isSelected()) {
                    presentationView.setSelectedSlot(slideView.getSlotViews().get(i));
                    slideView.getSlotViews().get(i).getSlot().setSelected(true);
                    Slot slot = slideView.getSlotViews().get(i).getSlot();
                    color = new Color(slot.getRed(), slot.getGreen(), slot.getBlue());
                    slideView.getSlotViews().get(i).getSlot().setRed(3);
                    slideView.getSlotViews().get(i).getSlot().setGreen(252);
                    slideView.getSlotViews().get(i).getSlot().setBlue(53);
                    slideView.repaint();
                    slideView.revalidate();
                    System.out.println(slideView.getSlotViews().get(i).getSlot().getSlotType());
                    break;
                }

                if(!slideView.getSlotViews().get(i).elementAt(position) && slideView.getSlotViews().get(i).getSlot().isSelected()){
                    presentationView.setSelectedSlot(null);
                    slideView.getSlotViews().get(i).getSlot().setSelected(false);
                    slideView.getSlotViews().get(i).getSlot().setRed(color.getRed());
                    slideView.getSlotViews().get(i).getSlot().setGreen(color.getGreen());
                    slideView.getSlotViews().get(i).getSlot().setBlue(color.getBlue());
                    slideView.repaint();
                    slideView.revalidate();
                    break;
                }
            }
    }
}
