package milesapnash.astrostudy.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import milesapnash.astrostudy.TestData;
import milesapnash.astrostudy.User;

import java.util.Objects;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.MockAPI.getTopicQuestions;
import static milesapnash.astrostudy.MockAPI.getTopics;

public class SelectTopicViewController extends DataController {

  private final ObservableList<String> topics =
      FXCollections.observableArrayList(
          getTopics()
      );
  private User currentUser;

  @FXML
  ComboBox<String> topicBox;
  @FXML
  Button startButton;

  @Override
  public <T> void parseData(T data) {
    currentUser = (User) data;

    topicBox.setItems(topics);
    topicBox.setButtonCell(new ListCell(){
      @Override
      protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item==null){
          setStyle("-fx-text-fill: derive(-fx-control-inner-background,-30%)");
        } else {
          setStyle("-fx-text-fill: -fx-text-inner-color");
          setText(item.toString());
        }
      }
    });
    topicBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> startButton.setVisible(true));
  }

  @FXML
  public void startTopicTest(ActionEvent event){
    final String topic = topicBox.getSelectionModel().getSelectedItem();
    if (!Objects.equals(topic, "")){
      TestData testData = new TestData(currentUser, getTopicQuestions(topic,10));
      switchScene(event, "test", testData);
    }
  }
}
