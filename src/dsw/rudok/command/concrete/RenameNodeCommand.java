package dsw.rudok.command.concrete;

import dsw.rudok.app.MainFrame;
import dsw.rudok.command.AbstractCommand;
import dsw.rudok.model.workspace.nodes.RuNode;

import javax.swing.*;

public class RenameNodeCommand extends AbstractCommand {

    private RuNode node;
    private String name;
    private String oldName;

    public RenameNodeCommand(RuNode node, String oldName, String name){
        this.node = node;
        this.name = name;
        this.oldName = oldName;
    }

    @Override
    public void doCommand() {
        node.setName(name);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }

    @Override
    public void undoCommand() {
        node.setName(oldName);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }
}
