package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.model.workspace.SlotType;
import dsw.rudok.view.frames.ImageEditor;
import dsw.rudok.view.frames.TextEditor;

import java.awt.event.ActionEvent;

public class EditSlotAction extends RudokAction{

    public EditSlotAction(){
        putValue(NAME, "Edit Slot");
        putValue(SHORT_DESCRIPTION, "Edit Slot Content");
        putValue(SMALL_ICON, loadIcon("images/edit_slot.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

        if(presentationView.getSelectedSlot().getSlot().getSlotType() == SlotType.TEXT){
            TextEditor textEditor = new TextEditor();
            MainFrame.getInstance().setTextEditor(textEditor);
            presentationView.getSelectedSlot().getSlotHandler().read();
            textEditor.setVisible(true);
        }

        if(presentationView.getSelectedSlot().getSlot().getSlotType() == SlotType.IMAGE){
            ImageEditor imageEditor = new ImageEditor();
            imageEditor.setVisible(true);
            System.out.println(presentationView.getSelectedSlot().getSlot().getContent());
        }
    }
}
