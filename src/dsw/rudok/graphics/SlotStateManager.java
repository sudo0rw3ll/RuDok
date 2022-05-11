package dsw.rudok.graphics;

import dsw.rudok.graphics.concrete.*;

public class SlotStateManager {

    private RudokSlotState currentSlotState;
    private AddSlotState addSlotState;
    private RemoveSlotState removeSlotState;
    private MoveSlotState moveSlotState;
    private SelectionSlotState selectionSlotState;
    private IdleState idleState;

    public SlotStateManager(){
        initState();
    }

    private void initState(){
        this.addSlotState = new AddSlotState();
        this.removeSlotState = new RemoveSlotState();
        this.moveSlotState = new MoveSlotState();
        this.selectionSlotState = new SelectionSlotState();
        this.idleState = new IdleState();
        this.currentSlotState = moveSlotState;
    }

    public RudokSlotState getCurrentSlotState(){return this.currentSlotState;}

    public void setAddSlotState(){this.currentSlotState = addSlotState;}

    public void setRemoveSlotState(){this.currentSlotState = removeSlotState;}

    public void setMoveSlotState(){this.currentSlotState = moveSlotState;}

    public void setSelectionSlotState(){this.currentSlotState = selectionSlotState;}

    public void setIdleState(){this.currentSlotState = idleState;}
}
