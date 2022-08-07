package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class ResetPasswordController {

  @FXML
  TextField emailField;
  @FXML
  Label errorLabel;

  @FXML
  void toLogin(ActionEvent event) {
    errorLabel.setText("");
    if (!Objects.equals(emailField.getText(), "")){
      switchScene(event, "login");
    } else {
      errorLabel.setText("Input valid email");
    }
  }
}
