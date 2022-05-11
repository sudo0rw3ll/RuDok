package dsw.rudok.command.concrete;

import dsw.rudok.actions.RemoveAction;
import dsw.rudok.app.MainFrame;
import dsw.rudok.command.AbstractCommand;
import dsw.rudok.gui.tree.model.RudokTreeNode;

import javax.swing.*;

public class RemoveNodeCommand extends AbstractCommand {

    private RudokTreeNode parent;
    private RudokTreeNode child;

    public RemoveNodeCommand(RudokTreeNode parent, RudokTreeNode child){
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        parent.remove(child);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }

    @Override
    public void undoCommand() {
        child.setParent(parent);
        parent.add(child);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }
}
