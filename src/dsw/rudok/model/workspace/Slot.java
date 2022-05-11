package dsw.rudok.model.workspace;

import dsw.rudok.observer.RudokPublisher;
import dsw.rudok.observer.RudokSubscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Slot implements RudokPublisher, Serializable {

    private int x;
    private int y;
    private int width;
    private int height;

    private int red;
    private int green;
    private int blue;

    private int stroke;
    private int strokeType;

    private String content = "";
    private SlotType slotType;

    private boolean isSelected;

    transient private List<RudokSubscriber> slotSubscribers;

    public Slot(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isSelected = false;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {this.green = green;}

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public int getStrokeType() {
        return strokeType;
    }

    public void setStrokeType(int strokeType) {
        this.strokeType = strokeType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        notifyRudokSubscriber(this,"changePosition");
    }

    @Override
    public void addRudokSubscriber(RudokSubscriber rudokSubscriber) {
        if(rudokSubscriber == null){
            return;
        }

        if(this.slotSubscribers == null){
            this.slotSubscribers = new ArrayList<>();
        }

        if(this.slotSubscribers.contains(rudokSubscriber)){
            return;
        }
        slotSubscribers.add(rudokSubscriber);
    }

    @Override
    public void removeRudokSubscriber(RudokSubscriber rudokSubscriber) {
        if(rudokSubscriber == null || this.slotSubscribers == null || !this.slotSubscribers.contains(rudokSubscriber)){
            return;
        }

        this.slotSubscribers.remove(rudokSubscriber);
    }

    @Override
    public void notifyRudokSubscriber(Object notification, String action) {
        if(notification == null || this.slotSubscribers == null || this.slotSubscribers.isEmpty()){
            return;
        }

        for(int i=0;i<slotSubscribers.size();i++){
            slotSubscribers.get(i).update(notification, action);
        }
    }
}
