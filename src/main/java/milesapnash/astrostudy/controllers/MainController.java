package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MainController {

  @FXML
  void toLogin(ActionEvent event) {
    switchScene(event, "login", null);
  }

  @FXML
  void toRegister(ActionEvent event) {
    switchScene(event, "register", null);
  }
}
