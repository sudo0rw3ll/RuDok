package dsw.rudok.observer;

public interface RudokPublisher {
    void addRudokSubscriber(RudokSubscriber rudokSubscriber);
    void removeRudokSubscriber(RudokSubscriber rudokSubscriber);
    void notifyRudokSubscriber(Object notification, String action);
}
