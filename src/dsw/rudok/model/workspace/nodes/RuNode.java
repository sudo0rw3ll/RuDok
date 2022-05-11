package dsw.rudok.model.workspace.nodes;

import dsw.rudok.observer.RudokPublisher;
import dsw.rudok.observer.RudokSubscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements RudokPublisher, Serializable {

    private String name;
    private RuNode parent;

    private transient List<RudokSubscriber> rudokSubscribers;

    public RuNode(String name, RuNode parent){
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof RuNode){
            RuNode node = (RuNode) obj;
            return this.getName().equals(node.getName());
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyRudokSubscriber(this, "name");
    }

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    @Override
    public void addRudokSubscriber(RudokSubscriber rudokSubscriber) {
        if(rudokSubscriber == null){
            return;
        }

        if(this.rudokSubscribers == null){
            this.rudokSubscribers = new ArrayList<>();
        }

        if(this.rudokSubscribers.contains(rudokSubscriber)){
            return;
        }

        this.rudokSubscribers.add(rudokSubscriber);
    }

    @Override
    public void removeRudokSubscriber(RudokSubscriber rudokSubscriber) {
        if(rudokSubscriber == null || this.rudokSubscribers == null || !this.rudokSubscribers.contains(rudokSubscriber)){
            return;
        }

        this.rudokSubscribers.remove(rudokSubscriber);
    }

    @Override
    public void notifyRudokSubscriber(Object notification, String action) {
        if(notification == null || this.rudokSubscribers == null || this.rudokSubscribers.isEmpty()){
            return;
        }

        for(int i=0;i<rudokSubscribers.size();i++){
            rudokSubscribers.get(i).update(notification, action);
        }
    }
}
