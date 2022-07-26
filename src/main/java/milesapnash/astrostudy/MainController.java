package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MainController {

  @FXML
  void toMenu(ActionEvent event) {
    switchScene(event, "menu-view.fxml");
  }
}
