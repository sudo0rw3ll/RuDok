package dsw.rudok.model.workspace.nodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode implements Serializable {

    List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        this.children = new ArrayList<RuNode>();
    }

    public RuNodeComposite(String name, RuNode parent, List<RuNode> children){
        super(name, parent);
        this.children = children;
    }

    public void addChild(RuNode child){
        if(!children.contains(child)) {
            children.add(child);
            notifyRudokSubscriber(child, "addAction");
        }
    }

    public void removeChild(RuNode child){
        if(children.contains(child)){
            children.remove(child);
            notifyRudokSubscriber(child, "removeAction");
        }
    }

    public List<RuNode> getChildren(){
        return children;
    }
}
