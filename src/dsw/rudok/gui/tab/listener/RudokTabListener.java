package dsw.rudok.gui.tab.listener;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.nodes.RuNode;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RudokTabListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        RuNode node;
        if(MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent() == null){
            return;
        }

        node = (RuNode) ((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();

        if(e.getClickCount() == 2){
            if(node instanceof Project){
                Project project = (Project) node;
                MainFrame.getInstance().getProjectView().setProject(project);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
