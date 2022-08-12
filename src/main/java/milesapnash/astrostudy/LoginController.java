package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class LoginController  {

  @FXML
  TextField usernameField;
  @FXML
  PasswordField passwordField;
  @FXML
  Label errorLabel;

  @FXML
  void attemptLogin(ActionEvent event) {
//    String username = usernameField.getText();
//    String password = passwordField.getText();
//    errorLabel.setText("");
//
//    if (username.isEmpty()){
//      errorLabel.setText("Username cannot be blank");
//      return;
//    }
//    if (password.isEmpty()){
//      errorLabel.setText("Password cannot be blank");
//      return;
//    }
//
//    if (MockAPI.login(username,password)) {
    if (true){
      User u = new User("username", 0);
      switchScene(event, "menu", u);
    } else {
      errorLabel.setText("Either username or password incorrect");
    }
  }

  @FXML
  void toSignUp(ActionEvent event) {
    switchScene(event, "register");
  }

  @FXML
  void forgottenPassword(ActionEvent event) {
    switchScene(event, "reset-password");
  }
}
