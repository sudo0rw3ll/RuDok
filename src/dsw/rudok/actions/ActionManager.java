package dsw.rudok.actions;

import java.awt.*;

public class ActionManager {

    private NewAction newAction;
    private InfoAction infoAction;
    private EditAuthorAction editAuthor;
    private EditThemeAction editTheme;
    private RemoveAction removeAction;
    private EditPresentationAction editPresentationAction;
    private PreviewAction previewAction;
    private NextSlideAction nextSlideAction;
    private PreviousSlideAction previousSlideAction;
    private AddSlotAction addSlotAction;
    private RemoveSlotAction removeSlotAction;
    private MoveSlotAction moveSlotAction;
    private ChooseColorAction chooseColorAction;
    private DashedStrokeAction dashedStrokeAction;
    private ClassicStrokeAction classicStrokeAction;
    private SelectSlotAction selectSlotAction;
    private RedoAction redoAction;
    private UndoAction undoAction;
    private TextAction textAction;
    private ImageAction imageAction;
    private EditSlotAction editSlotAction;
    private ShareAction shareAction;
    private SaveAction saveAction;
    private OpenAction openAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        newAction = new NewAction();
        infoAction = new InfoAction();
        editAuthor = new EditAuthorAction();
        editTheme = new EditThemeAction();
        removeAction = new RemoveAction();
        editPresentationAction = new EditPresentationAction();
        previewAction = new PreviewAction();
        nextSlideAction = new NextSlideAction();
        previousSlideAction = new PreviousSlideAction();
        addSlotAction = new AddSlotAction();
        removeSlotAction = new RemoveSlotAction();
        moveSlotAction = new MoveSlotAction();
        chooseColorAction = new ChooseColorAction();
        dashedStrokeAction = new DashedStrokeAction();
        classicStrokeAction = new ClassicStrokeAction();
        selectSlotAction = new SelectSlotAction();
        redoAction = new RedoAction();
        undoAction = new UndoAction();
        textAction = new TextAction();
        imageAction = new ImageAction();
        editSlotAction = new EditSlotAction();
        shareAction = new ShareAction();
        saveAction = new SaveAction();
        openAction = new OpenAction();
    }

    public NewAction getNewAction() {
        return newAction;
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public EditAuthorAction getEditAuthor(){return editAuthor;}

    public EditThemeAction getEditTheme(){return editTheme;}

    public RemoveAction getRemoveAction() {
        return removeAction;
    }

    public EditPresentationAction getEditPresentationAction() {return editPresentationAction;}

    public PreviewAction getPreviewAction(){return previewAction;}

    public NextSlideAction getNextSlideAction(){return nextSlideAction;}

    public PreviousSlideAction getPreviousSlideAction(){return previousSlideAction;}

    public AddSlotAction getAddSlotAction() {return addSlotAction;}

    public RemoveSlotAction getRemoveSlotAction() {return removeSlotAction;}

    public MoveSlotAction getMoveSlotAction() {return moveSlotAction;}

    public ChooseColorAction getChooseColorAction(){return chooseColorAction;}

    public DashedStrokeAction getDashedStrokeAction(){return dashedStrokeAction;}

    public ClassicStrokeAction getClassicStrokeAction(){return classicStrokeAction;}

    public SelectSlotAction getSelectSlotAction(){return selectSlotAction;}

    public RedoAction getRedoAction() {return redoAction;}

    public UndoAction getUndoAction() {return undoAction;}

    public TextAction getTextAction() {return textAction;}

    public ImageAction getImageAction(){return imageAction;}

    public EditSlotAction getEditSlotAction(){return editSlotAction;}

    public ShareAction getShareAction(){return shareAction;}

    public SaveAction getSaveAction(){return saveAction;}

    public OpenAction getOpenAction(){return openAction;}
}
