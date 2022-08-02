package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.LoginController.login;

public class RegisterController {

  @FXML
  TextField usernameField;
  @FXML
  TextField emailField;
  @FXML
  TextField passwordField;

  private boolean validUsername(String username){
    if (username.isEmpty()){
      // Blank error message
      return false;
    }

    if (username.length() < 4 || username.length() > 20){
      //Length error message
      return false;
    }
    return true;
  }

  private boolean validEmail(String email){
    if (email.isEmpty()){
      // Blank error message
      return false;
    }

    final Matcher emailMatcher = Pattern.compile("^(.+)@(.+)$").matcher(email);
    if (!emailMatcher.find()){
      // Not email error message
      return false;
    }

    return true;
  }

  private boolean validPassword(String password){
    //Length/prescence check + real time checker

    final Matcher passwordMatcher = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])").matcher(password);
    if (!passwordMatcher.find()){
      // Error message
      return false;
    }
    return true;
  }

  @FXML
  void attemptRegister(ActionEvent event) {
    final String username = usernameField.getText();
    if (validUsername(username) && validEmail(emailField.getText()) && validPassword(passwordField.getText())){
      // Check username doesn't already exist + email not in use
      // Attempt to create account

      User u = new User(username, 0);
      login(event, u);
    }
  }

  @FXML
  void toLogin(ActionEvent event) {
    switchScene(event, "login");
  }
}
