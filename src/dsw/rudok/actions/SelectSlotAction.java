package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.event.ActionEvent;

public class SelectSlotAction extends RudokAction{

    public SelectSlotAction(){
        putValue(NAME, "Select Slot");
        putValue(SHORT_DESCRIPTION, "Select presentation slot");
        putValue(SMALL_ICON, loadIcon("images/select_slot.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        presentationView.startSelectionState();
    }
}
