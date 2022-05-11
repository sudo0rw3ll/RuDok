package dsw.rudok.gui.tree.controller;

import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.Slide;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.model.workspace.nodes.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class RudokTreeCellRenderer extends DefaultTreeCellRenderer {

    public RudokTreeCellRenderer(){

    }

    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus
    ){
        super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);

        if(value instanceof RudokTreeNode) {
            RuNode rudokN = ((RudokTreeNode) value).getRuNode();
            if (rudokN instanceof Workspace) {
                URL image = getClass().getResource("icons/workspace.png");
                Icon icon = null;
                if (image != null) {
                    icon = new ImageIcon(image);
                }
                setIcon(icon);
            }

            if(rudokN instanceof Project){
                URL image = getClass().getResource("icons/proj.png");
                Icon icon = null;
                if(image != null){
                    icon = new ImageIcon(image);
                }
                setIcon(icon);
            }

            if(rudokN instanceof Presentation){
                URL image = getClass().getResource("icons/pres.png");
                Icon icon = null;
                if(image != null){
                    icon = new ImageIcon(image);
                }
                setIcon(icon);
            }

            if(rudokN instanceof Slide){
                URL image = getClass().getResource("icons/sl.png");
                Icon icon = null;
                if(image != null){
                    icon = new ImageIcon(image);
                }
                setIcon(icon);
            }
        }

        return this;
    }
}
