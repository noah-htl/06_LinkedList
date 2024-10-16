module at.htlsaalfelden.linkedlist {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htlsaalfelden.linkedlist to javafx.fxml;
    exports at.htlsaalfelden.linkedlist;
}