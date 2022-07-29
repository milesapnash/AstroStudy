package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MenuController {

  @FXML
  private Label userid;

  @FXML
  void toMain(ActionEvent event) {
    switchScene(event, "main");
  }

  public void setUser(User user){
    display(user);
  }

  private void display(User user) {
    userid.setText(user.toString());
  }
}
