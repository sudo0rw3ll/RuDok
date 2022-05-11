package dsw.rudok.rudokerrors;

import dsw.rudok.observer.RudokPublisher;
import dsw.rudok.observer.RudokSubscriber;

import java.util.ArrayList;
import java.util.List;

public class RudokErrorFactory implements RudokPublisher {

    private List<RudokSubscriber> errorSubscribers;

    public RudokErrorFactory(){
        this.errorSubscribers = new ArrayList<>();
    }

    public void makeErrorMessage(String content, String title, int type){
        RudokGreska error = new RudokGreska(content, title, type);
        notifyRudokSubscriber(error, "rudokError");
    }

    @Override
    public void addRudokSubscriber(RudokSubscriber rudokSubscriber) {
        if(rudokSubscriber == null) {
            return;
        }

        if(this.errorSubscribers == null){
            this.errorSubscribers = new ArrayList<>();
        }

        if(this.errorSubscribers.contains(rudokSubscriber)){
            return;
        }

        this.errorSubscribers.add(rudokSubscriber);
    }

    @Override
    public void removeRudokSubscriber(RudokSubscriber rudokSubscriber) {
        if(rudokSubscriber == null || this.errorSubscribers == null || this.errorSubscribers.isEmpty()){
            return;
        }

        this.errorSubscribers.remove(rudokSubscriber);
    }

    @Override
    public void notifyRudokSubscriber(Object notification, String action) {
        if(notification == null || this.errorSubscribers == null || this.errorSubscribers.isEmpty()){
            return;
        }

        for(int i=0;i<errorSubscribers.size();i++){
            errorSubscribers.get(i).update(notification, action);
        }
    }
}
