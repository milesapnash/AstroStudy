package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MainController {

  @FXML
  void toLogin(ActionEvent event) {
    switchScene(event, "login");
  }

  @FXML
  void toRegister(ActionEvent event) {
    switchScene(event, "register");
  }
}
