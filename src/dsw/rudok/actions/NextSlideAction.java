package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.*;
import java.awt.event.ActionEvent;

public class NextSlideAction extends RudokAction{

    public NextSlideAction(){
        putValue(NAME, "Next Slide");
        putValue(SHORT_DESCRIPTION, "Previews next slide of presentation");
        putValue(SMALL_ICON, loadIcon("images/next_slide.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        CardLayout preview = (CardLayout) presentationView.getReviewPanel().getLayout();
        preview.next(presentationView.getReviewPanel());

        presentationView.repaint();
        presentationView.revalidate();
    }
}
