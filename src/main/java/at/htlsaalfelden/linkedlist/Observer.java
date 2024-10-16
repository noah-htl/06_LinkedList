package at.htlsaalfelden.linkedlist;

public interface Observer {
    void onValueChanged(LinkedList.Event event);
}
