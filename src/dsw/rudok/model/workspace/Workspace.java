package dsw.rudok.model.workspace;

import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;

import java.io.Serializable;

public class Workspace extends RuNodeComposite implements Serializable {

    public Workspace(String name){
        super(name, null);
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Project){
            Project project = (Project) child;
            if(!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child instanceof Project){
            Project project = (Project) child;
            if(this.getChildren().contains(project)){
                this.getChildren().remove(project);
            }
        }
    }
}
