package dsw.rudok.gui.tree.controller;

import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.nodes.RuNode;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class RudokTreeSelectionListener implements TreeSelectionListener {

    public RudokTreeSelectionListener(){

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        RudokTreeNode item = (RudokTreeNode) path.getLastPathComponent();
        System.out.println(item.getRuNode().getName()); //Debug path-a
        System.out.println(path.toString());

        RuNode node = item.getRuNode();

        if(node instanceof Presentation){
            System.out.println(((Presentation) node).getAuthor());
            System.out.println(((Presentation)node).getThemePath());
        }
    }
}
