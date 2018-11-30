package edu.gatech.cs1331.hw7;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author hkim944
 * @version 1.0
 */

public class OfficeHourQueue extends Application {

    private LinkedQueue<String> studentList = new LinkedQueue<>();
    private int maxNum = 0;

    @Override
    public void start(Stage primaryStage) {
        ListView<String> listView = new ListView<>(studentList);

        Text currentQueue = new Text();
        Text maxQueue = new Text();
        currentQueue.setText("Current Number of Students In Queue: 0");
        maxQueue.setText("Max Number of Students In Queue: 0");

        Button addButton = new Button();
        addButton.setText("Add Student");
        Button deQueButton = new Button();
        deQueButton.setText("Dequeue Student");
        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
                studentList.enqueue(inputField.getText());
                currentQueue.setText("Current Number of Students In Queue: "
                        + studentList.size());
                if (maxNum < studentList.size()) {
                    maxNum = studentList.size();
                }
                maxQueue.setText("Max Number of Students In Queue: "
                        + maxNum);
                inputField.setText("");
                inputField.requestFocus();
            });

        deQueButton.setOnAction(e -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Privilege Check");
                dialog.setHeaderText("Confirmation");
                dialog.setContentText("Please Enter Password: ");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(password -> {
                        if (password.equals("CS1321R0X")) {
                            studentList.dequeue();
                            currentQueue.setText("Current Number of Students"
                                    + " In Queue: " + studentList.size());
                        }
                    });
            });

        addButton.disableProperty()
                .bind(Bindings.isEmpty(inputField.textProperty()));

        HBox topBox = new HBox();
        topBox.getChildren().addAll(currentQueue);

        HBox top2Box = new HBox();
        top2Box.getChildren().addAll(maxQueue);

        HBox bottomBox = new HBox();
        bottomBox.getChildren().addAll(inputField, addButton, deQueButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(topBox, top2Box, listView, bottomBox);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CS 1321 Office Hours Queue");
        primaryStage.show();
    }
}
