package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;

import java.awt.event.ActionEvent;

public class RedoAction extends RudokAction{

    public RedoAction(){
        putValue(NAME, "Redo Action");
        putValue(SHORT_DESCRIPTION, "Redo adding node to tree");
        putValue(SMALL_ICON, loadIcon("images/redo_action.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().doCommand();
    }
}
