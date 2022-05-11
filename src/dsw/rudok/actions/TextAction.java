package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.graphics.concrete.AddSlotState;
import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.model.workspace.SlotType;

import java.awt.event.ActionEvent;

public class TextAction extends RudokAction{

    public TextAction(){
        putValue(NAME, "Create Text");
        putValue(SHORT_DESCRIPTION, "Creates a text type of slot");
        putValue(SMALL_ICON, loadIcon("images/text_type.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if(presentationView.getSlotStateManager().getCurrentSlotState() instanceof AddSlotState){
            ((AddSlotState)presentationView.getSlotStateManager().getCurrentSlotState()).setSlotType(SlotType.TEXT);
        }
    }
}
