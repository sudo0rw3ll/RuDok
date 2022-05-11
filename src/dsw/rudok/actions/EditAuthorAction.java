package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.view.dialogs.EditAuthorDialog;

import java.awt.event.ActionEvent;

public class EditAuthorAction extends RudokAction{

    public EditAuthorAction(){
        putValue(NAME,"Edit author");
        putValue(SHORT_DESCRIPTION,"Change author's name");
        putValue(SMALL_ICON,loadIcon("images/autor.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode node = null;

        if(MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent() == null){
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Author can only be set for presentation","Author Error",0);
            return;
        }else{
            node = (RuNode) ((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();
        }

        if(node instanceof Presentation){
            EditAuthorDialog editAuthorDialog = new EditAuthorDialog(MainFrame.getInstance());
            editAuthorDialog.setVisible(true);
        }else{
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Author can only be set for presentation","Author Error",0);
        }
    }
}
