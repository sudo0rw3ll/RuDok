package dsw.rudok.gui;

import dsw.rudok.app.MainFrame;

import javax.swing.*;

public class RudokToolbar extends JToolBar {

    public RudokToolbar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getRemoveAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getOpenAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getEditAuthor());
        add(MainFrame.getInstance().getActionManager().getEditTheme());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getShareAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getInfoAction());
    }
}
