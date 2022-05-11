package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.event.ActionEvent;

public class RemoveSlotAction extends RudokAction{

    public RemoveSlotAction(){
        putValue(NAME, "Erase Slot");
        putValue(SHORT_DESCRIPTION, "Remove selected slot from presentation");
        putValue(SMALL_ICON, loadIcon("images/remove_slot.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView)MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        presentationView.startRemoveState();
    }
}
