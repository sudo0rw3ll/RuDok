package dsw.rudok.gui.tree.model;

import dsw.rudok.gui.tab.listener.RudokTabListener;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class RudokTreeNode extends DefaultMutableTreeNode {

    private RuNode ruNode;

    public RudokTreeNode(RuNode ruNode){
        super();
        this.ruNode = ruNode;
    }

    @Override
    public void add(MutableTreeNode newChild) {
        if(ruNode instanceof RuNodeComposite){
            RudokTreeNode node = (RudokTreeNode) newChild;
            ((RuNodeComposite) ruNode).addChild(node.getRuNode());
        }
    }

    @Override
    public void remove(MutableTreeNode aChild) {
        if(ruNode instanceof RuNodeComposite){
            RudokTreeNode node = (RudokTreeNode) aChild;
            ((RuNodeComposite) ruNode).removeChild(node.getRuNode());
        }
    }

    @Override
    public TreeNode getParent() {
        return new RudokTreeNode(ruNode.getParent());
    }

    @Override
    public TreeNode getChildAt(int index) {
        return new RudokTreeNode(((RuNodeComposite)ruNode).getChildren().get(index));
    }

    @Override
    public int getChildCount() {
        if(ruNode instanceof RuNodeComposite){
            return ((RuNodeComposite)ruNode).getChildren().size();
        }
        return 0;
    }

    @Override
    public int getIndex(TreeNode aChild) {
        return super.getIndex(aChild);
    }

    @Override
    public boolean getAllowsChildren(){
        if(ruNode instanceof RuNodeComposite){
            return true;
        }
        return false;
    }

    @Override
    public boolean isLeaf() {
        if(ruNode instanceof RuNodeComposite){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return ruNode.getName();
    }

    public RuNode getRuNode() {
        return ruNode;
    }
}
