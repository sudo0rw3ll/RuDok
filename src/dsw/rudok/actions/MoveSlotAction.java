package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.event.ActionEvent;

public class MoveSlotAction extends RudokAction{

    public MoveSlotAction(){
        putValue(NAME, "Move Slot");
        putValue(SHORT_DESCRIPTION, "Move slot to another action");
        putValue(SMALL_ICON, loadIcon("images/move_slot.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        presentationView.startMoveState();
    }
}
