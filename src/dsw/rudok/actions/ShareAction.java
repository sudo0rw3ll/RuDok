package dsw.rudok.actions;

import dsw.rudok.app.Main;
import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.gui.tree.view.RudokTree;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;
import dsw.rudok.view.dialogs.ShareDialog;

import java.awt.event.ActionEvent;
import java.util.List;

public class ShareAction extends RudokAction{

    public ShareAction(){
        putValue(NAME, "Share");
        putValue(SHORT_DESCRIPTION, "Shares Selected presentation with project");
        putValue(SMALL_ICON, loadIcon("images/shared_file.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ShareDialog shareDialog = new ShareDialog();
        shareDialog.setVisible(true);
    }
}
