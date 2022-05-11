package dsw.rudok.gui.tree.view;

import dsw.rudok.gui.tab.listener.RudokTabListener;
import dsw.rudok.gui.tree.controller.RudokTreeCellEditor;
import dsw.rudok.gui.tree.controller.RudokTreeCellRenderer;
import dsw.rudok.gui.tree.controller.RudokTreeSelectionListener;
import dsw.rudok.gui.tree.model.RudokTreeModel;

import javax.swing.*;

public class RudokTree extends JTree {

    public RudokTree(RudokTreeModel model){
        setModel(model);
        addMouseListener(new RudokTabListener());
        RudokTreeCellRenderer cellRenderer = new RudokTreeCellRenderer();
        addTreeSelectionListener(new RudokTreeSelectionListener());
        setCellEditor(new RudokTreeCellEditor(this,cellRenderer));
        setCellRenderer(cellRenderer);
        setEditable(true);
    }
}
