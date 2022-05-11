package dsw.rudok.controller;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.view.dialogs.EditThemeDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditThemeController implements ActionListener {

    private EditThemeDialog dialog;

    public EditThemeController(EditThemeDialog dialog){
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        RuNode node = (RuNode) ((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();

        int r;

        if(!(node instanceof Presentation)){
            return;
        }

        if(e.getActionCommand().toLowerCase().equals("open")){
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView());

            FileFilter filter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());
            jFileChooser.setFileFilter(filter);

            r = jFileChooser.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
                ((Presentation) node).setThemePath(jFileChooser.getSelectedFile().getAbsolutePath());
                dialog.setVisible(false);
            }else{
                System.out.println("cancelled");
            }
        }
    }

}
