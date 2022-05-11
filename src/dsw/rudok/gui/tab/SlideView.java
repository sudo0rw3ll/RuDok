package dsw.rudok.gui.tab;

import dsw.rudok.controller.MouseController;
import dsw.rudok.controller.MoveSlotListener;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Slide;
import dsw.rudok.model.workspace.Slot;
import dsw.rudok.observer.RudokSubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlideView extends JPanel implements RudokSubscriber {

    private Slide slide;
    private JLabel countLabel;
    private List<SlotView> slotViews;

    public SlideView(Slide slide, Dimension dimension, PresentationView presentationView){
        this.slide = slide;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600,400));
        countLabel = new JLabel();
        countLabel.setText(String.valueOf(slide.getCount()));
        this.add(countLabel, BorderLayout.SOUTH);

        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE,10));

        if(dimension.getHeight() == 400 && dimension.getWidth() == 600) {
            this.addMouseListener(new MouseController(presentationView));
            this.addMouseMotionListener(new MoveSlotListener(presentationView));
        }
        this.slotViews = new ArrayList<SlotView>();

        slide.addRudokSubscriber(this);
        slide.getParent().addRudokSubscriber(this);
    }

    @Override
    public void update(Object notification, String action) {
        if(action.equalsIgnoreCase("changedTheme")){
            this.repaint();
            this.revalidate();
        }

        if(action.equalsIgnoreCase("newSlotAdded")){
            SlotView slotView = new SlotView((Slot)(notification));
            slotViews.add(slotView);
            if(this.getHeight() == 400 / 3 && this.getWidth() == 600 / 3){
                slotView.setWidth(slotView.getWidth() / 3);
                slotView.setHeight(slotView.getHeight() / 3);
                slotView.setX(slotView.getX() / 3);
                slotView.setY(slotView.getY() / 3);
            }
            this.repaint();
            this.revalidate();
        }

        if(action.equalsIgnoreCase("slotRemoved")){
            Slot slot = (Slot)(notification);
            for(SlotView slotView : slotViews){
                if(slotView.getSlot().equals(slot)){
                    slotViews.remove(slotView);
                    break;
                }
            }
            this.repaint();
            this.revalidate();
        }

        if(action.equalsIgnoreCase("updateSlot")){
            Slot slot = (Slot)(notification);
            for(SlotView slotView : slotViews){
                if(slotView.getSlot().equals(slot)){
                    slotView.setX(slot.getX());
                    slotView.setY(slot.getY());
                    break;
                }
            }
            this.repaint();
            this.revalidate();
        }
    }

    public Slide getSlide(){
        return this.slide;
    }

    public void paintComponent(Graphics g) {
        Image img = new ImageIcon(((Presentation)slide.getParent()).getThemePath()).getImage();

        g.drawImage(img, 0,0,(int)(this.getSize().getWidth()),
                (int)(this.getSize().getHeight()),null);

        for(SlotView slotView : slotViews){
            Graphics2D graphics = (Graphics2D) g;
            slotView.paint(graphics);
        }
    }

    public List<SlotView> getSlotViews(){
        return this.slotViews;
    }
}
