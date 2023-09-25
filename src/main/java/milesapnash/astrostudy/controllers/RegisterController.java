package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import milesapnash.astrostudy.MockAPI;
import milesapnash.astrostudy.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static milesapnash.astrostudy.AstroStudyApplication.*;

public class RegisterController implements SetupController {

  @FXML
  TextField usernameField;
  @FXML
  TextField emailField;
  @FXML
  TextField passwordField;
  @FXML
  Label usernameErrorLabel;
  @FXML
  Label emailErrorLabel;
  @FXML
  Label passwordErrorLabel;

  @Override
  public void setup() {
    //TODO
  }

  private boolean validUsername(String username){
    usernameErrorLabel.setText("");
    if (username.length() < 4 || username.length() > 20){
      usernameErrorLabel.setText("Must be between 4 and 20 characters");
      return false;
    }
    return true;
  }

  private boolean validEmail(String email){
    emailErrorLabel.setText("");
    if (email.isEmpty()){
      emailErrorLabel.setText("Cannot be blank");
      return false;
    }
    if (!validateEmail(email)){
      emailErrorLabel.setText("Must be a valid address");
      return false;
    }
    return true;
  }

  private boolean validPassword(String password){
    passwordErrorLabel.setText("");
    if (password.length() < 4 || password.length() > 20){
      passwordErrorLabel.setText("Must be between 4 and 20 characters");
      return false;
    }
    final Matcher passwordMatcher = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])").matcher(password);
    if (!passwordMatcher.find()){
      passwordErrorLabel.setText("Must contain upper, lower and number");
      return false;
    }
    return true;
  }

  @FXML
  void attemptRegister(ActionEvent event) {
    final String username = usernameField.getText();
    final boolean uValid = validUsername(username);
    final boolean eValid = validEmail(emailField.getText());
    final boolean pValid = validPassword(passwordField.getText());
    if (uValid && eValid && pValid){
      if (MockAPI.register(username, passwordField.getText())){
        User u = new User(username, 0);
        switchScene(event, "menu", u);
      } else {
        passwordErrorLabel.setText("Invalid Username");
      }
    }
  }

  @FXML
  void toLogin(ActionEvent event) {
    setupScene(event, "login");
  }

}
