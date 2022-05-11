package dsw.rudok.observer;

public interface RudokSubscriber {
    void update(Object notification, String action);
}
