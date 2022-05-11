package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.view.dialogs.EditThemeDialog;

import java.awt.event.ActionEvent;

public class EditThemeAction extends RudokAction{

    public EditThemeAction(){
        putValue(NAME,"Edit Theme");
        putValue(SHORT_DESCRIPTION, "Change presentation theme");
        putValue(SMALL_ICON,loadIcon("images/tema.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode node;

        if(MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent() == null){
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot upload theme, no presentation selected","Theme Error",0);
            return;
        }else{
            node = ((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();
        }

        if(node instanceof Presentation){
            EditThemeDialog editThemeDialog = new EditThemeDialog(MainFrame.getInstance());
            editThemeDialog.setVisible(true);
        }else{
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot upload theme, no presentation selected","Theme Error",0);
        }
    }
}
