package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import milesapnash.astrostudy.MockAPI;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.AstroStudyApplication.validateEmail;

public class ResetPasswordController {

  @FXML
  TextField emailField;
  @FXML
  Label errorLabel;

  @FXML
  void toLogin(ActionEvent event) {
    errorLabel.setText("");
    if (validateEmail(emailField.getText())){
      MockAPI.sendResetRequest(emailField.getText());
      switchScene(event, "login");
    } else {
      errorLabel.setText("Input valid email");
    }
  }
}
