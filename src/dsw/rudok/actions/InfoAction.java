package dsw.rudok.actions;

import dsw.rudok.view.dialogs.InfoDialog;
import dsw.rudok.app.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends RudokAction{

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(NAME,"Info");
        putValue(SHORT_DESCRIPTION,"Informacije o studentu");
        putValue(SMALL_ICON,loadIcon("images/info.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InfoDialog infoDialog = new InfoDialog(MainFrame.getInstance());
        infoDialog.setVisible(true);
    }
}
