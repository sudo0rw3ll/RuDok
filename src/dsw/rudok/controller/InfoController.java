package dsw.rudok.controller;

import dsw.rudok.view.dialogs.InfoDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoController implements ActionListener {

    private InfoDialog infoDialog;

    public InfoController(InfoDialog infoDialog){
        this.infoDialog = infoDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("OK")){
            infoDialog.setVisible(false);
        }
    }
}
