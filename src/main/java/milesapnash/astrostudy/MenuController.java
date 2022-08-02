package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MenuController {

  @FXML
  private Label userLabel;
  private User currentUser;

  @FXML
  void toStart(ActionEvent event) {
    switchScene(event, "main");
  }

  public void setUser(User user){
    currentUser = user;
    userLabel.setText("Welcome, " + user.toString());
  }
}
