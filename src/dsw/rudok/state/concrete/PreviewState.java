package dsw.rudok.state.concrete;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.state.RudokState;

import java.awt.*;

public class PreviewState implements RudokState {
    @Override
    public void switchMode(PresentationView view) {
        System.out.println("PREVIEW");
        view.getCenterPanel().removeAll();
        view.getToolbar().removeAll();

        view.getToolbar().add(MainFrame.getInstance().getActionManager().getEditPresentationAction());
        view.getToolbar().addSeparator();
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getPreviousSlideAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getNextSlideAction());

        view.getCenterPanel().add(view.getReviewPanel(), BorderLayout.CENTER);

        view.repaint();
        view.revalidate();
    }
}
