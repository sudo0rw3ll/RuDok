package dsw.rudok.model.workspace;

import dsw.rudok.model.workspace.nodes.RuNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Slide extends RuNode implements Serializable {

    private int count;
    private List<Slot> slots;

    public Slide(String name, RuNode parent) {
        super(name, parent);
        this.slots = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Slide ";
    }

    public void setTheme() {
        /*Metoda koja obavestava slide view da je
        * doslo do promene teme u prezentaciji */
        notifyRudokSubscriber(this, "changedTheme");
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public void addSlot(Slot slot){
        if(!slots.contains(slot)){
            slots.add(slot);
            notifyRudokSubscriber(slot,"newSlotAdded");
        }
    }

    public void removeSlot(Slot slot){
        if(slots.contains(slot)){
            slots.remove(slot);
            notifyRudokSubscriber(slot,"slotRemoved");
        }
    }

    public void updateSlot(Slot slot){
        notifyRudokSubscriber(slot, "updateSlot");
    }
}
