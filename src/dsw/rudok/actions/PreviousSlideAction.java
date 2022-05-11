package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.*;
import java.awt.event.ActionEvent;

public class PreviousSlideAction extends RudokAction{

    public PreviousSlideAction(){
        putValue(NAME, "Previous Slide");
        putValue(SHORT_DESCRIPTION, "Previews previous slide of current presentation");
        putValue(SMALL_ICON, loadIcon("images/previous_slide.png"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        CardLayout cardLayout = (CardLayout) presentationView.getReviewPanel().getLayout();

        cardLayout.previous(presentationView.getReviewPanel());
    }
}
