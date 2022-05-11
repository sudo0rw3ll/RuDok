package dsw.rudok.actions;


import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.event.ActionEvent;

public class AddSlotAction extends RudokAction{

    public AddSlotAction(){
        putValue(NAME, "Add Slot");
        putValue(SHORT_DESCRIPTION, "Draw new slot");
        putValue(SMALL_ICON, loadIcon("images/add_slot.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        presentationView.startAddState();
    }
}
