package dsw.rudok.controller;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.view.dialogs.EditAuthorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAuthorController implements ActionListener {

    private EditAuthorDialog authorDialog;

    public EditAuthorController(EditAuthorDialog authorDialog){
        this.authorDialog = authorDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode node = (RuNode) ((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();
        if (node instanceof Presentation) {
            if (e.getActionCommand().toLowerCase().equals("ok")) {
                Presentation presentation = (Presentation) node;
                String authorName = authorDialog.getTxtAuthorName().getText();
                if (!authorName.isEmpty()) {
                    presentation.setAuthor(authorName);
                } else {
                    MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot set empty name for author","Author error",0);
                    return;
                }
            } else {
                return;
            }
            authorDialog.setVisible(false);
        }
    }
}
