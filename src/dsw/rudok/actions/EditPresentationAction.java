package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.event.ActionEvent;

public class EditPresentationAction extends RudokAction{

    public EditPresentationAction(){
        putValue(NAME, "Edit Presentation");
        putValue(SHORT_DESCRIPTION, "Starts presentation editing mode");
        putValue(SMALL_ICON, loadIcon("images/edit_presentation.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).startEditorState();
        ((PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).switchMode();
    }
}
