package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.graphics.concrete.AddSlotState;
import dsw.rudok.gui.tab.PresentationView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChooseColorAction extends RudokAction{

    public ChooseColorAction(){
        putValue(NAME, "Choose Slot Color");
        putValue(SHORT_DESCRIPTION, "Opens color picker for slot");
        putValue(SMALL_ICON, loadIcon("images/color_picker.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if(presentationView.getSlotStateManager().getCurrentSlotState() instanceof AddSlotState){
            AddSlotState addSlotState = (AddSlotState) presentationView.getSlotStateManager().getCurrentSlotState();
            Color color = JColorChooser.showDialog(null, "Slot Color Chooser",null);
            if(color != null){
                addSlotState.setColor(color);
            }else{
                MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("You must choose color","Color Selection",0);
            }
        }else{
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Color can be set only if add state is active","Color Selection",0);
        }
    }
}
