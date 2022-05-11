package dsw.rudok.state.concrete;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.state.RudokState;

import java.awt.*;

public class EditorState implements RudokState {
    @Override
    public void switchMode(PresentationView view) {
        System.out.println("EDIT");
        view.getCenterPanel().removeAll();
        view.getToolbar().removeAll();
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getPreviewAction());
        view.getToolbar().addSeparator();
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getAddSlotAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getRemoveSlotAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getMoveSlotAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getSelectSlotAction());
        view.getToolbar().addSeparator();
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getTextAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getImageAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getEditSlotAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getChooseColorAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getDashedStrokeAction());
        view.getToolbar().add(MainFrame.getInstance().getActionManager().getClassicStrokeAction());
        view.getToolbar().add(view.getLblStroke());
        view.getToolbar().add(view.getTxtStroke());
        view.getCenterPanel().add(view.getLeftScrollPane(), BorderLayout.WEST);
        view.getCenterPanel().add(view.getRightScrollPane(), BorderLayout.CENTER);

        view.repaint();
        view.revalidate();
    }
}
