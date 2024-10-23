package at.htlsaalfelden.linkedlist;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public TextField textInput;

    public static Scene scene;

    private LinkedList<String> linkedList;

    @FXML
    protected void onHelloButtonClick() {
        linkedList.addItem(textInput.getText());
        textInput.setText("");
    }

    @FXML
    protected void onHello2ButtonClick() {
        linkedList.deleteList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linkedList = new LinkedList<>();

        linkedList.addListener(event -> {
            if(event instanceof LinkedList.AddItemEvent<?> addItemEvent) {
                if(scene.getRoot() instanceof Pane pane) {
                    pane.getChildren().add(new Label((String) addItemEvent.getItem().getValue()));
                }
            }

            if(event instanceof LinkedList.DeleteItemEvent<?> deleteItemEvent) {
                if(scene.getRoot() instanceof Pane pane) {
                    Iterator<Node> nodeIterator = pane.getChildren().iterator();

                    while (nodeIterator.hasNext()) {
                        Node node = nodeIterator.next();
                        if(node instanceof Label label) {
                            if(label.getText().equals(deleteItemEvent.getItem().getValue())) {
                                nodeIterator.remove();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
}