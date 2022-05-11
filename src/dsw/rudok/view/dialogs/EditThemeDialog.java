package dsw.rudok.view.dialogs;

import dsw.rudok.controller.EditThemeController;
import javax.swing.*;
import java.awt.*;

public class EditThemeDialog extends JDialog{

    public EditThemeDialog(JFrame parent){
        super(parent, "Edit Presentation Theme Dialog",false);
        setLayout(new BorderLayout());
        setSize(300,300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnOpen = new JButton("Open");

        btnOpen.addActionListener(new EditThemeController(this));
        panel.add(btnOpen);
        this.add(panel,BorderLayout.CENTER);

        pack();
    }
}
