package dsw.rudok.gui.tab;

import dsw.rudok.app.MainFrame;
import dsw.rudok.graphics.SlotStateManager;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.Slide;
import dsw.rudok.observer.RudokSubscriber;
import dsw.rudok.state.RudokState;
import dsw.rudok.state.StateManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class PresentationView extends JPanel implements RudokSubscriber {

    private Presentation presentation;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel reviewPanel;
    private JScrollPane leftScrollPane;
    private JScrollPane rightScrollPane;
    private JLabel authorName;
    private JToolBar toolbar;
    private JTextField txtStroke;
    private JLabel lblStroke;

    private List<SlideView> slideViews;
    private List<SlideView> slideViewLeft;
    private List<SlideView> reviewPaneSlides;

    private StateManager stateManager; //Presentation view je mediator
    private SlotStateManager slotStateManager;

    private SlotView selectedSlot = null;

    public PresentationView(Presentation presentation){
        this.slideViews = new ArrayList<SlideView>();
        this.slideViewLeft = new ArrayList<SlideView>();
        this.reviewPaneSlides = new ArrayList<SlideView>();

        this.presentation = presentation;
        this.presentation.addRudokSubscriber(this);

        this.toolbar = new JToolBar();
        this.toolbar.setFloatable(false);
        this.toolbar.add(MainFrame.getInstance().getActionManager().getPreviewAction());
        this.toolbar.addSeparator();
        this.toolbar.add(MainFrame.getInstance().getActionManager().getAddSlotAction());
        this.toolbar.add(MainFrame.getInstance().getActionManager().getRemoveSlotAction());
        this.toolbar.add(MainFrame.getInstance().getActionManager().getMoveSlotAction());
        this.toolbar.add(MainFrame.getInstance().getActionManager().getSelectSlotAction());
        this.toolbar.addSeparator();
        this.toolbar.add(MainFrame.getInstance().getActionManager().getTextAction());
        this.toolbar.add(MainFrame.getInstance().getActionManager().getImageAction());
        this.toolbar.add(MainFrame.getInstance().getActionManager().getEditSlotAction());
        this.toolbar.add(MainFrame.getInstance().getActionManager().getChooseColorAction());
        this.toolbar.addSeparator();
        this.toolbar.add(MainFrame.getInstance().getActionManager().getDashedStrokeAction());
        this.toolbar.add(MainFrame.getInstance().getActionManager().getClassicStrokeAction());
        this.toolbar.addSeparator();
        lblStroke = new JLabel("Stroke size: ");
        this.toolbar.add(lblStroke);
        txtStroke = new JTextField();
        txtStroke.setMaximumSize(new Dimension(100,20));
        this.toolbar.add(txtStroke);

        this.setLayout(new BorderLayout());

        this.leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftScrollPane = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftScrollPane.setPreferredSize(new Dimension(300, 100));

        this.rightPanel = new JPanel();
        this.rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightScrollPane = new JScrollPane(rightPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.add(leftScrollPane, BorderLayout.WEST);
        this.centerPanel.add(rightScrollPane, BorderLayout.CENTER);

        this.reviewPanel = new JPanel(new CardLayout());

        checkAddition();
        stateManager = new StateManager();
        slotStateManager = new SlotStateManager();

        authorName = new JLabel(presentation.getAuthor());

        this.add(toolbar, BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(authorName, BorderLayout.SOUTH);
    }

    public void startAddState(){this.slotStateManager.setAddSlotState();}

    public void startRemoveState(){this.slotStateManager.setRemoveSlotState();}

    public void startMoveState(){this.slotStateManager.setMoveSlotState();}

    public void startSelectionState(){this.slotStateManager.setSelectionSlotState();}

    public void startIdleState(){this.slotStateManager.setIdleState();}

    //Mapirane metode
    public void mouseClicked(Point position, SlideView slideView){
        this.slotStateManager.getCurrentSlotState().mouseClicked(position, slideView);
    }

    public void mouseReleased(Point position, SlideView slideView){
        this.slotStateManager.getCurrentSlotState().mouseReleased(position, slideView);
    }

    public void mouseDragged(Point position, SlideView slideView){
        this.slotStateManager.getCurrentSlotState().mouseDragged(position,slideView);
    }

    public void startEditorState(){
        this.stateManager.setEditorState();
    }

    public void startPreviewState(){
        this.stateManager.setPreviewState();
    }

    public void switchMode(){
        this.stateManager.getCurrentState().switchMode(this);
    }

    public void checkAddition(){

        for(int i=0;i<presentation.getChildren().size();i++){
            Slide slide = (Slide) presentation.getChildren().get(i);

            boolean isOk = false;

            for(SlideView tempSlideView : slideViews){
                if(slide.equals(tempSlideView.getSlide())){
                    isOk = true;
                }
            }

            if(!isOk){
                SlideView newSlideView = new SlideView(slide, new Dimension(600, 400), this);
                rightPanel.add(newSlideView);
                slideViews.add(newSlideView);

                SlideView newSlideView1 = new SlideView(slide, new Dimension(600 / 3, 400 / 3), this);
                leftPanel.add(newSlideView1);
                slideViewLeft.add(newSlideView1);

                SlideView newSlideView2 = new SlideView(slide, new Dimension(600, 400), this);
                reviewPanel.add(newSlideView2);
                reviewPaneSlides.add(newSlideView2);
                ((Project)presentation.getParent()).setChanged(true);
            }
        }
        this.repaint();
        this.revalidate();
    }

    private void checkRemoval(){
        for(int i=0;i<slideViews.size();i++){
            SlideView slideView = slideViews.get(i);

            boolean deletion = false;

            for(int j=0;j<presentation.getChildren().size();j++){
                Slide slide = (Slide) presentation.getChildren().get(j);
                if(slideView.getSlide().equals(slide)){
                    deletion = true;
                }
            }

            if(!deletion){
                slideViews.remove(slideView);
                reviewPaneSlides.remove(i);
                slideViewLeft.remove(i);
                rightPanel.remove(slideView);
                leftPanel.remove(i);
                reviewPanel.remove(i);
                ((Project)presentation.getParent()).setChanged(true);
            }
        }

        this.repaint();
        this.revalidate();
    }

    @Override
    public void update(Object notification, String action) {
        if(notification instanceof Slide && action.equalsIgnoreCase("addAction")){
            checkAddition();
        }

        if(notification instanceof Slide && action.equalsIgnoreCase("removeAction")){
            checkRemoval();
        }

        if(action.equalsIgnoreCase("changedAuthor")){
            authorName.setText(((Presentation)notification).getAuthor());
        }
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public JPanel getReviewPanel() {
        return reviewPanel;
    }

    public JScrollPane getLeftScrollPane() {
        return leftScrollPane;
    }

    public JScrollPane getRightScrollPane() {
        return rightScrollPane;
    }

    public JToolBar getToolbar() {
        return toolbar;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public SlotStateManager getSlotStateManager() {
        return this.slotStateManager;
    }

    public RudokState getCurrentState(){return this.stateManager.getCurrentState();}

    public JTextField getTxtStroke() {
        return txtStroke;
    }

    public JLabel getLblStroke() {
        return lblStroke;
    }

    public SlotView getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(SlotView selectedSlot) {
        this.selectedSlot = selectedSlot;
    }
}