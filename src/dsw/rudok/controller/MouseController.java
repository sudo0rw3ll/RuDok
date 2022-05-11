package dsw.rudok.controller;

import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.gui.tab.SlideView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter {

    private PresentationView presentationView;

    public MouseController(PresentationView presentationView){
        this.presentationView = presentationView;
    }

    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            Point position = e.getPoint();
            SlideView slideView = (SlideView) e.getSource();
            presentationView.mouseClicked(position, slideView);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            Point position = e.getPoint();
            SlideView slideView = (SlideView) e.getSource();
            presentationView.mouseReleased(position, slideView);
        }
    }
}
