package at.htlsaalfelden.linkedlist;

public class LinkedList<T> {
    private Node<T> root = null;
    private Observer observer;

    public void addItem(T item) {
        Node<T> add = new Node<>(item);

        if(root == null) {
            root = add;
        } else {
            Node<T> node = root.getLastNode();
            node.setNext(add);
        }

        sendEvent(new AddItemEvent<>(add));
    }

    public Node<T> getRoot() {
        return root;
    }

    public void deleteList() {
        delete(root);
        sendEvent(new DeleteItemEvent<>(root));
        root = null;
    }

    private void delete(Node<T> root) {
        if(root.next == null) {
            return;
        }

        delete(root.next);
        sendEvent(new DeleteItemEvent<>(root.next));
        root.next = null;
    }


    private void sendEvent(Event event) {
        if(this.observer != null) {
            this.observer.onValueChanged(event);
        }
    }

    public void addListener(Observer observer) {
        this.observer = observer;
    }

    public static class Node<T> {
        private final T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getLastNode() {
            return getLastNode(this);
        }

        private static <T> Node<T> getLastNode(Node<T> parent) {
            if(parent.next == null) {
                return parent;
            }

            return getLastNode(parent.next);
        }
    }

    public static class Event {

    }

    public static class AddItemEvent<T> extends Event {
        private final Node<T> item;

        public AddItemEvent(Node<T> item) {
            this.item = item;
        }

        public Node<T> getItem() {
            return item;
        }
    }

    public static class DeleteItemEvent<T> extends Event {
        private final Node<T> item;

        public DeleteItemEvent(Node<T> item) {
            this.item = item;
        }

        public Node<T> getItem() {
            return item;
        }
    }
}
