package dsw.rudok.command.concrete;

import dsw.rudok.app.MainFrame;
import dsw.rudok.command.AbstractCommand;
import dsw.rudok.gui.tree.model.RudokTreeNode;

import javax.swing.*;

public class AddNodeCommand extends AbstractCommand {

    private RudokTreeNode parent;
    private RudokTreeNode child;

    public AddNodeCommand(RudokTreeNode parent, RudokTreeNode child){
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        child.setParent(parent);
        parent.add(child);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }

    @Override
    public void undoCommand() {
        parent.remove(child);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }
}
