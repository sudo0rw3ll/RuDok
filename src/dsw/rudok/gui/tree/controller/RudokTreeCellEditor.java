package dsw.rudok.gui.tree.controller;

import dsw.rudok.app.MainFrame;
import dsw.rudok.command.concrete.RenameNodeCommand;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.model.workspace.nodes.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class RudokTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private JTextField edit = null;

    public RudokTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5){
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0){
        if(arg0 instanceof MouseEvent){
            if(((MouseEvent)arg0).getClickCount() == 3){
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent e){
        RuNode node = ((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();

        if(node instanceof Workspace){
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot rename Workspace","Workspace Error",0);
            MainFrame.getInstance().getRudokTree().clearSelection();
        }else{
            String name = e.getActionCommand();
            if(name.isEmpty()){
                MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot set empty name for rudok node","Empty name error",0);
                return;
            }
            String oldName = node.getName();
            node.setName(name);
            MainFrame.getInstance().getCommandManager().addCommand(new RenameNodeCommand(node,oldName,name));
            MainFrame.getInstance().getRudokTree().clearSelection(); //dodato kako bi se menjao naziv cvora na enter
        }
    }
}
