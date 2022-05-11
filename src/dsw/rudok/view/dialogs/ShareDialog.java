package dsw.rudok.view.dialogs;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class ShareDialog extends JDialog {

    private JComboBox comboBox;

    private List<RudokTreeNode> projects;
    private JButton choose;

    public ShareDialog(){
        setSize(500,500);
        setTitle("Choose Project");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.add(new JLabel("Choose Project To Share Presentation"), BorderLayout.NORTH);
        projects = new ArrayList<RudokTreeNode>();

        comboBox = new JComboBox();
        fetchProjects();

        choose = new JButton("Share");
        choose.setSize(new Dimension(120,20));

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RuNode node = (RuNode) ((RudokTreeNode)MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();
                String selectedItem = comboBox.getSelectedItem().toString();
                RudokTreeNode parent = getSelected(selectedItem);

                if(node instanceof Presentation && parent != null){
                    RudokTreeNode child = new RudokTreeNode(node);
                    parent.add(child);
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
                }
            }
        });

        this.add(comboBox, BorderLayout.CENTER);
        this.add(choose, BorderLayout.SOUTH);

        this.pack();
        this.repaint();
        this.revalidate();
    }

    private void fetchProjects(){
        RudokTreeNode root = (RudokTreeNode) MainFrame.getInstance().getRudokTreeModel().getRoot();

        for(int i=0;i<root.getChildCount();i++){
            projects.add((RudokTreeNode) root.getChildAt(i));
            comboBox.addItem(((RudokTreeNode) root.getChildAt(i)).getRuNode().getName());
        }
    }

    private RudokTreeNode getSelected(String name){
        for(int i=0;i<projects.size();i++){
            if(name.equalsIgnoreCase(projects.get(i).getRuNode().getName())){
                return projects.get(i);
            }
        }
        return null;
    }

}
