package dsw.rudok.gui.tab;

import dsw.rudok.app.MainFrame;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Slot;
import dsw.rudok.model.workspace.SlotType;
import dsw.rudok.observer.RudokSubscriber;
import dsw.rudok.slothandler.ImageHandler;
import dsw.rudok.slothandler.SlotHandler;
import dsw.rudok.slothandler.TextHandler;
import dsw.rudok.state.concrete.EditorState;
import dsw.rudok.state.concrete.PreviewState;

import java.awt.*;

public class SlotView implements RudokSubscriber {

    private Slot slot;
    private Shape shape;

    private int x;
    private int y;
    private int width;
    private int height;

    private SlotHandler slotHandler;

    public SlotView(Slot slot){
        this.slot = slot;
        shape = new Rectangle(slot.getX(), slot.getY(), slot.getWidth(), slot.getHeight());
        this.x = slot.getX();
        this.y = slot.getY();
        this.width = slot.getWidth();
        this.height = slot.getHeight();
        this.slot.addRudokSubscriber(this);

        if(slot.getSlotType() == SlotType.TEXT)
            slotHandler = new TextHandler(this);
        else
            slotHandler = new ImageHandler(this);
    }

    public void paint(Graphics2D g){
        if(((PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).getCurrentState() instanceof EditorState) {
            g.setPaint(new Color(slot.getRed(), slot.getGreen(), slot.getBlue(), 179));
            g.fill(new Rectangle(x, y, width, height));
            if (slot.getStrokeType() == 1) {
                g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                        0, new float[]{slot.getStroke()}, 0));
            } else {
                g.setStroke(new BasicStroke((float) slot.getStroke()));
            }
            g.drawRect(x, y, width, height);
        }

        if(((PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).getCurrentState() instanceof PreviewState){
            this.slotHandler.paint(g);
        }
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean elementAt(Point position) {return shape.contains(position);}

    public SlotHandler getSlotHandler() {
        return slotHandler;
    }

    @Override
    public void update(Object notification, String action) {
        if(notification != null && action.equalsIgnoreCase("changePosition")) {
            this.x = ((Slot)notification).getX();
            this.y = ((Slot)notification).getY();
        }
    }
}
