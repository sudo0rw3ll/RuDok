package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.command.concrete.RemoveNodeCommand;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.Slide;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.model.workspace.nodes.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveAction extends RudokAction{

    public RemoveAction(){
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove item");
        putValue(SMALL_ICON, loadIcon("images/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode node;

        if(MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent() == null){
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot delete if nothing is selected","Deletion Error",0);
            return;
        }else{
            node = ((RudokTreeNode)MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();
        }

        if(node instanceof Workspace){
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Deletion of workspace is not allowed","Workspace Deletion",0);
            return;
        }

        if(node instanceof Project){
            Project project = (Project) node;
            Workspace parent = (Workspace) project.getParent();
            parent.removeChild(project);
            MainFrame.getInstance().getCommandManager().addCommand(new RemoveNodeCommand(new RudokTreeNode(parent),new RudokTreeNode(project)));
        }

        if(node instanceof Presentation){
            Presentation presentation = (Presentation) node;
            Project parent = (Project) presentation.getParent();
            parent.removeChild(presentation);
            MainFrame.getInstance().getCommandManager().addCommand(new RemoveNodeCommand(new RudokTreeNode(parent),new RudokTreeNode(presentation)));
        }

        if(node instanceof Slide){
            Slide slide = (Slide) node;
            Presentation parent = (Presentation) slide.getParent();
            parent.removeChild(slide);
            MainFrame.getInstance().getCommandManager().addCommand(new RemoveNodeCommand(new RudokTreeNode(parent),new RudokTreeNode(slide)));
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }
}
