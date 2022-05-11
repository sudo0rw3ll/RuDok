package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends RudokAction{

    public UndoAction(){
        putValue(NAME, "Undo Action");
        putValue(SHORT_DESCRIPTION, "Undo adding node to tree");
        putValue(SMALL_ICON, loadIcon("images/undo_action.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Undo");
        MainFrame.getInstance().getCommandManager().undoCommand();
    }
}
