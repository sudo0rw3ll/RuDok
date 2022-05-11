package dsw.rudok.controller;

import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.gui.tab.SlideView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MoveSlotListener implements MouseMotionListener {

    private PresentationView p;

    public MoveSlotListener(PresentationView p){
        this.p = p;
    }

    @Override
    public void mouseDragged(MouseEvent e) {p.mouseDragged(e.getPoint(), (SlideView) e.getSource());}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
