package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.graphics.concrete.AddSlotState;
import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.model.workspace.SlotType;

import java.awt.event.ActionEvent;

public class ImageAction extends RudokAction{

    public ImageAction(){
        putValue(NAME, "Image Action");
        putValue(SHORT_DESCRIPTION, "Creates image type of slot");
        putValue(SMALL_ICON, loadIcon("images/image.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if(presentationView.getSlotStateManager().getCurrentSlotState() instanceof AddSlotState){
            ((AddSlotState)presentationView.getSlotStateManager().getCurrentSlotState()).setSlotType(SlotType.IMAGE);
        }
    }
}
