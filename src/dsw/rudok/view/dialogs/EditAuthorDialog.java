package dsw.rudok.view.dialogs;

import dsw.rudok.controller.EditAuthorController;

import javax.swing.*;
import java.awt.*;

public class EditAuthorDialog extends JDialog {

    JTextField txtAuthorName;

    public EditAuthorDialog(JFrame parent) {
        super(parent, "Edit Presentation Author's name", false);
        setLayout(new BorderLayout());
        setSize(300, 300);
        setLocationRelativeTo(null);

        Dimension prefDim = new Dimension(120, 20);

        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblName = new JLabel("New Author name: ");
        txtAuthorName = new JTextField();
        txtAuthorName.setPreferredSize(prefDim);

        JPanel botPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(new EditAuthorController(this));
        botPan.add(buttonOK);

        panName.add(lblName);
        panName.add(txtAuthorName);

        panel.add(panName);

        this.add(panel, BorderLayout.CENTER);
        this.add(botPan, BorderLayout.SOUTH);

        pack();
    }

    public JTextField getTxtAuthorName() {
        return txtAuthorName;
    }
}
