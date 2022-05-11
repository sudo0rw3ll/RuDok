package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.graphics.concrete.AddSlotState;
import dsw.rudok.gui.tab.PresentationView;

import java.awt.event.ActionEvent;

public class DashedStrokeAction extends RudokAction{

    public DashedStrokeAction(){
        putValue(NAME, "Dashed Stroke");
        putValue(SHORT_DESCRIPTION, "Creates Dashed Stroke for Slot");
        putValue(SMALL_ICON, loadIcon("images/dashed_stroke.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if(presentationView.getSlotStateManager().getCurrentSlotState() instanceof AddSlotState){
            AddSlotState addSlotState = (AddSlotState)presentationView.getSlotStateManager().getCurrentSlotState();
            try{
                int size = Integer.parseInt(presentationView.getTxtStroke().getText());
                if(size > 0){
                    addSlotState.setStrokeSize(size);
                }else{
                    MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Stroke size cannot be negative or zero","Stroke Size",0);
                    presentationView.getTxtStroke().setText("");
                    return;
                }
            }catch (NumberFormatException x){
                MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("You have to enter number","Number error",0);
            }
            addSlotState.setStrokeType(1);
        }else{
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Stroke size can be set only when add slot action is selected","Stroke error",0);
        }
        presentationView.getTxtStroke().setText("");
    }
}
