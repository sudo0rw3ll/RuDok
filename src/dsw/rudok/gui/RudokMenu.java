package dsw.rudok.gui;

import dsw.rudok.app.MainFrame;

import javax.swing.*;

public class RudokMenu extends JMenuBar {

    public RudokMenu(){
        JMenu mFile = new JMenu("File");
        mFile.add(MainFrame.getInstance().getActionManager().getNewAction());
        mFile.add(MainFrame.getInstance().getActionManager().getRemoveAction());
        mFile.addSeparator();
        mFile.add(MainFrame.getInstance().getActionManager().getShareAction());
        mFile.addSeparator();
        mFile.add(MainFrame.getInstance().getActionManager().getSaveAction());
        mFile.add(MainFrame.getInstance().getActionManager().getOpenAction());

        JMenu mEdit = new JMenu("Edit");
        JMenu ePresentation = new JMenu("Presentation");

        mEdit.add(ePresentation);
        ePresentation.add(MainFrame.getInstance().getActionManager().getEditAuthor());
        ePresentation.add(MainFrame.getInstance().getActionManager().getEditTheme());
        mEdit.addSeparator();
        mEdit.add(MainFrame.getInstance().getActionManager().getUndoAction());
        mEdit.add(MainFrame.getInstance().getActionManager().getRedoAction());

        JMenu mHelp = new JMenu("Help");
        mHelp.add(MainFrame.getInstance().getActionManager().getInfoAction());

        this.add(mFile);
        this.add(mEdit);
        this.add(mHelp);
    }

}
