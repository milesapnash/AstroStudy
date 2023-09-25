package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static milesapnash.astrostudy.AstroStudyApplication.setupScene;

public class MainController {

  @FXML
  void toLogin(ActionEvent event) {
    setupScene(event, "login");
  }

  @FXML
  void toRegister(ActionEvent event) {
    setupScene(event, "register");
  }
}
