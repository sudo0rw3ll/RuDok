package dsw.rudok.state;

import dsw.rudok.state.concrete.EditorState;
import dsw.rudok.state.concrete.PreviewState;

public class StateManager {

    private RudokState currentState;
    private EditorState editorState;
    private PreviewState previewState;

    public StateManager(){
        initStates();
    }

    private void initStates(){
        editorState = new EditorState();
        previewState = new PreviewState();
        currentState = editorState;
    }

    public RudokState getCurrentState(){
        return this.currentState;
    }

    public void setEditorState(){
        this.currentState = editorState;
    }

    public void setPreviewState(){
        this.currentState = previewState;
    }
}
