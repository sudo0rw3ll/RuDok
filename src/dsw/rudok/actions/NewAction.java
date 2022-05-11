package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.command.concrete.AddNodeCommand;
import dsw.rudok.factory.AbstractNodeFactory;
import dsw.rudok.factory.FactoryCreator;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Slide;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends RudokAction{

    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(NAME,"New");
        putValue(SHORT_DESCRIPTION,"New");
        putValue(SMALL_ICON,loadIcon("images/new.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode node;

        if(MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent() == null){
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot add new rudok node if parent is not selected","RudokNode Addition Error",0);
            return;
        }

        node = ((RudokTreeNode)MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();

        if(node == null)
            return;

        if(node instanceof Slide){
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot add rudok node into Slide", "Slide Error",0);
        }

        int num = 0;

        if(node instanceof RuNodeComposite){
            num = ((RuNodeComposite)node).getChildren().size()+1;
        }
        MainFrame.getInstance().getRudokTree().expandPath(MainFrame.getInstance().getRudokTree().getSelectionPath());

        AbstractNodeFactory factory = FactoryCreator.createFactory(node);

        if(factory != null){
            RuNode nodeChild = factory.getRuNode(node, num);
            RudokTreeNode child = new RudokTreeNode(nodeChild);
            RudokTreeNode parent = (RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent();
//            child.setParent(parent);
//            parent.add(child);
            MainFrame.getInstance().getCommandManager().addCommand(new AddNodeCommand(parent, child));
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }
}
