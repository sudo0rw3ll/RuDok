package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.event.ActionEvent;

public class PreviewAction extends RudokAction{

    public PreviewAction(){
        putValue(NAME, "Preview");
        putValue(SHORT_DESCRIPTION, "Starts presentation preview");
        putValue(SMALL_ICON, loadIcon("images/preview.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).startPreviewState();
        ((PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).startIdleState();
        ((PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).switchMode();
    }
}
